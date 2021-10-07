package com.etiya.reCapProject.business.abstracts;

import com.etiya.reCapProject.entities.requests.posRequest.PosRequest;

public interface CreatePosService {
	
	public boolean payment(PosRequest posRequest);

}
