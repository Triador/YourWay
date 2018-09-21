package com.triador.yourwayserver.utils;

import com.triador.yourwayserver.dao.model.Book;
import org.openqa.selenium.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChitaiGorodParser {

    private WebDriver driver = SeleniumUtils.getDriver();

    private void parse(List<String> urls) {
        for (String url : urls) {
            driver.get(url);
            sleep(5000);
            driver.manage().window().maximize();
            driver.findElement(By.className("toggle-cardview__item_port")).click();
            scrollDown();
            List<WebElement> books = driver.findElements(By.className("product-card"));
            List<WebElement> smallBookImages = driver.findElements(By.cssSelector("img.lazy"));
            List<WebElement> bookTitles = driver.findElements(By.className("product-card__title"));

            for (int i = 0; i < smallBookImages.size(); i++) {
                String imageUrl = smallBookImages.get(i).getAttribute("data-original");
                String extension = getImageExtension(imageUrl);
                String bookTitle = bookTitles.get(i).getText();
                downloadBookImage(imageUrl, bookTitle + "_small", extension);
            }

            saveBooks(books);
            driver.close();
        }
    }

    private void saveBooks(List<WebElement> webElements) {
        int id = 0;
        for (WebElement webElement : webElements) {
            String bookUrl = webElement.findElement(By.className("product-card__img")).getAttribute("href");
            System.out.println(++id + " -----------------------------------------------------------------------");
            Book book = getBook(bookUrl);
            sleep(1000);
            System.out.println(book);
        }
    }

    private Book getBook(String bookUrl) {
        ArrayList<String> tabs = openNewBookWindow(bookUrl);

        List<WebElement> bookParametersValues = driver.findElements(By.className("product-prop__value"));
        List<WebElement> bookParameters = driver.findElements(By.className("product-prop__title"));
        Map<String, String> parametersMap = new HashMap<>();
        for (int i = 0; i < bookParameters.size(); i++) {
            parametersMap.put(bookParameters.get(i).getText(), bookParametersValues.get(i).getText());
        }
        Book book = mapBook(parametersMap);

        String imageUrl = driver.findElement(By.cssSelector("img[itemprop='image']"))
                .getAttribute("src");
        String extension = getImageExtension(imageUrl);

        downloadBookImage(imageUrl, book.getRussianTitle() + "_big", extension);

        driver.close();
        driver.switchTo().window(tabs.get(0));

        return book;
    }

    private Book mapBook(Map<String, String> parametersMap) {
        String title = driver.findElement(By.className("product__title")).getText();
        String author = driver.findElement(By.className("product__author")).getText();

        int publicationYear = Integer.parseInt(parametersMap.getOrDefault("Год издания", "undefined"));
        int pageAmount = Integer.parseInt(parametersMap.getOrDefault("Кол-во страниц", "undefined"));
        String isbns = parametersMap.getOrDefault("ISBN", "undefined");

        String description = driver.findElement(By.cssSelector("div[itemprop='description']")).getText();

        Book book = new Book();
        book.setRussianTitle(title);
        book.setAuthor(author);
        book.setPageAmount(pageAmount);
        book.setPublicationYear(publicationYear);
        book.setIsbn(isbns);
        book.setDescription(description);

        return book;
    }

    private void scrollDown() {
        for (int i = 0; i < 3; i++) {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
            try {
                driver.findElement(By.className("js__show-more-cards")).click();
            } catch (ElementNotVisibleException ignored) {}
            sleep(1000);
        }
    }

    private int getScrollsAmount() {
        String text = driver.findElement(By.className("count-result__value")).getText();
        int amount = Integer.parseInt(text);
        return amount / 20;
    }

    private ArrayList<String> openNewBookWindow(String bookUrl) {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(bookUrl);
        sleep(1000);

        return tabs;
    }

    private void downloadBookImage(String url, String bookTitle, String extension) {
        try (InputStream in = new URL(url).openStream()) {
            Files.copy(in, Paths.get("/Users/antonandreev/Desktop/Photo/book_images/" + bookTitle + extension));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getImageExtension(String imageUrl) {
        return imageUrl.substring(imageUrl.lastIndexOf("."));
    }

    public static void main(String[] args) {
        ChitaiGorodParser cgr = new ChitaiGorodParser();

        //todo вынести константы для в проперти файл
        List<String> urls = new ArrayList<>();
        urls.add("https://www.chitai-gorod.ru/catalog/books/9657/");

        cgr.parse(urls);
    }
}
