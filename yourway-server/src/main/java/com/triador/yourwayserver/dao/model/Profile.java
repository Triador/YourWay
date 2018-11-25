package com.triador.yourwayserver.dao.model;

import java.util.List;

public class Profile {

    private String username;

    private String imageLink;

    private List<Book> progressBooks;

    private List<Book> futureBooks;

    private List<Book> finishedBooks;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public List<Book> getProgressBooks() {
        return progressBooks;
    }

    public void setProgressBooks(List<Book> progressBooks) {
        this.progressBooks = progressBooks;
    }

    public List<Book> getFutureBooks() {
        return futureBooks;
    }

    public void setFutureBooks(List<Book> futureBooks) {
        this.futureBooks = futureBooks;
    }

    public List<Book> getFinishedBooks() {
        return finishedBooks;
    }

    public void setFinishedBooks(List<Book> finishedBooks) {
        this.finishedBooks = finishedBooks;
    }
}
