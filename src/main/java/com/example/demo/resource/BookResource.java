package com.example.demo.resource;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/books")
public class BookResource {

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/save")
    public String saveBook(@RequestBody Book book) {
        Book savedBook = this.bookRepository.save(new Book(book));
        return savedBook.toString();
    }

    @GetMapping("/getall")
    public String findAllBooks() {
        Iterable<Book> books = this.bookRepository.findAll();
        return Lists.newArrayList(books).toString();
    }

    @GetMapping("/get/{author}")
    public String findByAuthor(@PathVariable String author) {
        List<Book> books = this.bookRepository.findByAuthor(author);
        return books.toString();
    }

    @GetMapping("/get/{author}/{year}")
    public String findByAuthorYear(@PathVariable String author,@PathVariable int year) {
        List<Book> books = this.bookRepository.findByAuthorAndYear(author, year);
        return books.toString();
    }

    @DeleteMapping("/deleteall")
    public void removeAllBooks() {
        this.bookRepository.deleteAll();
    }
}
