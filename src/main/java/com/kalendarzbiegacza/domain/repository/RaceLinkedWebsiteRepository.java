package com.kalendarzbiegacza.domain.repository;

import com.kalendarzbiegacza.domain.RaceLinkedWebsite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Łukasz on 2018-04-18.
 */
public interface RaceLinkedWebsiteRepository  extends CrudRepository<RaceLinkedWebsite, Long> {

    @Query("select r from RaceLinkedWebsite r where r.raceId = ?1")
    List<RaceLinkedWebsite> findWebsitesByRaceId(int raceId);
}