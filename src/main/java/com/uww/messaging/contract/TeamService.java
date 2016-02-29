package com.uww.messaging.contract;

import com.uww.messaging.model.Team;

import java.util.List;

/**
 * Created by horvste on 2/19/16.
 */
public interface TeamService {
    void save(int creatorUserId, String teamName, String teamDescription);
    List<Team> findTeamsByUserId(int userId);
}
