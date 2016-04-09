package com.uww.messaging.controller.user;

import com.uww.messaging.model.UserUploadedFile;
import com.uww.messaging.repository.UserUploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * Created by horvste on 3/19/16.
 */
@Controller
@RequestMapping(value = "/user/download")
public class UserDownloadController {
    @Autowired
    private UserUploadedFileRepository userUploadedFileRepository;

    @RequestMapping(value = "/file", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public FileSystemResource returnFileToDownload(@RequestParam("fileName") String fileName, HttpServletResponse httpServletResponse) {
        List<UserUploadedFile> userUploadedFiles = userUploadedFileRepository.findByFileName(fileName);
        if (userUploadedFiles.size() == 0)
            return null;

        UserUploadedFile userUploadedFile = userUploadedFiles.get(0);
        FileSystemResource fileSystemResource = new FileSystemResource(
                new File(userUploadedFile.getFilePath())
        );
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + userUploadedFile.getFileName());
        return fileSystemResource;
    }
}
