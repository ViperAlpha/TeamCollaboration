package com.uww.messaging.contract;

import com.uww.messaging.display.TeamInvitationResponse;
import com.uww.messaging.display.UserDisplay;
import com.uww.messaging.model.team.Team;

import java.util.List;
import java.util.Set;

/**
 * Created by horvste on 2/19/16.
 */
public interface TeamService {
    void save(int creatorUserId, String teamName, String teamDescription);

    Team findTeamByTeamName(String teamName);

    List<Team> findTeamsByUserId(int userId);

    List<TeamInvitationResponse> findAllInvitationsToUser(final int userId);

    List<TeamInvitationResponse> findPendingInvitationsToUser(int userId);

    void addTeamMember(int teamId, int currentUserId, int invitedUserId);

    void inviteMemberToTeam(int teamId, int fromUserId, String invitedUsername, String message);

    void acceptTeamInvitation(int teamInvitationId);

    void rejectTeamInvitation(int teamInvitationId);

    Set<UserDisplay> findUsersLackingInvitationFromTeamOwner(int loggedInUserId, String username);

}
