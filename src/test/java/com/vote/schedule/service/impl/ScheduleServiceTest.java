package com.vote.schedule.service.impl;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import com.vote.schedule.model.Schedule;
import com.vote.schedule.repository.ScheduleRepository;
import com.vote.schedule.validation.ScheduleValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

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

}
