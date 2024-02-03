package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query( value = "update Book b set b.title = :title where b.id = :id", nativeQuery = true)
    @Modifying
    public void updateTitleById(@Param("id") Long id, @Param("title") String title);
}
