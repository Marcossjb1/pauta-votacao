package com.vote.schedule.repository;

import com.vote.schedule.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Vote findByIdUserAndIdSchedule(Long idUser, Long idSchedule);
    List<Vote> findByIdSchedule(Long idSchedule);
}
