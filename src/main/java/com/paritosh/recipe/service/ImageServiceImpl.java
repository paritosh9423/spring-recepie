package com.paritosh.recipe.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ImageServiceImpl implements ImageService{
	
	@Override
	public void saveImageFile(Long recipeId, MultipartFile file) {
		// TODO Auto-generated method stub
		log.debug("Received a file");
	}

}
