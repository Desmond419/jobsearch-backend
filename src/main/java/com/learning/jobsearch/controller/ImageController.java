package com.learning.jobsearch.controller;

import com.learning.jobsearch.service.UserService;
import com.learning.jobsearch.utils.GenUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    private UserService userService;

    /** Limit of image size (10MB) */
    public static final int IMAGE_MAX_SIZE = 10 * 1024 * 1024;
    /** File types of image allowed to be uploaded */
    public static final List<String> IMAGE_TYPES = new ArrayList<String>();

    static {
        IMAGE_TYPES.add("image/jpeg");
        IMAGE_TYPES.add("image/png");
        IMAGE_TYPES.add("image/bmp");
        IMAGE_TYPES.add("image/gif");
    }

    @PostMapping("/upload/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
                                              @RequestParam("userId") String userId, HttpSession session) {
        // Check whether image is empty
        if (file.isEmpty()) {
            return new ResponseEntity<>("Image file is not allowed to be empty", HttpStatus.NO_CONTENT);
        }

        // Check whether image size exceed limit
        if (file.getSize() > IMAGE_MAX_SIZE) {
            return new ResponseEntity<>("Image size " + (IMAGE_MAX_SIZE / 1024) + " is not allowed", HttpStatus.NO_CONTENT);
        }

        // Check image file type
        String contentType = file.getContentType();
        if (!IMAGE_TYPES.contains(contentType)) {
            return new ResponseEntity<>("Image file types not supported, only " + IMAGE_TYPES + " are allowed",
                    HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        String parent = session.getServletContext().getRealPath("upload");
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Store image file name
        String suffix = "";
        // Get file name
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = new GenUUID().getUUID() + suffix;

        // Create a file to saved image file
        File dest = new File(dir, filename);
        // Saving image file
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>("Upload failed, the file has been moved or deleted", HttpStatus.NO_CONTENT);
        } catch (IOException e) {
            return new ResponseEntity<>("Error when uploading files, please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String image = "/upload/" + filename;
        userService.uploadImageByUserId(userId, image);

        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}
