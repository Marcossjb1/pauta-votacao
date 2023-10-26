package com.vote.schedule.service.impl;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import com.vote.schedule.exception.ForbiddenException;
import com.vote.schedule.model.Schedule;
import com.vote.schedule.repository.ScheduleRepository;
import com.vote.schedule.validation.ScheduleValidator;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private ScheduleValidator scheduleValidator;
    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @Test
    public void shouldReturnCreateSchedule(){
        var schedule = mock(Schedule.class);
        var date = now();

        given(schedule.getId()).willReturn(1L);
        given((schedule.getDescription())).willReturn("Teste unitário de Pauta");
        given(schedule.getDeadline()).willReturn(date);
        given(scheduleRepository.save(schedule)).willReturn(schedule);

        var result = scheduleService.createSchedule(schedule);

        then(result.getId()).isEqualTo(1L);
        then(result.getDescription()).isEqualTo("Teste unitário de Pauta");
        then(result.getDeadline()).isEqualTo(date);
    }

    @Test
    public void shouldReturnCreateSessionWithScheduleEmpty(){
        var schedule = mock(Schedule.class);

        given(schedule.getId()).willReturn(null);

        thenThrownBy(() -> scheduleService.createSession(schedule))
                .isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    public void shouldReturnCreateSessionOfSchedule(){
        var schedule = mock(Schedule.class);

        given(schedule.getId()).willReturn(1L);
        given(schedule.getDeadline()).willReturn(null);
        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        given(scheduleRepository.save(schedule)).willReturn(schedule);

        var result = scheduleService.createSession(schedule);

        then(result.getId()).isEqualTo(1L);
    }

    @Test
    public void shouldReturnErrorOfCreateSchedule(){
        var schedule = mock(Schedule.class);

        given(schedule.getId()).willReturn(1L);
        given(schedule.getDeadline()).willReturn(now());
        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));

        assertThrows(ForbiddenException.class, () -> scheduleService.createSession(schedule));
    }

    @Test
    public void shouldReturnSearchSchedule(){
        var schedule = mock(Schedule.class);

        given(schedule.getId()).willReturn(1L);
        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));

        var result = scheduleService.searchSchedule(schedule.getId());

        then(result.getId()).isEqualTo(1L);
    }

    @Test
    public void shouldReturnErrorSearchSchedule(){
        var schedule = mock(Schedule.class);

        given(schedule.getId()).willReturn(null);

        thenThrownBy(() -> scheduleService.searchSchedule(schedule.getId()))
                .isInstanceOf(ObjectNotFoundException.class);
    }
}