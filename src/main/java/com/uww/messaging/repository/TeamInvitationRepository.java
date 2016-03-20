package com.uww.messaging.repository;

import com.uww.messaging.model.TeamInvitation;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created on 3/20/16
 *
 * @author reinaldo
 */
public interface TeamInvitationRepository extends CrudRepository<TeamInvitation, Integer> {
	List<TeamInvitation> findByToUserId(int toUserId);
}
