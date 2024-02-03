package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class Misc {

    @Autowired
    BookRepository bookRepository;

    public void someMethod(Book book) throws Exception{
        log.info("Inside someMethod");
        book.setTitle("book 3");
        bookRepository.save(book);
        log.info("Book Saved: {}", book.getId());
        throw new Exception("ServiceException");
    }
}


