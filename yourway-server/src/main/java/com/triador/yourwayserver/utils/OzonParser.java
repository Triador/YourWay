package com.triador.yourwayserver.utils;

import com.triador.yourwayserver.models.Book;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OzonParser {

    private WebDriver driver = SeleniumUtils.getDriver();

    private void parse(List<String> urls) {
        for (String url : urls) {
            driver.get(url);
            driver.manage().window().maximize();
            scrollDown();
            List<WebElement> webElements = driver.findElements(By.className("bOneTile"));
            saveBooks(webElements);
            driver.close();
        }
    }

    private void scrollDown() {
        for (int i = 0; i < getScrollsAmount(); i++) {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int getScrollsAmount() {
        String text = driver.findElement(By.className("eMainHeader_Count")).getText();
        int amount = Integer.parseInt(text.substring(0, text.indexOf(" ")));
        return amount / 20;
    }

    private void saveBooks(List<WebElement> webElements) {
        int id = 0;
        for (WebElement webElement : webElements) {
            String bookUrl = webElement.getAttribute("data-href");
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

        Book book = null;
        if (!isAudioBook()) {
            if (isForeignBook()) {
                driver.findElement(By.className("eItemProperties_all")).click();
            }

            List<WebElement> bookParametersValues = driver.findElements(By.className("eItemProperties_text"));
            List<WebElement> bookParameters = driver.findElements(By.className("eItemProperties_name"));
            Map<String, String> parametersMap = new HashMap<>();
            for (int i = 0; i < bookParameters.size(); i++) {
                parametersMap.put(bookParameters.get(i).getText(), bookParametersValues.get(i).getText());
            }
            book = mapBook(parametersMap);
        }

        driver.close();
        driver.switchTo().window(tabs.get(0));

        return book;
    }

    private Book mapBook(Map<String, String> parametersMap) {
        String russianTitle = driver.findElement(By.className("bItemName")).getText();

        String originalTitle = parametersMap.get("Ориг.название");
        String author = parametersMap.get("Автор");
        int pageAmount = Integer.parseInt(parametersMap.get("Количество страниц"));
        int publicationYear = Integer.parseInt(parametersMap.get("Год выпуска"));
        String isbns = parametersMap.get("ISBN");

        String description = driver.findElement(By.className("eItemDescription_text")).getText();
        description = clipBookDescription(description);

        Book book = new Book();
        book.setRussianTitle(russianTitle);
        book.setOriginalTitle(originalTitle);
        book.setAuthor(author);
        book.setPageAmount(pageAmount);
        book.setPublicationYear(publicationYear);
        book.setIsbns(isbns);
        book.setDescription(description);

        return book;
    }

    private ArrayList<String> openNewBookWindow(String bookUrl) {
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://www.ozon.ru" + bookUrl);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return tabs;
    }

    private boolean isForeignBook() {
        String firstParameter = driver.findElement(By.className("eItemProperties_name")).getText();
        return firstParameter.startsWith("Ориг");
    }

    private String clipBookDescription(String description) {
        int endIndex = description.length();
        if (description.contains("\n\n")) {
            endIndex = description.indexOf("\n\n");

            if (endIndex < 15) endIndex = description.length();
        }
        return description.substring(0, endIndex);
    }

    private String getBookType() {
        String type;
        try {
            type = driver.findElement(By.className("eFormat_Text")).getText();
        } catch (NoSuchElementException e) {
            type = driver.findElement(By.className("eSingleFormat_NameText")).getText();
        }
        System.out.println(type);
        return type;
    }

    private boolean isAudioBook() {
        String type = getBookType();
        return "Аудиокнига".equals(type)
                || "Цифровая аудиокнига".equals(type);
    }

    private void downloadBookImage(String url) {
        try(InputStream in = new URL(url).openStream()){
            Files.copy(in, Paths.get("C:/File/To/Save/To/image.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        OzonParser htmlParser = new OzonParser();

        //todo вынести константы для озона в проперти файл
        List<String> urls = new ArrayList<>();
        urls.add("https://www.ozon.ru/catalog/1137234/?bests=1&store=1%2c0");

        htmlParser.parse(urls);
    }
}
