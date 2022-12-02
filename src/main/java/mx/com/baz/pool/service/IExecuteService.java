package mx.com.baz.pool.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

/**
 * @description
 * 
 * @project pool-conexiones
 * @author Mary C
 * @created Agosto 2022
 * @version 1.0
 */

public interface IExecuteService {
	/**
	 * 
	 * @param query representa el script de consulta a ejecutar en base de datos
	 * @return el resultado de la consulta de la base de datos EOBDDES
	 */
	Mono<String> queryExeEOBDDES(String query);

}
