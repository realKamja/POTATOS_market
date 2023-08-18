package com.poteto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.poteto.sevice.ProducterService;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ImageController {

	@Autowired
	private ProducterService producterService;

    // 밑의 유알엘에 이미지 자원을 위치시켜주는 함수
    @GetMapping("/image/{imageName}")
    public void showImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        //TODO ProducterImagePath에 들어가는 이미지 이름(imageName)으로 PorducterImage 데이터 가져오는 함수 만들어야 함
        // byte[] imageBytes = producterService.getImageByName(imageName);
        /**
         * import java.util.Base64;
         * String base64Image = "your_base64_image_string_here"; // Replace with actual value
         * byte[] 64Image);
         */
        // if (imageBytes != null) {
        //     respdecodedImageBytes = Base64.getDecoder().decode(baseonse.setContentType("image/jpeg");
        //     response.getOutputStream().write(imageBytes);
        // }
    }
}
