package com.example.service;

import com.example.enums.WorkType;
import com.example.exceptions.AlreadyExistsException;
import com.example.exceptions.NotFoundException;
import com.example.model.WorkerModel;
import com.example.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    //////// HH/mm dd/MM/yyyy  - date format
    @Autowired
    private WorkerRepository repository;

    public WorkerModel create(WorkerModel workerModel){
        exists(workerModel.getPassportId());

        workerModel.setCreatedDate(LocalDate.now());
        repository.save(workerModel);

        return workerModel;
    }

    public WorkerModel update(WorkerModel workerModel){
        if(!repository.existsByPassportId(workerModel.getPassportId())){
            throw new NotFoundException("We cannot update because we don't have this worker!");
        }

        return repository.save(workerModel);
    }

    public WorkerModel byId(Integer id){
         return fromOptional(repository.findById(id),"id");
    }

    public WorkerModel byPassportId(String passportId){
        return fromOptional(repository.findByPassportId(passportId),"passport id");
    }

    public List<WorkerModel> list(){
        return repository.getAll();
    }

    public List<WorkerModel> allByName(String name){
        return repository.findAllByName(name);
    }

    public List<WorkerModel> allBySurname(String surname){
        return repository.findAllBySurname(surname);
    }

    public List<WorkerModel> allByWorkType(String workType){
        return repository.findAllByWorkType(WorkType.valueOf(workType));
    }

    public List<WorkerModel> allBySalary(Double salary){
        return repository.findAllBySalary(salary);
    }

    public List<WorkerModel> allByDate(String date){
        String[] dateA = date.split(" ");

        LocalDate d = LocalDateTime.parse(dateA[1]).toLocalDate();
        LocalTime t = LocalDateTime.parse(dateA[0]).toLocalTime();
        return repository.findAllByCreatedDate(LocalDateTime.of(d,t));
    }

    public List<WorkerModel> allByDateBefore(String date){
        String[] dateA = date.split(" ");

        LocalDate d = LocalDateTime.parse(dateA[1]).toLocalDate();
        LocalTime t = LocalDateTime.parse(dateA[0]).toLocalTime();

        return repository.findAllByCreatedDateBefore(LocalDateTime.of(d,t));
    }

    public List<WorkerModel> allByDateAfter(String date){
        String[] dateA = date.split(" ");

        LocalDate d = LocalDateTime.parse(dateA[1]).toLocalDate();
        LocalTime t = LocalDateTime.parse(dateA[0]).toLocalTime();
        return repository.findAllByCreatedDateAfter(LocalDateTime.of(d,t));
    }

    public List<WorkerModel> allByDateBetween(String dateFrom, String dateTill){
        String[] timeFrom = dateFrom.split(" ");
        String[] timeTill = dateTill.split(" ");

        LocalDate d1 = LocalDateTime.parse(timeFrom[1]).toLocalDate();
        LocalTime t1 = LocalDateTime.parse(timeFrom[0]).toLocalTime();

        LocalDate d2 = LocalDateTime.parse(timeTill[1]).toLocalDate();
        LocalTime t2 = LocalDateTime.parse(timeTill[0]).toLocalTime();

        return repository.findAllByCreatedDateBetween(LocalDateTime.of(d1,t1),LocalDateTime.of(d2,t2));
    }

    public void deletePid(String pid){
        WorkerModel model = byPassportId(pid);

        repository.delete(model);
    }

    public void deleteById(Integer id){
        WorkerModel model = byId(id);

        repository.delete(model);
    }

    private void exists(String passportId){
        if(repository.existsByPassportId(passportId)){
            throw new AlreadyExistsException("This worker already exists!");
        }
    }

    private WorkerModel fromOptional(Optional<WorkerModel> optional,String paramType){
        if(optional.isEmpty()){
            throw new NotFoundException("Worker with "+paramType+" does not exists!");
        }

        return optional.get();
    }
}
