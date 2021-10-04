package com.etiya.reCapProject.business.abstracts;

import java.util.List;

import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.concretes.RentAl;
import com.etiya.reCapProject.entities.requests.rentalRequest.AddRentAlRequest;
import com.etiya.reCapProject.entities.requests.rentalRequest.DeleteRentAlRequest;
import com.etiya.reCapProject.entities.requests.rentalRequest.UpdateRentAlRequest;

public interface RentAlService {
	
	Result addRentalForIndividualCustomer(AddRentAlRequest addRentAlRequest);

	Result updateRentalForIndividualCustomer(UpdateRentAlRequest updateRentAlRequest);
	
	Result addRentalForCorporateCustomer(AddRentAlRequest addRentAlRequest);
	
	Result updateRentalForCorporateCustomer(UpdateRentAlRequest updateRentAlRequest);
	
	Result delete(DeleteRentAlRequest deleteRentAlRequest);
	
	DataResult<List<RentAl>> getAll();
	
	DataResult<RentAl> getById(int id);

}
