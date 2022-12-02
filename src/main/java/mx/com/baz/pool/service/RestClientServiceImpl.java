package mx.com.baz.pool.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestClientServiceImpl {
	
	private String url="http://localhost:8083/homologacion/info/alnova";	
	//private String url="http://10.82.56.194:8083/homologacion/info/alnova";	
	
	public void  getAllObjects(String json) {
		try {
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<String> entity = new HttpEntity<String>(json);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,entity,String.class);
			String lista = response.getBody();

		}catch (ResourceAccessException rae) {
			log.error("No se puede conectar al servicio", rae);
		}
		
	}
	
}
