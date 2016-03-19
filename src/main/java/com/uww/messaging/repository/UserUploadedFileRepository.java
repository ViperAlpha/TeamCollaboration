package com.uww.messaging.repository;

import com.uww.messaging.model.UserUploadedFile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by horvste on 3/19/16.
 */
public interface UserUploadedFileRepository extends CrudRepository<UserUploadedFile, Integer> {
    List<UserUploadedFile> findByFileName(String fileName);

    List<UserUploadedFile> findByChatId(int chatId);
}
