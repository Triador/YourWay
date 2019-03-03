package com.triador.yourwayserver.dao.model;

import java.util.Objects;

public class BookTitle {

    private int id;
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookTitle bookTitle = (BookTitle) o;
        return id == bookTitle.id &&
                Objects.equals(value, bookTitle.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, value);
    }

    @Override
    public String toString() {
        return "BookTitle{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
