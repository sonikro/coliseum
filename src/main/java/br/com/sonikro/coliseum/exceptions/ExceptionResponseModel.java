package br.com.sonikro.coliseum.exceptions;

public class ExceptionResponseModel {
	private Integer error_code;
	private String error_message;
	private String exception_class;
	
	public Integer getError_code() {
		return error_code;
	}
	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	public String getException_class() {
		return exception_class;
	}
	public void setException_class(String exception_class) {
		this.exception_class = exception_class;
	}
	
	
}
