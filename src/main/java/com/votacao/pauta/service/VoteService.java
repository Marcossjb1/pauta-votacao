package com.votacao.pauta.service;

import com.votacao.pauta.dto.ResultOfVotation;
import com.votacao.pauta.model.Vote;

public interface VoteService {
    Vote createVote(Vote vote);
    Vote searchVote(Long id);
    ResultOfVotation resultOfVote(Long idPauta);

}
