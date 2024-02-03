package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private Misc misc;

    private final ExecutorService es = Executors.newFixedThreadPool(10);

    public void createBooks() {
        log.info("Inside CreateBooks");
        Book book1 = new Book("Book 1", "Author 1");
        bookRepository.save(book1);
        log.info("Book saved: {}", book1.getId());
        es.submit( () ->{
            try{
                log.info("Inside submit");
                misc.someMethod(book1);
            }catch (Exception e){
                log.info("Entering catch block");
                bookRepository.updateTitleById(book1.getId(), "Book 2");
                log.info("Exiting catch block");
            }
        });
    }

    public Optional<Book> accessBook(Long id){
        return bookRepository.findById(id);
    }

}