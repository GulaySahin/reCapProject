package com.etiya.reCapProject;




//import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.etiya.reCapProject.core.utilities.results.ErrorDataResult;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.http.HttpStatus;
//import com.cloudinary.*;
//import com.cloudinary.utils.ObjectUtils;

@SpringBootApplication
@RestControllerAdvice
@EnableSwagger2
public class ReCapProjectApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ReCapProjectApplication.class, args);
		
		/*
		 * Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap( "cloud_name",
		 * "dpj4shyhs", "api_key", "965459993967681", "api_secret",
		 * "a-l-rkOUM70If_0Reff3dVdKpS4", "secure", true));
		 * 
		 * 
		 * File file = new File("my_image.jpg"); Map uploadResult =
		 * cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		 */
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
	
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	    return new ModelMapper();
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exception) {

		Map<String, String> validationErrors = new HashMap<String, String>();

		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		ErrorDataResult<Object> error = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
		return error;
	}

}
