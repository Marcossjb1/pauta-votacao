package com.vote.schedule.controller;

import com.vote.schedule.model.Schedule;
import com.vote.schedule.service.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleServiceImpl scheduleServiceImpl;

    @GetMapping("/{id}")
    public Schedule searchSchedule(@PathVariable Long id) {
        return scheduleServiceImpl.searchSchedule(id);
    }

    @PostMapping("/create")
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        return scheduleServiceImpl.createSchedule(schedule);
    }

    @PostMapping("/session")
    public Schedule createSession(@RequestBody Schedule schedule) {
        return scheduleServiceImpl.createSession(schedule);
    }
}



