package com.sapient.springapp.repository;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sapient.springapp.domain.Custom;

/**
 * Repository for querying the db with custom dynamically built query
 * @author Jatin Girhotra
 *
 */
@Repository
public class JdbcRepository {
	//spring jdbctemplate wrapper
	JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Custom> getSomeGenericReport(String input)
	{
		String[] inputSplit=input.split(":");
		//base query, this is an example but anything in this query (including the whole query) can be manipulated using user inputs, context, application properties,
		String sql="SELECT p1.POLICY_NAME as policyname, p2.description from policy p1 inner join  policy p2 where p1.id=p2.id";

		//Add dynamic where clause

		for (int i = 0; i < inputSplit.length; i++) {
			sql+=" and p1."+inputSplit[i]+" like '"+inputSplit[++i]+"%'";
		}

		List<Custom> rows = jdbcTemplate.query(sql,new BeanPropertyRowMapper(Custom.class));
		return rows;
	}



}