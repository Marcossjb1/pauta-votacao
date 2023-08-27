package com.votacao.pauta.controller;

import com.votacao.pauta.dto.ResultOfVotation;
import com.votacao.pauta.model.Vote;
import com.votacao.pauta.service.impl.VoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voto")
public class VoteController {

    @Autowired
    private VoteServiceImpl voteServiceImpl;

    @GetMapping("/{id}")
    public Vote buscarVoto(@PathVariable Long id){
        return voteServiceImpl.searchVote(id);
    }

    @PostMapping("/inserir")
    public Vote inserirVoto(@RequestBody Vote vote){
        return voteServiceImpl.createVote(vote);
    }

    @GetMapping("resultado/{id}")
    public ResultOfVotation buscarResultado(@PathVariable Long id){
        return voteServiceImpl.resultOfVote(id);
    }


}
