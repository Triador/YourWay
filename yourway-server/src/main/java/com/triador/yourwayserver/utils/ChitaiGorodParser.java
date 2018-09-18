package com.triador.yourwayserver.utils;

import com.triador.yourwayserver.models.Book;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChitaiGorodParser {

    private WebDriver driver = SeleniumUtils.getDriver();

    private void parse(List<String> urls) {
        for (String url : urls) {
            driver.get(url);
            driver.manage().window().maximize();
            driver.findElement(By.className("toggle-cardview__item_port")).click();
            scrollDown();
            List<WebElement> webElements = driver.findElements(By.className("product-card"));
            saveBooks(webElements);
            driver.close();
        }
    }

    private void saveBooks(List<WebElement> webElements) {
        int id = 0;
        for (WebElement webElement : webElements) {
            String bookUrl = webElement.findElement(By.className("product-card__img")).getAttribute("href");
            System.out.println(++id + " -----------------------------------------------------------------------");
            Book book = getBook(bookUrl);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

        driver.close();
        driver.switchTo().window(tabs.get(0));

        return book;
    }

    private Book mapBook(Map<String, String> parametersMap) {
        String title = driver.findElement(By.className("product__title")).getText();
        String author = driver.findElement(By.className("product__author")).getText();

        int publicationYear = Integer.parseInt(parametersMap.get("Год издания"));
        int pageAmount = Integer.parseInt(parametersMap.get("Кол-во страниц"));
        String isbns = parametersMap.get("ISBN");

        String description = driver.findElement(By.cssSelector("div[itemprop='description']")).getText();

        Book book = new Book();
        book.setRussianTitle(title);
        book.setAuthor(author);
        book.setPageAmount(pageAmount);
        book.setPublicationYear(publicationYear);
        book.setIsbns(isbns);
        book.setDescription(description);

        return book;
    }

    private void scrollDown() {
        for (int i = 0; i < 3; i++) {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
            try {
//                Thread.sleep(1000);
                try {
                    driver.findElement(By.className("js__show-more-cards")).click();
                } catch (ElementNotVisibleException ignored) {
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return tabs;
    }

    public static void main(String[] args) {
        ChitaiGorodParser cgr = new ChitaiGorodParser();

        //todo вынести константы для озона в проперти файл
        List<String> urls = new ArrayList<>();
        urls.add("https://www.chitai-gorod.ru/catalog/books/9657/");

        cgr.parse(urls);
    }
}
