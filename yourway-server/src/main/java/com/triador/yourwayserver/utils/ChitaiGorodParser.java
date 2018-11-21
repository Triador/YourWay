package com.triador.yourwayserver.utils;

import com.triador.yourwayserver.dao.impl.BookDAO;
import com.triador.yourwayserver.dao.model.Book;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Lazy
public class ChitaiGorodParser {

    private WebDriver driver = SeleniumUtils.getDriver();
    private BookDAO bookDAO;
    private Converter converter;

    @Autowired
    public ChitaiGorodParser(BookDAO bookDAO, Converter converter) {
        this.bookDAO = bookDAO;
        this.converter = converter;
    }

    public void parse(List<String> urls) {
        for (String url : urls) {
            driver.get(url);
            sleep(5000);
            driver.findElement(By.className("toggle-cardview__item_port")).click();
            scrollDown();
            List<WebElement> books = driver.findElements(By.className("product-card"));
            List<WebElement> smallBookImages = driver.findElements(By.cssSelector("img.lazy"));
            List<WebElement> bookTitles = driver.findElements(By.className("product-card__title"));

            for (int i = 0; i < smallBookImages.size(); i++) {
                String imageUrl = smallBookImages.get(i).getAttribute("data-original");
                String extension = getImageExtension(imageUrl);
                String bookTitle = bookTitles.get(i).getText();
                downloadBookImage(imageUrl, "small_" + converter.convert(bookTitle), extension);
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
            bookDAO.save(book);
            System.out.println("book " + book.getRussianTitle() + " saved");
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
        if (!imageUrl.isEmpty()) {
            String extension = getImageExtension(imageUrl);
            book.setImageLink(converter.convert(book.getRussianTitle()) + extension);

            downloadBookImage(imageUrl, "big_" + converter.convert(book.getRussianTitle()), extension);
        }

        driver.close();
        driver.switchTo().window(tabs.get(0));

        return book;
    }

    private Book mapBook(Map<String, String> parametersMap) {
        String title = driver.findElement(By.className("product__title")).getText();
        String author = driver.findElement(By.className("product__author")).getText();

        int publicationYear = Integer.parseInt(parametersMap.getOrDefault("Год издания", "0"));
        int pageAmount = Integer.parseInt(parametersMap.getOrDefault("Кол-во страниц", "0"));
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
            Files.copy(in, Paths.get("C:\\Users\\aandreev\\Workspace\\YourWay\\yourway-client\\src\\assets\\book_images\\" + bookTitle + extension));
        } catch (FileAlreadyExistsException e) {
            System.out.println("this image has been already downloaded");
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
}
