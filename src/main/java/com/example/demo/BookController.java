package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/")
    public String m(){
        bookService.createBooks();
        return "done";
    }

    @GetMapping("/{id}")
    public Book getBookById(@RequestParam("id") Long id){
        return bookService.accessBook(id).orElse(null);
    }
}
