package com.vote.schedule.service.impl;

import com.vote.schedule.repository.ScheduleRepository;
import com.vote.schedule.validation.ScheduleValidator;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private ScheduleValidator scheduleValidator;
    @InjectMocks
    private ScheduleServiceImpl scheduleService;

}
