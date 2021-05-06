package com.demo.springelasticsearch.model;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "books")
public class Book {

	private String id;
	private String title;
	private String author;
	private String releasedate;

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", releasedate=" + releasedate + "]";
	}

}
