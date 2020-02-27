package com.plurersight.fundamentals;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.plurersight.entity.Book;
import com.plurersight.repositary.BookRepository;

@Component
public class FundamentalsApplication implements CommandLineRunner{
	
	private BookRepository repository;

	public FundamentalsApplication(BookRepository repository) {
        this.repository = repository;
    }

	public void run(String... args) {


		repository.save(new Book("Java"));
		repository.save(new Book("Node"));
		repository.save(new Book("Python"));

		System.out.println("\nfindAll()");
		repository.findAll().forEach(x -> System.out.println(x));

		System.out.println("\nfindById(1L)");
		repository.findById(1l).ifPresent(x -> System.out.println(x));

		System.out.println("\nfindByName('Node')");
		repository.findByName("Node").forEach(x -> System.out.println(x));

	}

}
