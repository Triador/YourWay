package com.triador.yourwayserver.models;

import java.util.Objects;

public class Book {

    private int id;
    private String russianTitle;
    private String originTitle;
    private String author;
    private int pageAmount;
    private int publicationYear;
    private String isbn;
    private String description;
    private String imageLink;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRussianTitle() {
        return russianTitle;
    }

    public void setRussianTitle(String russianTitle) {
        this.russianTitle = russianTitle;
    }

    public String getOriginTitle() {
        return originTitle;
    }

    public void setOriginTitle(String originTitle) {
        this.originTitle = originTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageAmount() {
        return pageAmount;
    }

    public void setPageAmount(int pageAmount) {
        this.pageAmount = pageAmount;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Book book = (Book) o;
        return id == book.id &&
                pageAmount == book.pageAmount &&
                publicationYear == book.publicationYear &&
                Objects.equals(russianTitle, book.russianTitle) &&
                Objects.equals(originTitle, book.originTitle) &&
                Objects.equals(author, book.author) &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(description, book.description) &&
                Objects.equals(imageLink, book.imageLink);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, russianTitle, originTitle, author, pageAmount, publicationYear, isbn, description, imageLink);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", russianTitle='" + russianTitle + '\'' +
                ", originTitle='" + originTitle + '\'' +
                ", author='" + author + '\'' +
                ", pageAmount=" + pageAmount +
                ", publicationYear=" + publicationYear +
                ", isbn='" + isbn + '\'' +
                ", description='" + description + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
