	package com.paritosh.recipe.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.paritosh.recipe.backingBeans.UnitOfMeasureBackingBean;
import com.paritosh.recipe.converters.UnitOfMeasureToUnitOfMeasureBackingBean;
import com.paritosh.recipe.repositories.UnitOfMeasureRespository;
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{
	
	
	private final UnitOfMeasureRespository unitOfMeasureRespository;
	private final UnitOfMeasureToUnitOfMeasureBackingBean unitOfMeasureToUnitOfMeasureBackingBean;
	

	public UnitOfMeasureServiceImpl(UnitOfMeasureRespository unitOfMeasureRespository,
			UnitOfMeasureToUnitOfMeasureBackingBean unitOfMeasureToUnitOfMeasureBackingBean) {
		super();
		this.unitOfMeasureRespository = unitOfMeasureRespository;
		this.unitOfMeasureToUnitOfMeasureBackingBean = unitOfMeasureToUnitOfMeasureBackingBean;
	}


	@Override
	public Set<UnitOfMeasureBackingBean> listAllUoms() {
		
		return StreamSupport.stream(unitOfMeasureRespository.findAll()
									.spliterator(),false)
									.map(unitOfMeasureToUnitOfMeasureBackingBean::convert)
									.collect(Collectors.toSet());
	}
	
	

}
