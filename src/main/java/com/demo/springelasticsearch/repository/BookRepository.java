package com.demo.springelasticsearch.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.demo.springelasticsearch.model.Book;

@Repository
public interface BookRepository  extends ElasticsearchRepository<Book, String>{

	Page<Book> findByAuthor(String author, PageRequest pagerequest);

	List<Book> findByTitle(String title);

}
