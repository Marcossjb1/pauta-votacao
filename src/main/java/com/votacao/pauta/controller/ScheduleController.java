package com.votacao.pauta.controller;

import com.votacao.pauta.model.Schedule;
import com.votacao.pauta.service.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pauta")
public class ScheduleController {

    @Autowired
    private ScheduleServiceImpl scheduleServiceImpl;

    @GetMapping("/{id}")
    public Schedule buscarPauta(@PathVariable Long id) {
        return scheduleServiceImpl.searchSchedule(id);
    }

    @PostMapping("/inserir")
    public Schedule inserirPauta(@RequestBody Schedule schedule) {
        return scheduleServiceImpl.createSchedule(schedule);
    }

    @PostMapping("/sessao")
    public Schedule inserirSessao(@RequestBody Schedule schedule) {
        return scheduleServiceImpl.createSession(schedule);
    }
}



