package com.votacao.pauta.service;

import com.votacao.pauta.model.Schedule;

public interface ScheduleService {
    Schedule createSchedule(Schedule schedule);
    Schedule createSession(Schedule schedule);
    Schedule searchSchedule(Long id);

}
