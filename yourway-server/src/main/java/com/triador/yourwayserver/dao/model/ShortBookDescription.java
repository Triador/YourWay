package com.triador.yourwayserver.dao.model;

import java.util.Objects;

public class ShortBookDescription {

    private int bookId;
    private String title;
    private String imageLink;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortBookDescription that = (ShortBookDescription) o;
        return bookId == that.bookId &&
                Objects.equals(title, that.title) &&
                Objects.equals(imageLink, that.imageLink);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bookId, title, imageLink);
    }

    @Override
    public String toString() {
        return "ShortBookDescription{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
