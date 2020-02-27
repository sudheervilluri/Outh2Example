package com.plurersight;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plurersight.entity.Book;
import com.plurersight.repositary.BookRepository;

@RestController
public class bookController {
	 private final Logger log = LoggerFactory.getLogger(bookController.class);
	    
	 @Autowired
	 BookRepository bookRepository;


	    @GetMapping("/books")
	    Collection<Book> books() {
	        return bookRepository.findAll();
	    }

	    @GetMapping("/book/{id}")
	    ResponseEntity<?> getbook(@PathVariable Long id) {
	        Optional<Book> book = bookRepository.findById(id);
	        return book.map(response -> ResponseEntity.ok().body(response))
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    @PostMapping("/book")
	    ResponseEntity<Book> createbook(@Valid @RequestBody Book book) throws URISyntaxException {
	        log.info("Request to create book: {}", book);
	        Book result = bookRepository.save(book);
	        return ResponseEntity.created(new URI("/api/book/" + result.getId()))
	                .body(result);
	    }

	    @PutMapping("/book/{id}")
	    ResponseEntity<Book> updatebook(@Valid @RequestBody Book book) {
	        log.info("Request to update book: {}", book);
	        Book result = bookRepository.save(book);
	        return ResponseEntity.ok().body(result);
	    }

	    @DeleteMapping("/book/{id}")
	    public ResponseEntity<?> deletebook(@PathVariable Long id) {
	        log.info("Request to delete book: {}", id);
	        bookRepository.deleteById(id);
	        return ResponseEntity.ok().build();
	    }
}
