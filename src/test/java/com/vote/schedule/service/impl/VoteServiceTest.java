package com.vote.schedule.service.impl;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

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
        schedule.setDeadline(LocalDateTime.now().plusMinutes(1));

        var vote = new Vote();
        vote.setId(1L);
        vote.setIdUser(1L);
        vote.setIdSchedule(1L);
        vote.setVote(true);

        given(voteRepository.findByIdUserAndIdSchedule(1L, 1L)).willReturn(null);
        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        given(voteServiceImpl.validateDataForVote(vote)).willReturn(true);
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

        given(voteRepository.save(existingVote)).willReturn(existingVote);
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


    }
}
