package com.vote.schedule.validation;

import com.vote.schedule.exception.BadRequestException;
import com.vote.schedule.model.Schedule;
import com.vote.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleValidator {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public void validateDescriptionOfSchedule(Schedule schedule){
        if (schedule.getDescription().isEmpty()) {
            throw new BadRequestException("É necessário inserir uma descrição na pauta.");
        }
    }
}
