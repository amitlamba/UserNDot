package com.userrndot.common;

import static org.junit.Assert.fail;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.userndot.common.jdbc.StoredProc;
import com.userndot.common.jdbc.StoredProcExecutor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/common-context.xml" })
public class DBConnectionTest {

	@Resource
	private StoredProcExecutor spExecutor;

	@Test
	public void test() {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("First_Name", new String("Amit"));
		Map<String, Object> map = null;
		try {
			map = spExecutor.executeWithoutReturnValue(StoredProc.spGetUsers, params);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println(map);
		fail("Not yet implemented");
	}

}
