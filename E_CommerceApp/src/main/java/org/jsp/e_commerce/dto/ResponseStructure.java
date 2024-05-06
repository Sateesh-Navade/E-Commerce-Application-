package org.jsp.e_commerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T> {
	private String message;
	private T data;
	private int statusCode;
}
