package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.opencsv.CSVWriter;

public class Scraper {
    private static final int MAX_RETRIES = 3;
    private static final int TIMEOUT = 10000; // 10 seconds

    public static void main(String[] args) {
        String url = "https://books.toscrape.com/";

        Document doc = fetchDocument(url, MAX_RETRIES);
        if (doc != null) {
            Elements products = doc.select(".product_pod");

            List<String[]> data = new ArrayList<>();
            data.add(new String[] { "title", "price", "availability" });

            for (Element product : products) {
                String title = product.select("h3 a").attr("title");
                String price = product.select(".price_color").text();
                String availability = product.select(".availability").text().trim();
                data.add(new String[] { title, price, availability });
            }

            try (CSVWriter writer = new CSVWriter(new FileWriter("books.csv"))) {
                writer.writeAll(data);
            } catch (IOException e) {
                System.err.println("Error writing to CSV: " + e.getMessage());
            }

            System.out.println("Data has been saved to books.csv");
        }
    }

    private static Document fetchDocument(String url, int retries) {
        int attempt = 0;
        while (attempt < retries) {
            try {
                return Jsoup.connect(url)
                            .timeout(TIMEOUT)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                            .referrer("http://www.google.com")
                            .get();
            } catch (IOException e) {
                attempt++;
                System.err.println("Attempt " + attempt + " failed: " + e.getMessage());
                if (attempt >= retries) {
                    System.err.println("Max retries reached. Exiting.");
                    return null;
                }
                try {
                    Thread.sleep(2000); // Wait 2 seconds before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread interrupted: " + ie.getMessage());
                    return null;
                }
            }
        }
        return null;
    }
}
