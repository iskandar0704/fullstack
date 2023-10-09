package com.example.repository;

import com.example.enums.WorkType;
import com.example.model.WorkerModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends CrudRepository<WorkerModel, Integer> {
    List<WorkerModel> findAllByName(String name);

    List<WorkerModel> findAllBySurname(String surname);

    List<WorkerModel> findAllBySalary(Double salary);

    List<WorkerModel> findAllByWorkType(WorkType workType);

    List<WorkerModel> findAllByCreatedDate(LocalDateTime localDateTime);

    List<WorkerModel> findAllByCreatedDateBetween(LocalDateTime before, LocalDateTime after);

    List<WorkerModel> findAllByCreatedDateBefore(LocalDateTime before);

    List<WorkerModel> findAllByCreatedDateAfter(LocalDateTime after);

    Optional<WorkerModel> findByPassportId(String passportId);

    Boolean existsByPassportId(String passportId);

    @Query("from WorkerModel ")
    List<WorkerModel> getAll();


}
