package com.infogain.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Response<T> {

	T data;
	String error;

	/**
	 * 
	 */
	public Response() {
	}

	/**
	 * @param error
	 */
	public Response(String error) {
		this.error = error;
	}

	/**
	 * @param data
	 */
	public Response(T data) {
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @param data
	 * @param error
	 */
	public Response(T data, String error) {
		this.data = data;
		this.error = error;
	}

}
