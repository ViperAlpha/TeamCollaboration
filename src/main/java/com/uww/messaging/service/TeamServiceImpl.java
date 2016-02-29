package com.uww.messaging.service;

import com.google.common.base.Preconditions;
import com.uww.messaging.contract.TeamService;
import com.uww.messaging.model.Team;
import com.uww.messaging.model.TeamMember;
import com.uww.messaging.repository.TeamMemberRepository;
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
        TeamMember teamMember = new TeamMember();
        teamMember.setTeamId(team.getTeamId());
        teamMember.setUserId(creatorUserId);
        teamMemberRepository.save(teamMember);
    }

    @Override
    public List<Team> findTeamsByUserId(int userId) {
        List<TeamMember> teamMemberByUserId = teamMemberRepository.findTeamMemberByUserId(userId);
        List<Integer> teamIds = new ArrayList<>();
        teamMemberByUserId.forEach(teamMem ->
                teamIds.add(teamMem.getTeamId())
        );
        return teamRepository.findByTeamIdIn(teamIds);
    }
}
