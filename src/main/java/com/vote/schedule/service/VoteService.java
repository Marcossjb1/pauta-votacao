package com.vote.schedule.service;

import com.vote.schedule.dto.ResultOfVotation;
import com.vote.schedule.model.Vote;

public interface VoteService {
    Vote createVote(Vote vote);
    Vote searchVote(Long id);
    ResultOfVotation resultOfVote(Long idSchedule);

}
