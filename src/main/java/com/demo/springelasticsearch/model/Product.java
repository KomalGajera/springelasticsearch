package com.demo.springelasticsearch.model;

import java.util.Date;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "newproducts")
public class Product {

	private String id;
	private Date created;
	private String description;
	private Long in_stock;
	private boolean is_active;
	private String name;
	private Long price;
	private Long sold;
	private String[] tags;
	
}
