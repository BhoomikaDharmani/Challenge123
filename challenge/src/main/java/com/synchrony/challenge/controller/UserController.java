package com.synchrony.challenge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.synchrony.challenge.model.ImageMapping;
import com.synchrony.challenge.model.ImgurUploadResponse;
import com.synchrony.challenge.model.UserInfo;
import com.synchrony.challenge.repository.ImageMappingRepository;
import com.synchrony.challenge.service.ImgurService;
import com.synchrony.challenge.service.UserService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/v1/user",produces = "application/json")
@Slf4j
public class UserController {

	private final UserService userService;
	private final ImgurService imgurService;
	private final ImageMappingRepository imageMappingRepository;
	
	public UserController(UserService userService, ImgurService imgurService, ImageMappingRepository imageMappingRepository) {
		this.userService = userService;
		this.imgurService = imgurService;
		this.imageMappingRepository = imageMappingRepository;
	}
	
	@PostMapping
	public UserInfo createUser(@RequestBody UserInfo user) {
		System.out.println("inside create user");
		
		return userService.createUser(user);
	}
	
	@GetMapping
	public List<UserInfo> getUser() {
		return userService.getUsers();
	}
	
	@GetMapping("/allImages/{userName}/{password}")
	public ResponseEntity<List<ImageMapping>> getUserImages(@PathVariable String userName,@PathVariable String password){
		boolean verifyUser = userService.verifyUser(userName, password);
		if(!verifyUser) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		
		UserInfo userInfo = userService.getUserByName(userName);
		Integer userId = userInfo.getId();
		
		return ResponseEntity.ok(imageMappingRepository.findByUserId(userId));

	}
	
	@PostMapping("/imageUpload/{userName}/{password}")
	public ResponseEntity<ImgurUploadResponse> uploadImage(@PathVariable String userName, @PathVariable String password, @RequestBody MultipartFile imageUploadModel){
		
		boolean verifyUser = userService.verifyUser(userName, password);
		if(!verifyUser) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		
		try {
			// byte[] imageBytes = image.getBytes();
			ImgurUploadResponse imageResponse = imgurService.uploadIMAGE(imageUploadModel);
			saveUserImageInfo(userName, imageResponse);
			return ResponseEntity.ok(imageResponse);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the image");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
	}

	/**
	 * @param imageUploadModel
	 * @param imageResponse
	 */
	private void saveUserImageInfo(String userName, ImgurUploadResponse imageResponse) {
		String imageId = imageResponse.getData().getId();
		String imageDeleteHash = imageResponse.getData().getDeletehash();
		UserInfo userInfo = userService.getUserByName(userName);
		ImageMapping imageMapping = new ImageMapping();
		imageMapping.setUserId(userInfo.getId());
		imageMapping.setImageId(imageId);
		imageMapping.setImageDeleteHash(imageDeleteHash);
		imageMappingRepository.save(imageMapping);
	}
	
	@GetMapping(value = "/getImage/{imageHash}",produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<ImgurUploadResponse> getImage(@PathVariable   String imageHash){
		ImgurUploadResponse imageByte = imgurService.viewImage(imageHash);
	      return ResponseEntity.ok(imageByte);
	      
	}
	
	@DeleteMapping("/deleteImage/{imageDeleteHash}")
	@Transactional
	public ResponseEntity<Void> deleteImage(@PathVariable  String imageDeleteHash,@RequestParam  String userName,@RequestParam  String password) {
		boolean verifyUser = userService.verifyUser(userName, password);
		if(!verifyUser) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		try {
			imageMappingRepository.deleteByImageDeleteHash(imageDeleteHash);
			imgurService.deleteImage(imageDeleteHash);
			return ResponseEntity.ok().build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
}
