# SCT_SD_4
Web Scrapper Task 4 of the Skill Craft Technology.
# Web Scraper

This is a flexible Java project that can scrape data from any website and save it into a CSV file using `Jsoup` for web scraping and `OpenCSV` for CSV writing. The example provided is for scraping book data, but the code can be easily modified to scrape other types of data.

## Features

- Fetches data from a specified URL.
- Saves the scraped data into a CSV file.
- Implements retry logic for network requests.

## Technologies Used

- Java
- Jsoup (for web scraping)
- OpenCSV (for CSV writing)

## Prerequisites

- JDK 8 or above
- Maven (for dependency management)

## Setup

1. Clone the repository:
    ```sh
    git clone https://github.com/ChandanSingh70/SCT_SD_4.git
    cd web-scraper
    ```

2. Add the required dependencies to your `pom.xml` file:
    ```xml
    <dependencies>
        <dependency>
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>1.14.3</version>
        </dependency>
        <dependency>
            <groupId>com.opencsv</groupId>
            <artifactId>opencsv</artifactId>
            <version>5.5.2</version>
        </dependency>
    </dependencies>
    ```

3. Build the project:
    ```sh
    mvn clean install
    ```

## Running the Scraper

1. Modify the `Scraper.java` file to suit your target website by changing the URL and the data extraction logic.

2. Run the main class `Scraper`:
    ```sh
    mvn exec:java -Dexec.mainClass="com.example.Scraper"
    ```

3. The scraped data will be saved to a CSV file in the project directory.



  
