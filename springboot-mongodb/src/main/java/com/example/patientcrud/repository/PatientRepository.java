package com.example.patientcrud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.patientcrud.model.Patient;

@Repository
public interface PatientRepository extends MongoRepository<Patient, Long>{

}