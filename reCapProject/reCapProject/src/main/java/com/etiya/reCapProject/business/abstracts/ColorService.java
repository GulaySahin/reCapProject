package com.etiya.reCapProject.business.abstracts;

import java.util.List;

import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.entities.dtos.ColorDto;
import com.etiya.reCapProject.entities.requests.colorRequest.AddColorRequest;
import com.etiya.reCapProject.entities.requests.colorRequest.DeleteColorRequest;
import com.etiya.reCapProject.entities.requests.colorRequest.UpdateColorRequest;

public interface ColorService {
	
	DataResult<ColorDto> getByColorId(int colorId);

	DataResult<List<ColorDto>>  getAll();

	Result add(AddColorRequest addColorRequest);

	Result update(UpdateColorRequest updateColorRequest);

	Result delete(DeleteColorRequest deleteColorRequest);
}
