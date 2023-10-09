package com.example.controller;


import com.example.model.WorkerModel;
import com.example.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worker")
@CrossOrigin("http://localhost:3000")
public class WorkerController {
    @Autowired
    private WorkerService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody WorkerModel workerModel){
        WorkerModel model = service.create(workerModel);
        return ResponseEntity.ok(model);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody WorkerModel workerModel){
        WorkerModel model = service.update(workerModel);
        return ResponseEntity.ok(model);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<?> byId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.byId(id));
    }

    @GetMapping("/byPassportId/{passportId}")
    public ResponseEntity<?> byId(@PathVariable("passportId") String passportId){
        return ResponseEntity.ok(service.byPassportId(passportId));
    }

    @GetMapping("/list")
    public List<WorkerModel> list(){
        return service.list();
    }

    @GetMapping("/allByName/{name}")
    public List<WorkerModel> allByName(@PathVariable("name") String name){
        return service.allByName(name);
    }

    @GetMapping("/allBySurname/{surname}")
    public List<WorkerModel> allBySurame(@PathVariable("surname") String surname){
        return service.allBySurname(surname);
    }

    @GetMapping("/allByWorkType/{workType}")
    public List<WorkerModel> allByWorkType(@PathVariable("workType") String workType){
        return service.allByWorkType(workType);
    }

    @GetMapping("/allBySalary/{salary}")
    public List<WorkerModel> allBySalary(@PathVariable("salary") Double salary){
        return service.allBySalary(salary);
    }

    @GetMapping("/allByDate")
    public List<WorkerModel> allByDate(@RequestBody String date){
        return service.allByDate(date);
    }

    @GetMapping("/allByDateBefore")
    public List<WorkerModel> allByDateBefore(@RequestBody String dateBefore){
        return service.allByDateBefore(dateBefore);
    }

    @GetMapping("/allByDateAfter")
    public List<WorkerModel> allByDateAfter(@RequestBody String dateAfter){
        return service.allByDateAfter(dateAfter);
    }

    @GetMapping("/allByDateBetween")
    public List<WorkerModel> allByDat(@RequestBody String dateAfter, String dateTill){
        return service.allByDateBetween(dateAfter,dateTill);
    }

    @DeleteMapping("/delete/ByPId")
    public ResponseEntity<?> deletePId(@RequestBody String passportId){
        service.deletePid(passportId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/byId/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
