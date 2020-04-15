package com.logines.schedule.repository;

import com.logines.schedule.model.Users;
import com.logines.schedule.model.WorkHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WorkHourRepository extends JpaRepository<WorkHour, Integer> {

    @Query("SELECT s FROM WorkHour s WHERE s.username=:username")
    List<WorkHour> findWorkHoursByUsername(@Param("username") String name);

    @Modifying
    @Query(value = "DELETE FROM logines.work_hour WHERE created_at < NOW() - INTERVAL '60 days'", nativeQuery = true)
    void deleteLast60DaysWorkHours();

    //TODO:!!!!!!!!!!!!!!!!!!!!!!!!!!!

    //List<WorkHour> findByEndTimeAndUsername(@Param("end_time") String end_time, String username);

    //List<WorkHour> <findByStartTimeAndUsername>(@Param("start_time") String start_time, String username);


    @Query(value = "SELECT s.* FROM logines.work_hour s WHERE s.start_time\\:\\:timestamp between ?\\:\\:timestamp and NOW()\\:\\:timestamp", nativeQuery = true)
    List<WorkHour> findByStartTime(String startTime);

    @Query(value = "SELECT s.* FROM logines.work_hour s WHERE s.start_time between ? and ?", nativeQuery = true)
    List<WorkHour> findBetweenTime(String startTime, String endTime);

    @Query(value = "SELECT s.* FROM logines.work_hour s WHERE s.start_time between ? and ? and s.username= ?", nativeQuery = true)
    List<WorkHour> findBetweenTimeAndUsername(String startTime, String endTime, String username);

    @Query(value = "SELECT s.* FROM logines.work_hour s WHERE s.username= ?", nativeQuery = true)
    List<WorkHour> findByUsername(String username);

    @Query(value = "SELECT s.* FROM logines.work_hour s WHERE s.start_time between ? and ? and s.username= ?", nativeQuery = true)
    List<WorkHour> findByStartTimeAndUsername(String startTime, String username);

    @Query(value ="SELECT s FROM logines.\"work_hour\" s WHERE s.end_time >= ? AND s.end_time < ?", nativeQuery = true)
    List<WorkHour> findByEndTime(String endtime);

    //@Query(value = "DELETE FROM logines.work_hour WHERE to_date(cast(created_at as TEXT),'dd-MM-yyyy HH24:mi') < NOW() - INTERVAL '30 days'", nativeQuery = true)

    /*select  extract(hour from event_time) as hour_of_day ,
    sum(cast (event_data as numeric))

    from temp
    group by extract(hour from event_time)
    order by hour_of_day desc*/
    /*@Query(value = "SELECT extract(start_time from WorkHour) as hour_of_day FROM WorkHour WHERE username=:username ")
    String sumUpWorkhours(@Param("username") String name);*/
}
