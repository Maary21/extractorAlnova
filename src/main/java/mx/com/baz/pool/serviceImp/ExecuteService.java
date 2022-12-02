package mx.com.baz.pool.serviceImp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.baz.pool.dao.ExeSentenceEOBDDESDAO;
import mx.com.baz.pool.service.IExecuteService;
import reactor.core.publisher.Mono;

/**
 * @description
 * 
 * @project pool-conexiones
 * @author Mary C
 * @created Agosto 2022
 * @version 1.0
 */

@Service
public class ExecuteService implements IExecuteService {
	/**
	 * Inyecta un {@link ExeSentenceEOBDSDAO} bean
	 */
	@Autowired
	private ExeSentenceEOBDDESDAO daoEOBDDES;
	
	/**
	 * @return el resultado de la consulta de parametros
	 */
	
	@Override
	public Mono<String> queryExeEOBDDES(String query) {
		return daoEOBDDES.getEOBDDES(query);
	}

}
