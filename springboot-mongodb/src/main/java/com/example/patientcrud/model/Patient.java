package com.example.patientcrud.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
//import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Patient")
public class Patient {
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long PatientId;
	
	@NotBlank
	@Size(max=100)
	private String PatientName;
	
	@NotBlank
    @Size(max=100)
	private String PatientContactNo;

	public Patient() {
		
	}

	public Patient(@NotBlank @Size(max = 100) String patientName, @NotBlank @Size(max = 100) String patientContactNo) {
		super();
		PatientName = patientName;
		PatientContactNo = patientContactNo;
	}

	public long getPatientId() {
		return PatientId;
	}

	public void setPatientId(long patientId) {
		PatientId = patientId;
	}

	public String getPatientName() {
		return PatientName;
	}

	public void setPatientName(String patientName) {
		PatientName = patientName;
	}

	public String getPatientContactNo() {
		return PatientContactNo;
	}

	public void setPatientContactNo(String patientContactNo) {
		PatientContactNo = patientContactNo;
	}
	@Override
	public String toString() {
		return "Patient [PatientId=" + PatientId + ", PatientName=" + PatientName + ", PatientContactNo="
				+ PatientContactNo + "]";
	}
}
