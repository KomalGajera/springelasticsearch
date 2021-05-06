package com.demo.springelasticsearch.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.demo.springelasticsearch.model.Book;
import com.demo.springelasticsearch.model.UploadRequestDTO;

public interface BookService {
	
	Book save(Book book);
	
	void delete(Book book);
	
	Book findOne(String id);
	
	Iterable<Book> findAll();
	
	Page<Book> findByAuthor(String author,PageRequest pagerequest);

	List<Book> fingBytitle(String title);

	void fileReleaseUpload(@Valid UploadRequestDTO dto) throws FileNotFoundException, IOException;

	void generatePpt() throws FileNotFoundException, IOException, URISyntaxException;
}
