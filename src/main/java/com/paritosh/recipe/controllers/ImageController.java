package com.paritosh.recipe.controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.paritosh.recipe.backingBeans.RecipeBackingBean;
import com.paritosh.recipe.service.ImageService;
import com.paritosh.recipe.service.RecipeService;

@Controller
public class ImageController {

	private final ImageService imageService;
	private final RecipeService recipeService;
	public ImageController(ImageService imageService, RecipeService recipeService) {
		super();
		this.imageService = imageService;
		this.recipeService = recipeService;
	}
	@GetMapping("recipe/{id}/image")
	public String showUploadForm(@PathVariable String id , Model model) {
		
		model.addAttribute("recipe" , recipeService.findRecipeBackingBeanByID(Long.valueOf(id)));
		return "recipe/imageuploadform";
		
	}
	@PostMapping("recipe/{id}/image")
	public String handleImagePost(@PathVariable String id , @RequestParam("imagefile") MultipartFile multipartFile) {
		imageService.saveImageFile(Long.valueOf(id), multipartFile);
		return "redirect:/recipe/show/"+id;
	}
	@GetMapping("recipe/{id}/recipeimage")
	public void RenderImageFromDB(@PathVariable String id , HttpServletResponse httpServletResponse) throws Exception{
		RecipeBackingBean recipeBackingBean = recipeService.findRecipeBackingBeanByID(Long.valueOf(id));
		
		if(recipeBackingBean.getImage()!=null) {
			byte[] byteArray = new byte[recipeBackingBean.getImage().length];
			int i=0;
			for(Byte wrappedByte : recipeBackingBean.getImage()) {
				byteArray[i++] = wrappedByte;
			}
			httpServletResponse.setContentType("image/jpeg");
			InputStream inputStream = new ByteArrayInputStream(byteArray);
			IOUtils.copy(inputStream, httpServletResponse.getOutputStream());
		}
	}
	
}
