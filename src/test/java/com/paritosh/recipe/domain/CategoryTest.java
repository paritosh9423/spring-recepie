package com.paritosh.recipe.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category categoryInTest;

    @Before
    public void setUp(){
        categoryInTest = new Category();


    }

    @Test
    public void getId() {

        categoryInTest.setId(1L);
        Long testId = categoryInTest.getId();
        assertEquals(testId,categoryInTest.getId());

    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipeSet() {
    }
}