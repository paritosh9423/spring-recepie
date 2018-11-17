package com.paritosh.recipe.repositories;

import com.paritosh.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRespositoryTest {
    @Autowired
    UnitOfMeasureRespository unitOfMeasureRespository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findByUom() {
        Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRespository
                .findByUom("TeaSpoon");

        assertEquals("TeaSpoon",optionalUnitOfMeasure.get().getUom());

    }

    @Test
    public void findByUomCup() {
        Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRespository
                .findByUom("Cup");

        assertEquals("Cup",optionalUnitOfMeasure.get().getUom());

    }
}