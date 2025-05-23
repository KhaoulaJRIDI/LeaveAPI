package com.example.leaveapi.dto;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

public class StandardErrorDTO {

	private Integer status;
	private String error;
	private String message;
	private String path;
	
	public StandardErrorDTO(HttpStatus status, Throwable ex, HttpServletRequest request) {
		this.status = status.value();
		this.error = ex.getClass().getSimpleName();
		this.message = ex.getMessage();
		this.path = request.getRequestURI();
	}
	
	public Integer getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getPath() {
		return path;
	}

}
