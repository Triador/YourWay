package com.triador.yourwayserver.utils;

import com.triador.yourwayserver.models.Book;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class HtmlOzonParser {

    private WebDriver driver;

    private void init() {
        String exePath = "/Users/antonandreev/Downloads/chromedriver";
        System.setProperty("webdriver.chrome.driver", exePath);
        driver = new ChromeDriver();
    }

    private void parse(List<String> urls) {
        for (String url : urls) {
            driver.get(url);
            List<WebElement> webElements = driver.findElements(By.className("bOneTile"));
            saveBooks(webElements);
            driver.close();
        }
    }

    public void saveBooks(List<WebElement> webElements) {
        for (WebElement webElement : webElements) {
            String bookUrl = webElement.getAttribute("data-href");
            Book book = getBook(bookUrl);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(book);
        }
    }

    private Book getBook(String bookUrl) {
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://www.ozon.ru" + bookUrl);

        String title = driver.findElement(By.className("bItemName")).getText();

        List<WebElement> bookParameters = driver.findElements(By.className("eItemProperties_text"));
        String author = bookParameters.get(0).getText();
        int pageAmount = Integer.parseInt(bookParameters.get(2).getText());
        int publicationYear = Integer.parseInt(bookParameters.get(3).getText());
        String isbns = bookParameters.get(4).getText();

        driver.close();
        driver.switchTo().window(tabs.get(0));

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPageAmount(pageAmount);
        book.setPublicationYear(publicationYear);
        book.setIsbns(isbns);

        return book;
    }

    public static void main(String[] args) {
        HtmlOzonParser htmlParser = new HtmlOzonParser();
        htmlParser.init();

        //todo вынести константы для озона в проперти файл
        List<String> urls = new ArrayList<>();
        urls.add("https://www.ozon.ru/catalog/1137234/?bests=1&store=1%2c0");

        htmlParser.parse(urls);
    }
}
