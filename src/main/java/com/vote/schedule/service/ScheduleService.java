package com.vote.schedule.service;

import com.vote.schedule.model.Schedule;

public interface ScheduleService {
    Schedule createSchedule(Schedule schedule);
    Schedule createSession(Schedule schedule);
    Schedule searchSchedule(Long id);

}
