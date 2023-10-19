package com.vote.schedule.service.impl;

import com.vote.schedule.exception.BadRequestException;
import com.vote.schedule.exception.ForbiddenException;
import com.vote.schedule.model.Schedule;
import com.vote.schedule.model.Vote;
import com.vote.schedule.repository.ScheduleRepository;
import com.vote.schedule.repository.UserRepository;
import com.vote.schedule.repository.VoteRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private VoteRepository voteRepository;
    @Mock
    private ScheduleRepository scheduleRepository;
    @InjectMocks
    private VoteServiceImpl voteServiceImpl;

    @Test
    public void shouldReturnCreateVote(){

        var schedule = new Schedule();
        schedule.setId(1L);
        schedule.setDescription("Pauta teste");
        schedule.setDeadline(now().plusMinutes(1));

        var vote = new Vote();
        vote.setId(1L);
        vote.setIdUser(1L);
        vote.setIdSchedule(1L);
        vote.setVote(true);

        given(voteRepository.findByIdUserAndIdSchedule(1L, 1L)).willReturn(null);
        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        given(userRepository.existsById(1L)).willReturn(true);

        var result = voteServiceImpl.createVote(vote);

    }

    @Test
    public void shouldReturnExceptionAlreadyCreateVote(){

        Vote existingVote = new Vote();
        existingVote.setIdUser(1L);
        existingVote.setIdSchedule(1L);
        existingVote.setId(2L);
        existingVote.setVote(true);

        given(voteRepository.findByIdUserAndIdSchedule(1L, 1L)).willReturn(existingVote);
        given(scheduleRepository.existsById(existingVote.getIdSchedule())).willReturn(true);
        given(userRepository.existsById(existingVote.getIdUser())).willReturn(true);

        thenThrownBy(() -> voteServiceImpl.createVote(existingVote))
                .isInstanceOf(ForbiddenException.class);

    }

    @Test
    public void shouldReturnSearchVote(){

        Long voteId = 1L;

        Vote expectedVote = new Vote();
        expectedVote.setId(voteId);
        when(voteRepository.findById(voteId)).thenReturn(Optional.of(expectedVote));

        Vote resultVote = voteServiceImpl.searchVote(voteId);

        assertNotNull(resultVote);
        assertEquals(voteId, resultVote.getId());

    }

    @Test
    public void shouldReturnResultOfVote(){

        Schedule schedule = new Schedule();
        schedule.setId(1L);
        schedule.setDeadline(now().plusMinutes(0));

        List<Vote> votes = new ArrayList<>();
        Vote voteYes = new Vote();
        voteYes.setVote(true);
        votes.add(voteYes);
        Vote voteNo = new Vote();
        voteNo.setVote(false);
        votes.add(voteNo);


        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));
        when(voteRepository.findByIdSchedule(1L)).thenReturn(votes);

        var result = voteServiceImpl.resultOfVote(1L);

        verify(scheduleRepository, times(1)).findById(schedule.getId());
        verify(voteRepository, times(1)).findByIdSchedule(schedule.getId());

        assertEquals(result.getIdSchedule(), schedule.getId());

    }

    @Test
    public void shouldReturnErrorForCreateVote() {
        var vote = new Vote();
        vote.setId(1L);
        vote.setVote(true);

        assertThrows(BadRequestException.class, () -> voteServiceImpl.createVote(vote));

        verify(voteRepository, never()).save(vote);
    }

    @Test
    public void shouldReturnErrorForSearchVote(){
        thenThrownBy(() -> voteServiceImpl.searchVote(null))
                .isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    public void shouldReturnCalculateResultForScheduleApprove(){
        var schedule = new Schedule();
        schedule.setId(1L);
        schedule.setDeadline(now().plusMinutes(0));

        List<Vote> votes = new ArrayList<>();
        var voteYes = new Vote();
        voteYes.setVote(true);
        votes.add(voteYes);

        var voteYesOne = new Vote();
        voteYesOne.setVote(false);
        votes.add(voteYesOne);

        var voteYesTwo = new Vote();
        voteYesTwo.setVote(true);
        votes.add(voteYesTwo);

        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));
        when(voteRepository.findByIdSchedule(1L)).thenReturn(votes);

        var result = voteServiceImpl.resultOfVote(1L);

        verify(scheduleRepository, times(1)).findById(schedule.getId());
        verify(voteRepository, times(1)).findByIdSchedule(schedule.getId());

        assertEquals(result.getIdSchedule(), schedule.getId());
        assertEquals(result.getNo(), 1);
        assertEquals(result.getYes(), 2);

    }

    @Test
    public void shouldReturnErrorForGetScheduleById(){
        thenThrownBy(() -> voteServiceImpl.getScheduleById(null))
                .isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    public void shouldReturnOfVoteInProgress(){

        Schedule schedule = new Schedule();
        schedule.setId(1L);
        schedule.setDeadline(now().plusMinutes(1));

        List<Vote> votes = new ArrayList<>();
        Vote voteYes = new Vote();
        voteYes.setVote(true);
        votes.add(voteYes);
        Vote voteNo = new Vote();
        voteNo.setVote(false);
        votes.add(voteNo);


        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));
        when(voteRepository.findByIdSchedule(1L)).thenReturn(votes);

        thenThrownBy(() -> voteServiceImpl.resultOfVote(schedule.getId()))
                .isInstanceOf(ForbiddenException.class);

    }


}
