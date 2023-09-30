package com.vote.schedule.service.impl;

import com.vote.schedule.exception.ForbiddenException;
import com.vote.schedule.model.Schedule;
import com.vote.schedule.repository.ScheduleRepository;
import com.vote.schedule.service.ScheduleService;

import com.vote.schedule.validation.ScheduleValidator;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleValidator scheduleValidator;

    @Override
    public Schedule createSchedule(Schedule schedule) {
        scheduleValidator.validateDescriptionOfSchedule(schedule);
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule createSession(Schedule schedule) {
        Optional<Schedule> scheduleDB = scheduleRepository.findById(schedule.getId());
        if (scheduleDB.isEmpty()) {
            throw new ObjectNotFoundException(schedule.getId(), Schedule.class.getSimpleName());
        }
        if (scheduleDB.get().getDeadline() == null) {
            modifyScheduleWithDeadline(scheduleDB.get(), schedule.getDeadline());
            return scheduleRepository.save(scheduleDB.get());
        } else {
            throw new ForbiddenException("A pauta está fechada!");
        }
    }

    @Override
    public Schedule searchSchedule(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isPresent()) {
            return schedule.get();
        }
        throw new ObjectNotFoundException(id, Schedule.class.getSimpleName());
    }

    public void modifyScheduleWithDeadline(Schedule schedule, LocalDateTime deadLine) {
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime newDeadLine = (deadLine != null) ? deadLine : date.plusMinutes(1);
        schedule.setDeadline(newDeadLine);
    }
}