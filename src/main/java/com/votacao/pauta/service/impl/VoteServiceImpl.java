package com.votacao.pauta.service.impl;

import com.votacao.pauta.exception.BadRequestException;
import com.votacao.pauta.exception.ForbiddenException;
import com.votacao.pauta.model.Schedule;
import com.votacao.pauta.dto.ResultOfVotation;
import com.votacao.pauta.model.Vote;
import com.votacao.pauta.repository.ScheduleRepository;
import com.votacao.pauta.repository.UserRepository;
import com.votacao.pauta.repository.VoteRepository;
import com.votacao.pauta.service.VoteService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Vote createVote(Vote vote) {
        if (validateDataForVote(vote)) {
            Vote votes = voteRepository.findByIdUserAndIdSchedule(vote.getIdUser(), vote.getIdSchedule());
            if (votes == null) {
                Schedule schedule = getPautaById(vote.getIdSchedule());
                verifyDeadLineforVote(schedule);
                return voteRepository.save(vote);
            }
            throw new ForbiddenException("Você não pode votar novamente.");
        }
        throw new BadRequestException("Dados incorretos, para votar é preciso que o Usuário e Pauta estejam corretos.");
    }

    @Override
    public Vote searchVote(Long id) {
        Optional<Vote> vote = voteRepository.findById(id);
        if (vote.isPresent()) {
            return vote.get();
        }
        throw new ObjectNotFoundException(id, Vote.class.getSimpleName());
    }

    @Override
    public ResultOfVotation resultOfVote(Long idSchedule) {
        LocalDateTime data = LocalDateTime.now();
        Optional<Schedule> schedule = scheduleRepository.findById(idSchedule);
        List<Vote> votes = voteRepository.findByIdSchedule(idSchedule);
        verifyVotationInProgress(data, schedule);
        int yes = counterVotesForYes(votes);
        int no = counterVotesForNo(votes);
        String result = calculateResult(yes, no);
        return new ResultOfVotation(idSchedule, yes, no, result);
    }

    private String calculateResult(int yes, int no) {
        if (yes > no) {
            return "Pauta aprovada!";
        } else {
            return "Pauta reprovada!";
        }
    }

    private boolean validateDataForVote(Vote vote) {
        return scheduleRepository.existsById(vote.getIdSchedule()) && userRepository.existsById(vote.getIdUser());
    }

    private Schedule getPautaById(Long idSchedule) {
        Optional<Schedule> schedule = scheduleRepository.findById(idSchedule);
        if (schedule.isPresent()) {
            return schedule.get();
        } else {
            throw new ObjectNotFoundException(idSchedule, Schedule.class.getSimpleName());
        }
    }

    private void verifyDeadLineforVote(Schedule schedule) {
        LocalDateTime date = LocalDateTime.now();
        if (!date.isBefore(schedule.getDeadline())) {
            throw new ForbiddenException("A Pauta está fechada, você não pode mais votar!");
        }
    }

    private void verifyVotationInProgress(LocalDateTime date, Optional<Schedule> schedule) {

        if (date.isBefore(schedule.get().getDeadline())) {
            throw new ForbiddenException("A votação está em andamento!");
        }
    }

    private int counterVotesForYes(List<Vote> votes) {
        int yes = 0;
        for (Vote vote : votes) {
            if (vote.getVote()) {
                yes++;
            }
        }
        return yes;
    }

    private int counterVotesForNo(List<Vote> votes) {
        int no = 0;
        for (Vote vote : votes) {
            if (!vote.getVote()) {
                no++;
            }
        }
        return no;
    }
}