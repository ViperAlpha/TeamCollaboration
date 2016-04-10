package com.uww.messaging.controller.team;

import com.uww.messaging.model.team.TeamUploadedFile;
import com.uww.messaging.model.user.UserUploadedFile;
import com.uww.messaging.repository.team.TeamUploadedFileRepository;
import com.uww.messaging.repository.user.UserUploadedFileRepository;
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
 * Created by horvste on 4/9/16.
 */
@Controller
@RequestMapping(value = "/user/team/download")
public class TeamDownloadController {
    @Autowired
    private TeamUploadedFileRepository teamUploadedFileRepository;

    @RequestMapping(value = "/file", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public FileSystemResource returnFileToDownload(@RequestParam("fileName") String fileName, HttpServletResponse httpServletResponse) {
        List<TeamUploadedFile> teamUploadedFiles = teamUploadedFileRepository.findByFileName(fileName);

        if (teamUploadedFiles.size() == 0)
            return null;

        TeamUploadedFile teamUploadedFile = teamUploadedFiles.get(0);
        FileSystemResource fileSystemResource = new FileSystemResource(
                new File(teamUploadedFile.getFilePath())
        );
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + teamUploadedFile.getFileName());
        return fileSystemResource;
    }
}
