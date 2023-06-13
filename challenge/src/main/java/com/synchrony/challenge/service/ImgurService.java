package com.synchrony.challenge.service;

import java.io.File;
import java.io.IOException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.synchrony.challenge.model.ImgurUploadResponse;

@Service
public class ImgurService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ImgurService.class);

	@Value("$(imgur.clientId")
	private String clientId;
	@Value("$(imgur.clientSecret")
	private String clientSecret;
	
	private static String PATH = "https://api.imgur.com/";
	
	
	private final RestTemplate restTemplate;
	
	
	public ImgurService( RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public ImgurUploadResponse uploadIMAGE(MultipartFile imageBytes) throws IOException {
		logger.info("inside ImgurService.uploadImage");

		try {
			HttpHeaders headers = new HttpHeaders();
			// headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			headers.set("Authorization", "Client-ID"+clientId);
			//MultipartBodyBuilder builder = new MultipartBodyBuilder();
			//builder.part("image", new ByteArrayResource(imageBytes.getBytes()));
			
			File tempFile = null;
		    try {
		        String extension = "." + getFileExtention(imageBytes.getOriginalFilename());
		        tempFile = File.createTempFile("temp", extension);
		        imageBytes.transferTo(tempFile);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("image",  new FileSystemResource(tempFile));
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body,headers);
			ResponseEntity<ImgurUploadResponse> responseEntity = restTemplate.exchange(PATH + "3/upload", HttpMethod.POST,requestEntity,ImgurUploadResponse.class);
			if(responseEntity.getStatusCode() == HttpStatus.OK) {
				ImgurUploadResponse uploadResponse = responseEntity.getBody();
				if(uploadResponse != null && uploadResponse.getData() != null) {
					//return uploadResponse.getData().getId();
					return uploadResponse;
				}
			}
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String getFileExtention(String originalFilename) {
		logger.info("inside ImgurService.getFileExtention");
		return originalFilename.split(".").toString();
	}

	//TODO For deleteImage and getImage an access token is required, which Imgur.com is
	//not allowing to generation (getting 429 status code). So if that is available then 
	//it can be added in header and this api will work fine.
	public void deleteImage(String imageDeleteHash) {
		logger.info("inside ImgurService.deleteImage");

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Client-ID"+clientId);
			HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
			restTemplate.exchange(PATH + "3/image/{imageDeleteHash}", HttpMethod.DELETE, requestEntity, Void.class,imageDeleteHash);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public ImgurUploadResponse viewImage(String imageId) {
		logger.info("inside ImgurService.viewImage");

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Client-ID"+clientId);
		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<ImgurUploadResponse> responseEntity = restTemplate.exchange(PATH + "3/image/{imageId}", HttpMethod.GET,requestEntity,ImgurUploadResponse.class,imageId);

		return responseEntity.getBody();
	}
}
