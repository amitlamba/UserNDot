package com.userndot.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mongodb.MongoClient;

@Configuration
public class CommonConfig {

	@Value("${mongoFactory.ip}")
	private String mongoIP;

	@Value("${jdbc.mysqlDriverClassName}")
	private String mysqlJdbcDriverClassName;

	@Value("${dataSource.url}")
	private String mysqlUrl;

	@Value("${dataSource.username}")
	private String mysqlUsername;

	@Value("${dataSource.password}")
	private String mysqlPassword;

	@Value("${jdbc.initialSize}")
	private Integer initialSize;

	@Value("${jdbc.maxActive}")
	private Integer maxActive;

	@Bean(name = { "mongoclient" })
	public MongoClient mongoClient() {
		MongoClient mongoClient = new MongoClient(mongoIP);
		return mongoClient;
	}

	@Bean(name = { "jdbcMysqlContext" })
	public JdbcTemplate jdbcTemplate() {
		System.out.println("initialSize: " + initialSize);
		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName(mysqlJdbcDriverClassName);
		dataSource.setUrl(mysqlUrl);
		dataSource.setUsername(mysqlUsername);
		dataSource.setPassword(mysqlPassword);
		dataSource.setInitialSize(initialSize);
		dataSource.setMaxActive(maxActive);
		return new JdbcTemplate(dataSource);
	}
	
}
