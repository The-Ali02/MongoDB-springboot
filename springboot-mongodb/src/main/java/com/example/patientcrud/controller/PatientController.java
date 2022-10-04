package com.example.patientcrud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.patientcrud.exception.ResourceNotFoundException;
import com.example.patientcrud.model.Patient;
import com.example.patientcrud.repository.PatientRepository;
import com.example.patientcrud.service.SequenceGeneratorService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@GetMapping("/patients")
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@GetMapping("/patients/{PatientId}")
	public ResponseEntity<Patient> getPatientById(@PathVariable(value = "PatientId") Long PatientId)
			throws ResourceNotFoundException {
		Patient patient = patientRepository.findById(PatientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + PatientId));
		return ResponseEntity.ok().body(patient);
	}

	@PostMapping("/patients")
	public Patient createPatient(@Valid @RequestBody Patient patient) {
		patient.setPatientId(sequenceGeneratorService.generateSequence(Patient.SEQUENCE_NAME));
		return patientRepository.save(patient);
	}

	@PutMapping("/patients/{PatientId}")
	public ResponseEntity<Patient> updatePatient(@PathVariable(value = "PatientId") Long PatientId,
			@Valid @RequestBody Patient patientDetails) throws ResourceNotFoundException {
		Patient patient = patientRepository.findById(PatientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + PatientId));

		patient.setPatientName(patientDetails.getPatientName());
		patient.setPatientContactNo(patientDetails.getPatientContactNo());
		final Patient updatedPatient = patientRepository.save(patient);
		return ResponseEntity.ok(updatedPatient);
	}

	@DeleteMapping("/patients/{PatientId}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "PatientId") Long PatientId)
			throws ResourceNotFoundException {
		Patient patient = patientRepository.findById(PatientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + PatientId));

		patientRepository.delete(patient);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}