package com.vote.schedule.service.impl;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.BDDMockito.given;

import com.vote.schedule.model.Schedule;
import com.vote.schedule.repository.ScheduleRepository;
import com.vote.schedule.validation.ScheduleValidator;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
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
        var schedule = new Schedule();
        var date = LocalDateTime.now();

        schedule.setId(1L);
        schedule.setDescription("Teste unitário de Pauta");
        schedule.setDeadline(date);

        given(scheduleRepository.save(schedule)).willReturn(schedule);

        var result = scheduleService.createSchedule(schedule);

        then(result.getId()).isEqualTo(1L);
        then(result.getDescription()).isEqualTo("Teste unitário de Pauta");
        then(result.getDeadline()).isEqualTo(date);
    }

    @Test
    public void shouldReturnCreateSessionWithScheduleEmpty(){
        var schedule = new Schedule();

        schedule.setId(null);

        given(scheduleRepository.findById(1L)).willReturn(null);

        thenThrownBy(() -> scheduleService.createSession(schedule))
                .isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    public void shouldReturnCreateSessionOfSchedule(){
        var schedule = new Schedule();

        schedule.setId(1L);
        schedule.setDeadline(null);

        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        given(scheduleRepository.save(schedule)).willReturn(schedule);

        var result = scheduleService.createSession(schedule);

        then(result.getId()).isEqualTo(1L);
        //then(result.getDeadline()).isEqualTo(schedule.getDeadline());
    }

    @Test
    public void shouldReturnSearchSchedule(){
        var schedule = new Schedule();

        schedule.setId(1L);

        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));

        var result = scheduleService.searchSchedule(schedule.getId());

        then(result.getId()).isEqualTo(1L);
    }

    @Test
    public void shouldReturnErrorSearchSchedule(){
        var schedule = new Schedule();

        schedule.setId(null);

        thenThrownBy(() -> scheduleService.searchSchedule(schedule.getId()))
                .isInstanceOf(ObjectNotFoundException.class);
    }
}
