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

    @Test //MARCOS
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

        Vote result = voteServiceImpl.createVote(vote);
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
        // Defina uma pauta com ID 1 para o teste

        Vote scheduleVotation = new Vote();
        scheduleVotation.setIdSchedule(1L);
        scheduleVotation.setVote(1L, 2L);
        scheduleVotation.setIdUser(LocalDateTime.plusMinutes(1));

        // Simule que o método findById do ScheduleRepository retorne a pauta

        given(scheduleRepository.findById(1L)).willReturn(scheduleVotation);

        // Crie uma lista de votos para a pauta
        List<Vote> votes = new ArrayList<>();

        // Adicione votos à lista, por exemplo, alguns votos para "yes" e alguns para "no"
        votes.add(new Vote(existingVote.setVote(true), idSchedule, "yes"));
        votes.add(new Vote("user2", idSchedule, "yes"));
        votes.add(new Vote("user3", idSchedule, "no"));

        // Simule que o método findByIdSchedule do VoteRepository retorne a lista de votos
        given(voteRepository.findByIdSchedule(idSchedule)).willReturn(votes);

        LocalDateTime date = LocalDateTime.now();

        // Chame o método resultOfVote com o ID da pauta
        var result = voteServiceImpl.resultOfVote(idSchedule);

        // Verifique se o resultado retornado está correto
        assertEquals(idSchedule, result.getIdSchedule());
        assertEquals(2, result.getResult()); // Espera-se que haja 2 votos "yes"
        assertEquals(1, result.getResult());  // Espera-se que haja 1 voto "no"
        assertEquals("yes", result.getResult()); // Espera-se que o resultado seja "yes"
    }

    }
}
