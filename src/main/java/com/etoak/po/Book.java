package com.etoak.po;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * TODO
 * Created by xiao1 on 2017/7/27.
 */
public class Book implements Comparable<Book> {
    private Person author;
    private String title;
    private String publisher;
    private String isbn;
    private double price;

    public Book(String isbn) {
        this.isbn = isbn;
    }

    public Book(Person author, String title, String publisher, String isbn, double price) {
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.isbn = isbn;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(book.price, price) == 0 &&
                Objects.equal(author, book.author) &&
                Objects.equal(title, book.title) &&
                Objects.equal(publisher, book.publisher) &&
                Objects.equal(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(author, title, publisher, isbn, price);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Book{");
        builder.append("author=").append(author);
        builder.append(", title='").append(title).append('\'');
        builder.append(", publisher='").append(publisher).append('\'');
        builder.append(", isbn='").append(isbn).append('\'');
        builder.append(", price=").append(price);
        builder.append('}');
        return builder.toString();
    }

    @Override
    public int compareTo(Book o) {
        return ComparisonChain.start()
                .compare(this.title, o.getTitle())
                .compare(this.author, o.getAuthor())
                .compare(this.publisher, o.getPublisher())
                .compare(this.isbn, o.getIsbn())
                .compare(this.price, o.getPrice())
                .result();
    }

    public Person getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
