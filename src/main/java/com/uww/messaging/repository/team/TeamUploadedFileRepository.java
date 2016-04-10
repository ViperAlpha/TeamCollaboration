package com.uww.messaging.repository.team;

import com.uww.messaging.model.team.TeamUploadedFile;
import com.uww.messaging.model.user.UserUploadedFile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by horvste on 4/9/16.
 */
public interface TeamUploadedFileRepository extends CrudRepository<TeamUploadedFile, Integer> {
    List<TeamUploadedFile> findByTeamMessageChatId(int teamMessageChatId);

    List<TeamUploadedFile> findByFileName(String fileName);
}
