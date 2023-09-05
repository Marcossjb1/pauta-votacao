package com.vote.schedule.controller;

import com.vote.schedule.dto.ResultOfVotation;
import com.vote.schedule.model.Vote;
import com.vote.schedule.service.impl.VoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteServiceImpl voteServiceImpl;

    @GetMapping("/{id}")
    public Vote searchVote(@PathVariable Long id){
        return voteServiceImpl.searchVote(id);
    }

    @PostMapping("/create")
    public Vote createVote(@RequestBody Vote vote){
        return voteServiceImpl.createVote(vote);
    }

    @GetMapping("result/{id}")
    public ResultOfVotation searchResult(@PathVariable Long id){
        return voteServiceImpl.resultOfVote(id);
    }


}
