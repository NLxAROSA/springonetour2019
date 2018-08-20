package io.pivotal.workshop.workshopfortuneservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FortuneRepository extends JpaRepository<Fortune, Long> {

    // This query is not efficient, but ok for such s small DB
    @Query(value="select * from fortune order by rand() limit 1", nativeQuery = true)
    Fortune findRandomFortune();

}
