package com.paritosh.recipe.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.paritosh.recipe.backingBeans.UnitOfMeasureBackingBean;
import com.paritosh.recipe.converters.UnitOfMeasureToUnitOfMeasureBackingBean;
import com.paritosh.recipe.domain.UnitOfMeasure;
import com.paritosh.recipe.repositories.UnitOfMeasureRespository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


public class UnitOfMeasureServiceImplTest {
	@Mock
	UnitOfMeasureRespository unitOfMeasureRespository;
	
	UnitOfMeasureToUnitOfMeasureBackingBean unitOfMeasureToUnitOfMeasureBackingBean = new UnitOfMeasureToUnitOfMeasureBackingBean();
	UnitOfMeasureService unitOfMeasureService;
	
	@Test
	public void listAllUoms()  throws Exception{
		//given
		Set<UnitOfMeasure> unitOfMeasuresSet = new HashSet<>();
		UnitOfMeasure uom1 = new UnitOfMeasure();
		uom1.setId(1L);
		unitOfMeasuresSet.add(uom1);
		
		UnitOfMeasure uom2 = new UnitOfMeasure();
		uom2.setId(2L);
		unitOfMeasuresSet.add(uom2);
		
		when(unitOfMeasureRespository.findAll()).thenReturn(unitOfMeasuresSet);
		
		//when
		Set<UnitOfMeasureBackingBean> uomSet= unitOfMeasureService.listAllUoms();
		
		//then
		assertEquals(2, uomSet.size());
		verify(unitOfMeasureRespository,times(1)).findAll();
	}
	
	@Before
	public void  setup() throws Exception{
		MockitoAnnotations.initMocks(this);
		unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRespository, unitOfMeasureToUnitOfMeasureBackingBean);
		
	}
	
	
	
}
