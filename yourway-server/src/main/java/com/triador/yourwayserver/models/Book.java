package com.triador.yourwayserver.models;

public class Book {

    private int id;
    private String title;
    private String author;
    private int pageAmount;
    private int publicationYear;
    private String isbns;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getIsbns() {
        return isbns;
    }

    public void setIsbns(String isbns) {
        this.isbns = isbns;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (pageAmount != book.pageAmount) return false;
        if (publicationYear != book.publicationYear) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (isbns != null ? !isbns.equals(book.isbns) : book.isbns != null) return false;
        return description != null ? description.equals(book.description) : book.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + pageAmount;
        result = 31 * result + publicationYear;
        result = 31 * result + (isbns != null ? isbns.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pageAmount=" + pageAmount +
                ", publicationYear=" + publicationYear +
                ", isbns='" + isbns + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
