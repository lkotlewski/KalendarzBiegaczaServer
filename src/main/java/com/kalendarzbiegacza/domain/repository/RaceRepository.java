package com.kalendarzbiegacza.domain.repository;

import com.kalendarzbiegacza.domain.Race;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Łukasz on 2018-04-10.
 */
public interface RaceRepository extends CrudRepository<Race, Long>{

    @Query("select r from Race r where date between ?1 and ?2 order by r.date asc")
    List<Race> findByDatesRange(Calendar dateFrom, Calendar dateTo);

    @Query("select r from Race r where  city= ?1 and date between ?2 and ?3 order by r.date asc")
    List<Race> findByDatesRangeAndCity(String city, Calendar dateFrom, Calendar dateTo);
}
