package com.demo.springelasticsearch.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springelasticsearch.model.Book;
import com.demo.springelasticsearch.model.Customer;
import com.demo.springelasticsearch.model.Product;
import com.demo.springelasticsearch.model.UploadRequestDTO;
import com.demo.springelasticsearch.service.BookService;

@RestController
public class BookController {

	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;

	@Autowired
	private BookService bookservice;

	@PostMapping("/createindex")
	public void createIndex() {
		Boolean isCraeted = elasticsearchRestTemplate.indexOps(Book.class).create();
		System.out.println("Index is craeted successfully:" + isCraeted);
	}

	@GetMapping("/findname")
	public void findName() {
		
//		SearchRequest searchRequest = new SearchRequest(); 
//		SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder(); 
//		searchSourceBuilder.query(QueryBuilders.matchAllQuery()); 
//		searchRequest.source(searchSourceBuilder);


		QueryBuilder queryBuilder = QueryBuilders.matchQuery("firstname", "peter");

		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

		List<SearchHit<Customer>> customer = elasticsearchRestTemplate
				.search(searchQuery, Customer.class, IndexCoordinates.of("customers")).getSearchHits();

		List<Customer> finalcustomer = new ArrayList<>();
		for (SearchHit<Customer> searchHit : customer) {
			finalcustomer.add(searchHit.getContent());
			System.out.println("\n\n This is search result is:" + searchHit.getContent());
		}

	}

	@GetMapping("/findage")
	public void findAll() {
		Criteria criteria = new Criteria("age").greaterThan(20.0).lessThan(100.0);

		CriteriaQuery searchQuery = new CriteriaQuery(criteria);

		SearchHits<Customer> products = elasticsearchRestTemplate.search(searchQuery, Customer.class,
				IndexCoordinates.of("customers"));
		System.out.println("\n\n Result is:" + products.getSearchHit(0).getContent());
	}

	@GetMapping("/findproductname")
	public void findProductName() {
//		QueryBuilder queryBuilder = QueryBuilders.m atchQuery("price", 105);
//		ArrayList<String> tags=new ArrayList<>();
//		tags.add("Coffee");
//		tags.add("Alcohol");
//		QueryBuilder queryBuilder = QueryBuilders.termsQuery("tags.keyword", tags);
//		QueryBuilder queryBuilder = QueryBuilders.existsQuery("tags");
//		QueryBuilder queryBuilder = QueryBuilders.prefixQuery("name", "co");
//		QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name.keyword","*er");
//		QueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("description", "magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien");
//		QueryBuilder mustQuey= QueryBuilders.matchQuery("name", "Milk Frother");
//		QueryBuilder mustNotQuery= QueryBuilders.matchQuery("price", 500);
//		QueryBuilder shouldQuery = QueryBuilders.existsQuery("tags");
//		QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(mustQuey).mustNot(mustNotQuery).should(shouldQuery);
		QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("Wine -Rubyport").field("namename");
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

		List<SearchHit<Product>> product = elasticsearchRestTemplate
				.search(searchQuery, Product.class, IndexCoordinates.of("newproducts")).getSearchHits();
		int count = 0;
		List<Product> products = new ArrayList<>();
		for (SearchHit<Product> searchHit : product) {
			products.add(searchHit.getContent());
			System.out.println("\n\n" + searchHit.getContent());
			count++;
		}

		System.out.println("\n\n\n total number of hits is:" + count);

	}

	/**
	 * @param dto
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@PostMapping("/v1/fileReleaseUpload")
	public void fileReleaseUpload(@Valid UploadRequestDTO dto) throws FileNotFoundException, IOException {
		bookservice.fileReleaseUpload(dto);
	}
	
	@PostMapping("/generatePpt")
	public void generatePpt() throws IOException, URISyntaxException {
		bookservice.generatePpt();
	}
	

}
