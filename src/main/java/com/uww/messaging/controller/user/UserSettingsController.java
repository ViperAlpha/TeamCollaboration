package com.uww.messaging.controller.user;

import com.uww.messaging.contract.UserService;
import com.uww.messaging.model.user.User;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.print.attribute.standard.Media;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import static org.springframework.http.MediaType.*;

/**
 * Created by horvste on 4/22/16.
 */
@RequestMapping(value = "/user/settings")
@Controller
public class UserSettingsController {
    private UserService userService;
    private ServletContext servletContext;

    @Autowired
    public UserSettingsController(UserService userService, ServletContext servletContext) {
        this.userService = userService;
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "/avatarUpload", method = RequestMethod.POST)
    public ResponseEntity avatarUpload(Authentication authentication, @RequestParam("fileUpload") MultipartFile multipartFile) {
        userService.setAvatar(userService.getLoggedInUserId(authentication), multipartFile);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/avatar", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> avatar(Authentication auth) {
        User loggedInUser = userService.getLoggedInUser(auth);
        String avatarImage = loggedInUser.getAvatarPicture();
        return getResponseEntityForImage(loggedInUser.getUserId(), avatarImage);
    }


    @RequestMapping(value = "/avatar", method = RequestMethod.GET, params = "userId")
    @ResponseBody
    public ResponseEntity<byte[]> avatarForAnotherUser(@RequestParam int userId) {
        User user = userService.findUserById(userId);
        return getResponseEntityForImage(user.getUserId(), user.getAvatarPicture());
    }


    private static ResponseEntity<byte[]> getResponseEntityForImage(int userId, String avatarImage) {
        if (avatarImage == null)
            return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);

        File avatarImageFile = new File(avatarImage);
        if (!avatarImageFile.exists())
            throw new RuntimeException("No Avatar Image Exists With User: " + userId);
        try {
            byte[] avatarImageBytes = Files.readAllBytes(avatarImageFile.toPath());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentLength(avatarImageBytes.length);
            httpHeaders.setContentType(MediaType.IMAGE_JPEG);
            httpHeaders.set("Content-Disposition", "inline; filename=" + avatarImageFile.getName());
            return new ResponseEntity<byte[]>(avatarImageBytes, httpHeaders, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}