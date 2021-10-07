package com.etiya.reCapProject.core.utilities.results;

public class SuccessDataResult<T> extends DataResult<T> {
	
	public SuccessDataResult(T data) {
		super(true,data);
	}
	
	public SuccessDataResult(T data,String message) {
		super(true,data,message);
		
	}
	
	public SuccessDataResult(String message) {
		super(true,null,message);
	}
	
	
	public SuccessDataResult() {
		super(true,null);
	}
}
