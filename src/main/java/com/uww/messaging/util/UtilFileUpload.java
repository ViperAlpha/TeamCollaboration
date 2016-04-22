package com.uww.messaging.util;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * Created by horvste on 4/22/16.
 */
public class UtilFileUpload {
    public static File transferFileToDirWithRandomName(String saveBaseDirectory, MultipartFile multipartFile) throws IOException {
        Preconditions.checkNotNull(saveBaseDirectory);
        Preconditions.checkNotNull(multipartFile);

        String randomAlphaNumeric = RandomStringUtils.randomAlphabetic(8);
        String fileSavePath = saveBaseDirectory + "/" + randomAlphaNumeric;
        File file = new File(fileSavePath);
        if (file.exists()) {
            randomAlphaNumeric = RandomStringUtils.randomAlphabetic(8);
            fileSavePath = saveBaseDirectory + "/" + randomAlphaNumeric;
            file = new File(fileSavePath);
            if (file.exists()) {
                String randomlyGeneratedFileNameErrorMessage = MessageFormat.format("Attempted to generate random name: {0} for file {1} but {0} exists", randomAlphaNumeric, multipartFile.getOriginalFilename());
                throw new RuntimeException(randomlyGeneratedFileNameErrorMessage);
            }
        }
        multipartFile.transferTo(file);
        return file;
    }
}
