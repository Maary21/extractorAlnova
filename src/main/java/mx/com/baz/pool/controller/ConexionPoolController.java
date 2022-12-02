package mx.com.baz.pool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sun.tools.sjavac.Log;
import lombok.extern.slf4j.Slf4j;
import mx.com.baz.pool.model.response.GenericResponse;
import mx.com.baz.pool.service.IExecuteService;
import mx.com.baz.pool.service.RestClientServiceImpl;

import org.springframework.http.MediaType;
import static mx.com.baz.pool.utils.Constant.SEPARADOR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Map;

import reactor.core.publisher.Mono;

/**
 * @description
 * 
 * @project pool-conexiones
 * @author Mary C
 * @created Agosto 2022
 * @version 1.0
 */

@Slf4j
@CrossOrigin(origins = "*" , methods = {RequestMethod.GET})
@RestController
@RequestMapping(value = "/api/v3/query" ) //, consumes = {"*/*"})//consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class ConexionPoolController{

	private static final Logger log = LoggerFactory.getLogger(ConexionPoolController.class);
			
	@Autowired
	private IExecuteService service;
	
	/**
	 * Método efectúa la consulta de la base EOBD.
	 * 
	 * @param query contiene la sentencia para la consulta EOBD
	 * @return el resultado de la consulta de la base EOBD
	 */
	@GetMapping(value ="/eo/consulta", consumes = MediaType.ALL_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<GenericResponse<String>>> execute(@RequestParam (value = "query", required = true) String query) {
		//log.info(SEPARADOR);
		//System.out.println("SE INICIALIZA EL ENDPOINT DE CONSULTAS A BD EO");
		//log.info("SE INICIALIZA EL ENDPOINT DE CONSULTAS A BD EO");
		//System.out.println(query);
		log.info(query);
		//log.info(SEPARADOR);
		return service.queryExeEOBDDES(query)
				/**
				 * Cuando obtenemos la respuesta se contruye la respuesta
				 */
				.map(q -> new ResponseEntity<>(new GenericResponse<>(200, "consulta correcta", q), HttpStatus.OK))
				/**
				 * Cuenta se completa la accion u ocurre un error
				 * se recibe la señal y se pintan los logs
				 */
				.doFinally(signalType -> {
					//log.info(SEPARADOR);
					//log.info(SEPARADOR);
					//System.out.println("SE FINALIZA EL ENDPOINT DE CONSULTAS A BD EO");
					//log.info("SE FINALIZA EL ENDPOINT DE CONSULTAS A BD EO");
					//log.info(SEPARADOR);
				});
	}
	
	
}
