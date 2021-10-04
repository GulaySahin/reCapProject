package com.etiya.reCapProject.core.utilities.results;

public class ErrorDataResult<T> extends DataResult<T> {
	
	public ErrorDataResult(T data) {
		super(false,data);
	}
	
	public ErrorDataResult(T data,String message) {
		super(false,null,message);
		
	}
	
	public ErrorDataResult(String message) {
		super(false,null,message);
	}
	
	
	public ErrorDataResult() {
		super(false,null);
	}
}
