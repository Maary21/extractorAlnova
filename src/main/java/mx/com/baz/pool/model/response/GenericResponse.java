package mx.com.baz.pool.model.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonInclude;

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
public class GenericResponse <T>{
	public int code = 200;
	public String message = "";
	public List<String> errors = new ArrayList<>();
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public T response;
	
	public GenericResponse() {}
	
	public GenericResponse(int code, String message) {
		this.code = code;
		this.message = message;
	}
	public GenericResponse(int code, String message, List<String> errors) {
		//new GenericResponse<Object>(code, message);
		this.code = code;
		this.message = message;
		this.errors = errors;
	}
	public GenericResponse(int code, String message, T response) {
		//new GenericResponse<Object>(code, message);
		this.code = code;
		this.message = message;
		this.response = response;
	}
	public GenericResponse(int code, String message, List<String> errors, T response) {
		//new GenericResponse<Object>(code, message, errors);
		this.code = code;
		this.message = message;
		this.errors = errors;
		this.response = response;
	}
	
}
