package com.etoak.mockito.dal;

import java.util.Collections;
import java.util.List;

import com.etoak.mockito.models.Book;


/**
 * BookDAL
 * @author xiaoliang.cui_c
 * @date 2017/11/7
 */
public class BookDAL {
    private static BookDAL bookDAL = new BookDAL();

    public List<Book> getAllBooks() {
        return Collections.emptyList();
    }

    public Book getBook(String isbn) {
        return null;
    }

    public String addBook(Book book) {
        return book.getIsbn();
    }

    public String updateBook(Book book) {
        return book.getIsbn();
    }

    public static BookDAL getInstance() {
        return bookDAL;
    }
}
