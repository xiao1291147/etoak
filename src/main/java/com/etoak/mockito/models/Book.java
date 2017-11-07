package com.etoak.mockito.models;

import java.util.List;

/**
 * Book
 * @author xiaoliang.cui_c
 * @date 2017/11/7
 */
public class Book {

    private String isbn;
    private String title;
    private List<String> authors;
    private String publication;
    private Integer yearOfPublication;
    private Integer numberOfPages;
    private String image;

    public Book() {
    }

    public Book(String isbn, String title, List<String> authors, String publication, Integer yearOfPublication, Integer numberOfPages, String image) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.publication = publication;
        this.yearOfPublication = yearOfPublication;
        this.numberOfPages = numberOfPages;
        this.image = image;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Book{");
        builder.append("isbn='").append(isbn).append('\'');
        builder.append(", title='").append(title).append('\'');
        builder.append(", authors=").append(authors);
        builder.append(", publication='").append(publication).append('\'');
        builder.append(", yearOfPublication=").append(yearOfPublication);
        builder.append(", numberOfPages=").append(numberOfPages);
        builder.append(", image='").append(image).append('\'');
        builder.append('}');
        return builder.toString();
    }
}
