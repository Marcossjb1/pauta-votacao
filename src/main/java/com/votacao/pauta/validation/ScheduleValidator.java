package com.votacao.pauta.validation;

import com.votacao.pauta.exception.BadRequestException;
import com.votacao.pauta.model.Schedule;
import com.votacao.pauta.repository.ScheduleRepository;
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
