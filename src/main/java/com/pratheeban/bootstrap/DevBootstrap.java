package com.pratheeban.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.pratheeban.model.Author;
import com.pratheeban.model.Book;
import com.pratheeban.model.Publisher;
import com.pratheeban.repositories.AuthorRepository;
import com.pratheeban.repositories.BookRepository;
import com.pratheeban.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private  void initData(){

        Publisher publisher = new Publisher();
        publisher.setName("foo");

        publisherRepository.save(publisher);
        //Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", publisher);
        Publisher ppp2 = new Publisher("Worx", "02");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        ddd.setPublisher(ppp2);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}