package com.paritosh.recipe.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.paritosh.recipe.backingBeans.RecipeBackingBean;
import com.paritosh.recipe.service.ImageService;
import com.paritosh.recipe.service.RecipeService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ImageControllerTest {
	
	@Mock
	ImageService imageService;
	
	@Mock
	RecipeService recipeService;
	
	ImageController imageController;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		imageController = new ImageController(imageService, recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
		
	}
	 @Test
	    public void getImageForm() throws Exception {
	        //given
	       RecipeBackingBean recipeBackingBean = new RecipeBackingBean();
	       recipeBackingBean.setId(1L);

	        when(recipeService.findRecipeBackingBeanByID(Mockito.anyLong())).thenReturn(recipeBackingBean);

	        //when
	        mockMvc.perform(get("/recipe/1/image"))
	                .andExpect(status().isOk())
	                .andExpect(model().attributeExists("recipe"));

	        verify(recipeService, Mockito.times(1)).findRecipeBackingBeanByID(Mockito.anyLong());

	    }
	 
	 
	    @Test
	    public void handleImagePost() throws Exception {
	        MockMultipartFile multipartFile =
	                new MockMultipartFile("imagefile", "testing.txt", "text/plain",
	                        "Spring Framework Guru".getBytes());

	        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
	                .andExpect(status().is3xxRedirection())
	                .andExpect(header().string("Location", "/recipe/show/1"));

	        verify(imageService, times(1)).saveImageFile(anyLong(), any());
	    }
	    
	    
	    
	    @Test
	    public void renderImageFromDB() throws Exception {

	        //given
	        RecipeBackingBean recipeBackingBean = new RecipeBackingBean();
	        recipeBackingBean.setId(1L);

	        String s = "fake image text";
	        Byte[] bytesBoxed = new Byte[s.getBytes().length];

	        int i = 0;

	        for (byte primByte : s.getBytes()){
	            bytesBoxed[i++] = primByte;
	        }

	        recipeBackingBean.setImage(bytesBoxed);

	        when(recipeService.findRecipeBackingBeanByID(anyLong())).thenReturn(recipeBackingBean);

	        //when
	        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
	                .andExpect(status().isOk())
	                .andReturn().getResponse();

	        byte[] reponseBytes = response.getContentAsByteArray();

	        assertEquals(s.getBytes().length, reponseBytes.length);
	    }

}
