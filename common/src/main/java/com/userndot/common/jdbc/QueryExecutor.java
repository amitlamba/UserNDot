package com.userndot.common.jdbc;

import java.sql.ResultSet;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class QueryExecutor {

	@Resource(name="jdbcContext")
    private JdbcTemplate jdbcTemplate;
	private static Logger logger = LoggerFactory.getLogger(QueryExecutor.class);
	
	public ResultSet executeSelectQuery(String queryString, Map<String,Object> params) {
		
		return null;
	}

	
}
