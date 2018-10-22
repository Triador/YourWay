package com.triador.yourwayserver.dao.model;

import java.util.Objects;

public class BookTitle {
    private int id;
    private String title;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookTitle bookTitle = (BookTitle) o;
        return id == bookTitle.id &&
                Objects.equals(title, bookTitle.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "BookTitle{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
