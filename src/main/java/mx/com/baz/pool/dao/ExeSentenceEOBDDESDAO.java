package mx.com.baz.pool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j2;
import mx.com.baz.pool.config.DataSourseBDConfig;
import mx.com.baz.pool.exceptions.SentenceException;
import mx.com.baz.pool.service.RestClientServiceImpl;
import reactor.core.publisher.Mono;

/**
 * @description
 * 
 * @project pool-conexiones
 * @author Mary C
 * @created Agosto 2022
 * @version 1.0
 */
@Log4j2
@Repository
public class ExeSentenceEOBDDESDAO {
	
	@Autowired
	private RestClientServiceImpl restTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(ExeSentenceEOBDDESDAO.class);
	
	@SuppressWarnings({ "unchecked", "restriction" })
	public Mono<String> getEOBDDES(String query){
		try {
			Connection connection =  DataSourseBDConfig.getConnectionEO();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet r = statement.executeQuery(); //SELECT * FROM TCCONCILIACION
			
			ResultSetMetaData md = r.getMetaData();
			int numCols = md.getColumnCount();
			List<String> colNames = IntStream.range(0, numCols)
					.mapToObj(i -> {
					try {
							return md.getColumnName(i + 1);
						} catch (SQLException e) {
							e.printStackTrace();
							return "?";
						}
					}) 
					.collect(Collectors.toList());
					
			JSONArray result = new JSONArray();
			
			while (r.next()) {
				JSONObject row = new JSONObject();
				colNames.forEach(cn -> {
					try {
						row.put(cn, r.getObject(cn));
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				result.add(row);
				restTemplate.getAllObjects(row.toString());
				log.info(row.toString());
			}	
			
			log.info(result.toString());
			return Mono.just(result.toString());
			
		} catch (Exception e) {
			return Mono.error(e);
			//return Mono.error(new SentenceException(400, "", e.getMessage()));

		}
		
	}
	
}
