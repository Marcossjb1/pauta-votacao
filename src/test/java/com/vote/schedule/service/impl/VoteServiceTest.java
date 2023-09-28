package com.vote.schedule.service.impl;

import com.vote.schedule.repository.VoteRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {

    @Mock
    private VoteRepository voteRepository;
    @InjectMocks
    private VoteServiceImpl voteService;

}
