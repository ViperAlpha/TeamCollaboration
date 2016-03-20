package com.uww.messaging.service;

import com.google.common.base.Preconditions;
import com.uww.messaging.contract.TeamService;
import com.uww.messaging.model.Team;
import com.uww.messaging.model.TeamMember;
import com.uww.messaging.model.TeamMessageChat;
import com.uww.messaging.repository.TeamMemberRepository;
import com.uww.messaging.repository.TeamMessageChatRepository;
import com.uww.messaging.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by horvste on 2/19/16.
 */
@Service
public class TeamServiceImpl implements TeamService {
	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private TeamMemberRepository teamMemberRepository;

	@Autowired
	private TeamMessageChatRepository teamMessageChatRepository;


	@Transactional
	@Override
	public void save(int creatorUserId, String teamName, String teamDescription) {
		Preconditions.checkNotNull(teamName);
		Preconditions.checkNotNull(teamDescription);
		Team team = new Team();
		team.setTeamName(teamName);
		team.setTeamDescription(teamDescription);
		Date currentDate = new Date();
		team.setCreatedTime(currentDate);
		teamRepository.save(team);

		TeamMessageChat teamMessageChat = new TeamMessageChat(team.getTeamId(),null);
		teamMessageChatRepository.save(teamMessageChat);

		TeamMember teamMember = new TeamMember();
		teamMember.setTeamId(team.getTeamId());
		teamMember.setUserId(creatorUserId);
		teamMemberRepository.save(teamMember);
	}

	@Override
	public List<Team> findTeamsByUserId(int userId) {
		List<TeamMember> teamMemberByUserId = teamMemberRepository.findTeamMemberByUserId(userId);
		List<Integer> teamIds = new ArrayList<>();
		teamMemberByUserId.forEach(teamMem -> teamIds.add(teamMem.getTeamId())
		);
		return teamRepository.findByTeamIdIn(teamIds);
	}

	@Transactional
	@Override
	public void addTeamMember(final int teamId, final int currentUserId, final int invitedUserId) {
		List<Team> teams = teamRepository.findByTeamIdIn(new ArrayList<Integer>() {{add(teamId);}});

		// makes sure there is one and only one team
		if(teams.size() > 1) throw new RuntimeException("Size greater than one");
		else if(teams.size() == 0) throw new RuntimeException("No team with this id: " + teamId);

		Team team = teams.get(0);
		System.out.println("team : " + team.getTeamName() + " desc: " + team.getTeamDescription());

		// makes sure the currentUser is in the team so it can invite
		if(!isMemberIsInTeam(teamId, currentUserId)){
			throw new RuntimeException("Member is not in this team " + teamId);
		}

		TeamMember teamMember = new TeamMember(invitedUserId,teamId);
		teamMemberRepository.save(teamMember);

	}

	public boolean isMemberIsInTeam(int teamId, int userId){

		List<TeamMember> teamMemberByUserId = teamMemberRepository.findTeamMemberByUserId(userId);

		for(TeamMember member : teamMemberByUserId){
			if (member.getTeamId() == teamId){
				return true;
			}
		}

		return false;
	}
}
