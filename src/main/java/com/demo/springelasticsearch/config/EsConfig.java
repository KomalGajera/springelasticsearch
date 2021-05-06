package com.demo.springelasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.demo.springelasticsearch.repository")
@ComponentScan(basePackages = "com.demo.springelasticsearch.*")
public class EsConfig {

	@Value("${spring.data.elasticsearch.cluster-nodes}")
	private String elasticsearchHost;

	@Bean
	public RestHighLevelClient client() {
		String[] hostValues = elasticsearchHost.split(":");
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost(hostValues[0], 9200, HttpHost.DEFAULT_SCHEME_NAME)));
		return client;
	}

	@Bean(name = "elasticsearchTemplate")
	public ElasticsearchRestTemplate elasticsearchRestTemplate() {
		return new ElasticsearchRestTemplate(client());
	}

//	@Bean
//	public RestHighLevelClient client() {
//		ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo("localhost:9200").build();
//
//		return RestClients.create(clientConfiguration).rest();
//	}
//
//	@Bean(name = "elasticsearchTemplate")
//	public ElasticsearchRestTemplate elasticsearchRestTemplate() {
//		return new ElasticsearchRestTemplate(client());
//	}
}
