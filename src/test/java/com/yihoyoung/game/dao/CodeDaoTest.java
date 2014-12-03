package com.yihoyoung.game.dao;

import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class CodeDaoTest {

	private CodeDao codeDao;

	@Before
	public void setUP() {
		codeDao = new CodeDao();
		DataSource dataSource = new SingleConnectionDataSource(
				"jdbc:mysql://yihoyoung.cafe24.com/code",
				"root", "yihoyoung111", true);
		codeDao.setDataSource(dataSource);
	}
	@Test
	public void getCoe() {
		List<Map<String, Object>> datalist = codeDao.getConfig();
		assertFalse(0 == datalist.size());
	}
	
	@Test
	public void testMock() {
		codeDao = mock(CodeDao.class);
	}
}
