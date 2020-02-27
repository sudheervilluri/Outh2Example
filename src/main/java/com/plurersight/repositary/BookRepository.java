package com.plurersight.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plurersight.entity.Book;


public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByName(String name);

}