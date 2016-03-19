package com.uww.messaging.repository;

import com.uww.messaging.model.UserInvitation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by horvste on 3/18/16.
 */
public interface UserInvitationRepository extends CrudRepository<UserInvitation, Integer> {
    List<UserInvitation> findByToUserId(int toUserId);

    List<UserInvitation> findByFromUserIdAndToUserId(int fromUserId, int toUserId);

    List<UserInvitation> findByToUserIdAndStatusEquals(int toUserId, String status);

    @Query("SELECT u FROM UserInvitation u WHERE (u.fromUserId=?1 OR u.toUserId=?1) AND u.status=?2")
    List<UserInvitation> findInvitationsByUserId(int userId, String status);
}
