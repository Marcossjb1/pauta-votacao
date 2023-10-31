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

import java.time.LocalDateTime;
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
        var schedule = mock(Schedule.class);
        var vote = mock(Vote.class);

        given(schedule.getDeadline()).willReturn(LocalDateTime.parse("2024-10-20T09:00:00"));

        given(vote.getId()).willReturn(1L);
        given(vote.getIdUser()).willReturn(1L);
        given(vote.getIdSchedule()).willReturn(1L);
        given(vote.getVote()).willReturn(true);
        
        given(voteRepository.findByIdUserAndIdSchedule(1L, 1L)).willReturn(null);
        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        given(userRepository.existsById(1L)).willReturn(true);
        given(scheduleRepository.existsById(1L)).willReturn(true);
        given(voteRepository.save(vote)).willReturn(vote);

        var result = voteServiceImpl.createVote(vote);

        assertEquals(1L, result.getId());
        assertEquals(1L, result.getIdUser());
        assertEquals(1L, result.getIdSchedule());
        assertEquals(true, result.getVote());
    }

    @Test
    public void shouldReturnExceptionAlreadyCreateVote(){
        var existingVote = mock(Vote.class);

        given(existingVote.getIdUser()).willReturn(1L);
        given(existingVote.getIdSchedule()).willReturn(1L);

        given(voteRepository.findByIdUserAndIdSchedule(1L, 1L)).willReturn(existingVote);
        given(scheduleRepository.existsById(existingVote.getIdSchedule())).willReturn(true);
        given(userRepository.existsById(existingVote.getIdUser())).willReturn(true);

        thenThrownBy(() -> voteServiceImpl.createVote(existingVote))
                .isInstanceOf(ForbiddenException.class);
    }

    @Test
    public void shouldReturnSearchVote(){
        Long voteId = 1L;

        var expectedVote = mock(Vote.class);

        given(expectedVote.getId()).willReturn(voteId);
        given(voteRepository.findById(voteId)).willReturn(Optional.of(expectedVote));

        var resultVote = voteServiceImpl.searchVote(voteId);

        assertNotNull(resultVote);
        assertEquals(voteId, resultVote.getId());
    }

    @Test
    public void shouldReturnResultOfVote(){
        var schedule = mock(Schedule.class);
        var voteYes = mock(Vote.class);
        var voteNo = mock(Vote.class);

        var listVotes = List.of(voteYes, voteNo);

        given(voteYes.getVote()).willReturn(true);
        given(voteNo.getVote()).willReturn(false);
        given(schedule.getId()).willReturn(1L);
        given(schedule.getDeadline()).willReturn(now());
        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        given(voteRepository.findByIdSchedule(1L)).willReturn(listVotes);

        var result = voteServiceImpl.resultOfVote(1L);

        verify(scheduleRepository, times(1)).findById(schedule.getId());
        verify(voteRepository, times(1)).findByIdSchedule(schedule.getId());

        assertEquals(result.getIdSchedule(), schedule.getId());
    }

    @Test
    public void shouldReturnErrorForCreateVote() {
        var vote = mock(Vote.class);

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
        var schedule = mock(Schedule.class);
        var voteYes = mock(Vote.class);
        var voteYesOne = mock(Vote.class);
        var voteYesTwo = mock(Vote.class);

        given(voteYes.getVote()).willReturn(true);
        given(voteYesOne.getVote()).willReturn(true);
        given(voteYesTwo.getVote()).willReturn(true);
        given(schedule.getId()).willReturn(1L);
        given(schedule.getDeadline()).willReturn(now().plusMinutes(0));
        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        given(voteRepository.findByIdSchedule(1L)).willReturn(List.of(voteYes, voteYesOne, voteYesTwo));

        var result = voteServiceImpl.resultOfVote(1L);

        verify(scheduleRepository, times(1)).findById(schedule.getId());
        verify(voteRepository, times(1)).findByIdSchedule(schedule.getId());

        assertEquals(result.getIdSchedule(), schedule.getId());
        assertEquals(result.getNo(), 0);
        assertEquals(result.getYes(), 3);
    }

    @Test
    public void shouldReturnErrorForGetScheduleById(){
        thenThrownBy(() -> voteServiceImpl.getScheduleById(null))
                .isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    public void shouldReturnOfVoteInProgress(){
        var schedule = mock(Schedule.class);
        var voteYes = mock(Vote.class);
        var voteNo = mock(Vote.class);

        given(schedule.getId()).willReturn(1L);
        given(schedule.getDeadline()).willReturn(now().plusMinutes(1));
        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        given(voteRepository.findByIdSchedule(1L)).willReturn(List.of(voteYes, voteNo));

        thenThrownBy(() -> voteServiceImpl.resultOfVote(schedule.getId()))
                .isInstanceOf(ForbiddenException.class);
    }
}
