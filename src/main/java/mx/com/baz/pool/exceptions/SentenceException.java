package mx.com.baz.pool.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @description
 * 
 * @project pool-conexiones
 * @author Mary C
 * @created Agosto 2022
 * @version 1.0
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class SentenceException extends Exception {
	private int codigoE;
	private String mensaje;
	private String fallo;
}