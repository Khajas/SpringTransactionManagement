package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(rollbackFor = Exception.class)
public class Misc {

    @Autowired
    BookRepository bookRepository;

    public void someMethod(Book book) throws Exception{
        book.setTitle("book 3");
        bookRepository.save(book);
        throw new Exception("ServiceException");
    }
}


