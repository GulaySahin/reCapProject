package com.etiya.reCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.abstracts.ColorService;
import com.etiya.reCapProject.business.constants.messages.ColorMessages;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.ColorDao;
import com.etiya.reCapProject.entities.concretes.Color;
import com.etiya.reCapProject.entities.dtos.ColorDto;
import com.etiya.reCapProject.entities.requests.colorRequest.AddColorRequest;
import com.etiya.reCapProject.entities.requests.colorRequest.DeleteColorRequest;
import com.etiya.reCapProject.entities.requests.colorRequest.UpdateColorRequest;

@Service
public class ColorManager implements ColorService{

	private ColorDao colorDao;
	private ModelMapper modelMapper;
	
	@Autowired
	public ColorManager(ColorDao colorDao,ModelMapper modelMapper) {
		super();
		this.colorDao = colorDao;
		this.modelMapper=modelMapper;
	}

	@Override
	public DataResult<ColorDto> getByColorId(int colorId) {
		
		Color color= this.colorDao.getByColorId(colorId);
		ColorDto  colorDto= modelMapper.map(color, ColorDto.class);
		return new SuccessDataResult<ColorDto>(colorDto,ColorMessages.GetById);
	}

	@Override
	public DataResult<List<ColorDto>> getAll() {
		List<Color> colors= this.colorDao.findAll();
		List<ColorDto> colorDtos= colors.stream().map(color->modelMapper.map(color, ColorDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ColorDto>>(colorDtos,ColorMessages.GetAll);
	}

	@Override
	public Result add(AddColorRequest addColorRequest) {
		
		Color color = new Color();
		color.setColorName(addColorRequest.getColorName());
	
		this.colorDao.save(color);
		return new SuccessResult(ColorMessages.Add);
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		
		Color color = new Color();
		color.setColorId(updateColorRequest.getColorId());
		color.setColorName(updateColorRequest.getColorName());
		
		this.colorDao.save(color);
		return new SuccessResult(ColorMessages.Update);

		
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		
		Color color = new Color();
		color.setColorId(deleteColorRequest.getColorId());
		
		this.colorDao.delete(color);
		return new SuccessResult(ColorMessages.Delete);

	}

}
