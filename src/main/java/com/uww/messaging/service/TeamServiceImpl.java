package com.uww.messaging.service;

import com.google.common.base.Preconditions;
import com.uww.messaging.contract.TeamService;
import com.uww.messaging.display.TeamInvitationResponse;
import com.uww.messaging.display.UserDisplay;
import com.uww.messaging.model.team.Team;
import com.uww.messaging.model.team.TeamInvitation;
import com.uww.messaging.model.team.TeamMember;
import com.uww.messaging.model.team.TeamMessageChat;
import com.uww.messaging.model.user.User;
import com.uww.messaging.repository.team.TeamInvitationRepository;
import com.uww.messaging.repository.team.TeamMemberRepository;
import com.uww.messaging.repository.team.TeamMessageChatRepository;
import com.uww.messaging.repository.team.TeamRepository;
import com.uww.messaging.repository.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by horvste on 2/19/16.
 */
@Service
public class TeamServiceImpl implements TeamService {
	private TeamRepository teamRepository;

	private TeamMemberRepository teamMemberRepository;

	private TeamMessageChatRepository teamMessageChatRepository;

	private TeamInvitationRepository teamInvitationRepository;

	private UserRepository userRepository;

	@Autowired
	public TeamServiceImpl(TeamRepository teamRepository, TeamMemberRepository teamMemberRepository, TeamMessageChatRepository teamMessageChatRepository, TeamInvitationRepository teamInvitationRepository, UserRepository userRepository) {
		this.teamRepository = teamRepository;
		this.teamMemberRepository = teamMemberRepository;
		this.teamMessageChatRepository = teamMessageChatRepository;
		this.teamInvitationRepository = teamInvitationRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	@Override
	public void save(int creatorUserId, String teamName, String teamDescription) {
		Preconditions.checkNotNull(teamName);
		Preconditions.checkNotNull(teamDescription);
		Team team = new Team();
		team.setTeamName(teamName);
		team.setTeamDescription(teamDescription);
		team.setTeamLeader(creatorUserId);

		Date currentDate = new Date();
		team.setCreatedTime(currentDate);
		teamRepository.save(team);

		TeamMessageChat teamMessageChat = new TeamMessageChat(team.getTeamId(), null);
		teamMessageChatRepository.save(teamMessageChat);

		TeamMember teamMember = new TeamMember();
		teamMember.setTeamId(team.getTeamId());
		teamMember.setUserId(creatorUserId);
		teamMemberRepository.save(teamMember);
	}

	@Override
	public Team findTeamByTeamName(String teamName) {
		List<Team> byTeamName = teamRepository.findByTeamName(teamName);
		return byTeamName.size() == 0 ? null : byTeamName.get(0);
	}

	@Override
	public List<Team> findTeamsByUserId(int userId) {
		List<TeamMember> teamMemberByUserId = teamMemberRepository.findTeamMemberByUserId(userId);
		List<Integer> teamIds = new ArrayList<>();
		teamMemberByUserId.forEach(teamMem -> teamIds.add(teamMem.getTeamId())
		);
		return teamRepository.findByTeamIdIn(teamIds);
	}

	@Override
	public List<TeamInvitationResponse> findPendingInvitationsToUser(final int userId) {
		Iterable<TeamInvitation> teamInvitations = teamInvitationRepository.findByToUserId(userId);

		List<TeamInvitationResponse> responses = new ArrayList<>();

		teamInvitations.forEach(teamInvitation -> {
			if (!Objects.equals(teamInvitation.getStatus(), TeamInvitation.STATUS_PENDING)) {
				return;
			}
			TeamInvitationResponse response = new TeamInvitationResponse();

			User inviter = userRepository.findOne(teamInvitation.getFromUserId());
			Team toTeam = teamRepository.findOne(teamInvitation.getToTeamId());

			response.setTeamInvitationId(teamInvitation.getTeamInvitationId());

			response.setFromUserName(inviter.getFirstName() + " " + inviter.getLastName());

			response.setTeamName(toTeam.getTeamName());

			response.setInvitationTime(teamInvitation.getInvitationTime());

			response.setMessage(teamInvitation.getMessage());

			response.setStatus(teamInvitation.getStatus());

			responses.add(response);
		});

		return responses;
	}

	@Override
	public List<TeamInvitationResponse> findAllInvitationsToUser(final int toUserId) {

		Iterable<TeamInvitation> teamInvitations = teamInvitationRepository.findByToUserId(toUserId);

		List<TeamInvitationResponse> responses = new ArrayList<>();

		teamInvitations.forEach(teamInvitation -> {
			TeamInvitationResponse response = new TeamInvitationResponse();

			User inviter = userRepository.findOne(teamInvitation.getFromUserId());
			Team toTeam = teamRepository.findOne(teamInvitation.getToTeamId());

			response.setTeamInvitationId(teamInvitation.getTeamInvitationId());

			response.setFromUserName(inviter.getFirstName() + " " + inviter.getLastName());

			response.setTeamName(toTeam.getTeamName());

			response.setInvitationTime(teamInvitation.getInvitationTime());

			response.setMessage(teamInvitation.getMessage());

			response.setStatus(teamInvitation.getStatus());

			responses.add(response);
		});

		return responses;
	}


	@Transactional
	@Override
	public void addTeamMember(final int teamId, final int currentUserId, final int invitedUserId) {
		List<Team> teams = teamRepository.findByTeamIdIn(new ArrayList<Integer>() {{
			add(teamId);
		}});

		// makes sure there is one and only one team
		if (teams.size() > 1) {
			throw new RuntimeException("Size greater than one");
		} else if (teams.size() == 0) {
			throw new RuntimeException("No team with this id: " + teamId);
		}

		Team team = teams.get(0);
		System.out.println("team : " + team.getTeamName() + " desc: " + team.getTeamDescription());

		// makes sure the currentUser is in the team so it can invite
		if (!isMemberIsInTeam(teamId, currentUserId)) {
			throw new RuntimeException("Member is not in this team " + teamId);
		}

		TeamMember teamMember = new TeamMember(invitedUserId, teamId);
		teamMemberRepository.save(teamMember);
	}

	@Transactional
	@Override
	public void inviteMemberToTeam(final int teamId, final int fromUserId, final String invitedUsername, String message) {

		List<User> userList = userRepository.findByUsername(invitedUsername);

		if (userList == null || userList.size() == 0) {
			throw new UsernameNotFoundException(" User name is incorrect. Not possible to find " + invitedUsername);
		}

		int invitedUserId = userList.get(0).getUserId();

		List<TeamInvitation> toUserId = teamInvitationRepository.findByToUserIdAndToTeamId(invitedUserId, teamId);

		Team team = teamRepository.findOne(teamId);

		if (invitedUserId == team.getTeamLeader()) {
			throw new IllegalArgumentException("It is impossible to invite the leader to it`s own team.");
		} else if (fromUserId != team.getTeamLeader()) {
			throw new IllegalArgumentException("Sorry. It is not possible to invite if you are not the team leader.");
		} else if (toUserId.size() > 0 && toUserId.get(0).getStatus().equalsIgnoreCase(TeamInvitation.STATUS_ACCEPTED)) {
			throw new IllegalArgumentException("User already accepted the invitation and joined the team.");
		} else if (toUserId.size() > 0) {
			throw new IllegalArgumentException("User already has an invitation.");
		}

		TeamInvitation teamInvitation = new TeamInvitation();
		teamInvitation.setFromUserId(fromUserId);
		teamInvitation.setToUserId(invitedUserId);
		teamInvitation.setToTeamId(teamId);
		teamInvitation.setMessage(message);
		teamInvitation.setInvitationTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		teamInvitation.setStatus(TeamInvitation.STATUS_PENDING);

		teamInvitationRepository.save(teamInvitation);

	}

	@Transactional
	@Override
	public void acceptTeamInvitation(final int teamInvitationId) {
		TeamInvitation teamInvitation = teamInvitationRepository.findOne(teamInvitationId);

		if (teamInvitation == null) {
			throw new IllegalArgumentException("You cannot accept something you are not invited to participate.");
		}

		teamInvitation.setStatus(TeamInvitation.STATUS_ACCEPTED);

		teamInvitationRepository.save(teamInvitation);

		addTeamMember(teamInvitation.getToTeamId(), teamInvitation.getFromUserId(), teamInvitation.getToUserId());
	}

	@Transactional
	@Override
	public void rejectTeamInvitation(final int teamInvitationId) {
		TeamInvitation teamInvitation = teamInvitationRepository.findOne(teamInvitationId);

		teamInvitation.setStatus(TeamInvitation.STATUS_REJECTED);

		teamInvitationRepository.save(teamInvitation);
	}

	public boolean isMemberIsInTeam(int teamId, int userId) {

		List<TeamMember> teamMemberByUserId = teamMemberRepository.findTeamMemberByUserId(userId);

		for (TeamMember member : teamMemberByUserId) {
			if (member.getTeamId() == teamId) {
				return true;
			}
		}

		return false;
	}

	public Set<UserDisplay> findUsersLackingInvitationFromTeamOwner(int loggedInUserId, String username) {
		List<Team> teams = findTeamsByUserId(loggedInUserId);
		if (teams == null) { return new HashSet<>(); }
		Set<UserDisplay> userDisplays = new HashSet<>();
		teams.forEach(currentTeam -> {
			List<User> byUsernameStartingWith = userRepository.findByUsernameStartingWith(username);
			for (User in : byUsernameStartingWith) {
				boolean partOfTeam = isMemberIsInTeam(currentTeam.getTeamId(), in.getUserId());
				if (!partOfTeam) {
					userDisplays.add(new UserDisplay(
							in.getUserId(),
							in.getUsername()
					));
				}
			}
		});
		return userDisplays;
	}

}
