package com.userndot.common.jdbc;

import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Named
public class StoredProcExecutor {
    @Resource(name="jdbcMysqlContext")
    private JdbcTemplate jdbcTemplate;

    private Logger logger = LoggerFactory.getLogger(StoredProcExecutor.class);

    /**
     * Executes Stored Procedure with supplied name and with given
     * parameters
     * 
     * @param sp
     * @param params
     * @return
     */
    public Map<String, Object> execute(StoredProc sp, MapSqlParameterSource params) {
        return execute(sp.name(), params);
    }

    public Map<String, Object> execute(String sp, MapSqlParameterSource params) {
        logger.debug("Invoking Stored Proc: " + sp + " with Params: " + params.getValues());
        SimpleJdbcCall storedProc = new SimpleJdbcCall(jdbcTemplate).withProcedureName(sp).withReturnValue();
        Map<String, Object> outputMap = storedProc.execute(params);
        return outputMap;
    }

    public Map<String, Object> executeWithoutReturnValue(StoredProc sp, MapSqlParameterSource params) {
        return executeWithoutReturnValue(sp.name(), params);
    }

    public Map<String, Object> executeWithoutReturnValue(String sp, MapSqlParameterSource params) {
        logger.debug("Invoking Stored Proc: " + sp + " with Params: " + params.getValues());
        System.out.println("Invoking Stored Proc: " + sp + " with Params: " + params.getValues());
        SimpleJdbcCall storedProc = new SimpleJdbcCall(jdbcTemplate).withProcedureName(sp);
        Map<String, Object> outputMap = storedProc.execute(params);        
        logger.info(sp + " returned data : " + outputMap);
        return outputMap;
    }
    
    
}

