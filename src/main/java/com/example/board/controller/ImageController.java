package com.example.board.controller;

import com.example.board.entity.UploadFile;
import com.example.board.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
public class ImageController {

	@Autowired ImageService imageService;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@PostMapping("image")
	public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile file) {
		try {
			var contentType = file.getContentType();
			UploadFile uploadFile = imageService.store(file);
			if(contentType!= null && contentType.startsWith("video/"))
				return ResponseEntity.ok().body("/video/" + uploadFile.getId());
			else return ResponseEntity.ok().body("/image/" + uploadFile.getId());
		} catch(Exception e) {
			log.info("upload error = {}", e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	// 공통 파일 제공 메서드
	private ResponseEntity<?> serveMedia(Long fileId, String mediaType) {
		try {
			// 파일 로드
			UploadFile uploadFile = imageService.load(fileId);
			Resource resource = resourceLoader.getResource("file:" + uploadFile.getFilePath());

			// MIME 타입 설정
			String contentType;
			if ("video".equals(mediaType)) {
				contentType = "video/mp4";  // 필요한 경우 더 많은 포맷 추가
			} else {
				contentType = "image/jpeg";  // 이미지의 경우 기본적으로 jpeg로 설정
			}

			return ResponseEntity.ok()
					.header("Content-Type", contentType)
					.body(resource);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	// 이미지 제공
	@GetMapping("image/{fileId}")
	public ResponseEntity<?> serveImage(@PathVariable(name = "fileId") Long fileId) {
		return serveMedia(fileId, "image");
	}

	// 영상 제공
	@GetMapping("video/{fileId}")
	public ResponseEntity<?> serveVideo(@PathVariable(name = "fileId") Long fileId) {
		return serveMedia(fileId, "video");
	}

//	@GetMapping("image/{fileId}")
//	public ResponseEntity<?> serveFile(@PathVariable(name = "fileId") Long fileId){
//		try {
//			UploadFile uploadFile = imageService.load(fileId);
//			Resource resource = resourceLoader.getResource("file:" + uploadFile.getFilePath());
//			return ResponseEntity.ok().body(resource);
//		} catch(Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.badRequest().build();
//		}
//
//	}


}
