package com.example.model;

import com.example.enums.WorkType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "workers")
public class WorkerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(name = "passport_id",unique = true)
    private String passportId;
    @Column
    private String name;
    @Column
    private String surname;
    @Column(name = "work_type")
    @Enumerated(value = EnumType.STRING)
    private WorkType workType;
    @Column
    private Double salary;
    @Column(name = "created_date")
    private LocalDate createdDate;
}
