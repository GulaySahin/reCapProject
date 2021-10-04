package com.etiya.reCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.reCapProject.business.abstracts.ColorService;
import com.etiya.reCapProject.business.constants.Messages;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.ColorDao;
import com.etiya.reCapProject.entities.concretes.Color;
import com.etiya.reCapProject.entities.requests.colorRequest.AddColorRequest;
import com.etiya.reCapProject.entities.requests.colorRequest.DeleteColorRequest;
import com.etiya.reCapProject.entities.requests.colorRequest.UpdateColorRequest;

@Service
public class ColorManager implements ColorService{

	private ColorDao colorDao;
	
	@Autowired
	public ColorManager(ColorDao colorDao) {
		super();
		this.colorDao = colorDao;
	}

	@Override
	public DataResult<List<Color>> getByColorId(int colorId) {
		
		List<Color> colors= this.colorDao.getByColorId(colorId);
		return new SuccessDataResult<List<Color>>(colors,Messages.Listed);
	}

	@Override
	public DataResult<List<Color>> getAll() {
		List<Color> colors= this.colorDao.findAll();
		return new SuccessDataResult<List<Color>>(colors,Messages.List);
	}

	@Override
	public Result add(AddColorRequest addColorRequest) {
		
		Color color = new Color();
		color.setColorName(addColorRequest.getColorName());
	
		this.colorDao.save(color);
		return new SuccessResult(Messages.Add);
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		
		Color color = new Color();
		color.setColorId(updateColorRequest.getColorId());
		color.setColorName(updateColorRequest.getColorName());
		
		this.colorDao.save(color);
		return new SuccessResult(Messages.Update);

		
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		
		Color color = new Color();
		color.setColorId(deleteColorRequest.getColorId());
		
		this.colorDao.delete(color);
		return new SuccessResult(Messages.Delete);

	}

}
