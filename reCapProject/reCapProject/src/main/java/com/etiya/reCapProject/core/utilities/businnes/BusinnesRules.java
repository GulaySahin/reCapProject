package com.etiya.reCapProject.core.utilities.businnes;

import com.etiya.reCapProject.core.utilities.results.Result;

public class BusinnesRules {
	
	
	public static Result run (Result... logics)
	{
		for (Result  logic : logics)  {
			
			if(!logic.isSuccess() )
			{
				return logic;
			}
			
		}
		
		return null;
	}
}
