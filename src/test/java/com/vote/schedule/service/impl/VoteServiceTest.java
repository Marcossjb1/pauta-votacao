package com.vote.schedule.service.impl;

import com.vote.schedule.model.Schedule;
import com.vote.schedule.model.Vote;
import com.vote.schedule.repository.ScheduleRepository;
import com.vote.schedule.repository.VoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {
    @Mock
    private VoteRepository voteRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private VoteServiceImpl voteServiceImpl;

    @Test //MARCOS
    public void shouldReturnCreateVote(){

        var schedule = new Schedule();
        schedule.setId(1L);
        schedule.setDescription("Pauta teste");
        schedule.setDeadline(LocalDateTime.now());

        var vote = new Vote();
        vote.setId(1L);
        vote.setIdUser(1L);
        vote.setIdSchedule(1L);
        vote.setVote(true);

        given(voteRepository.findByIdUserAndIdSchedule(1L, 1L)).willReturn(vote);
        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        given(scheduleRepository.existsById(1L)).willReturn(true);

        Vote result = voteServiceImpl.createVote(vote);

        assertNotNull(result);
        assertTrue(result.getVote());

    }
}
