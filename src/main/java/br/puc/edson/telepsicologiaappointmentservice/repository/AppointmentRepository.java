package br.puc.edson.telepsicologiaappointmentservice.repository;

import br.puc.edson.telepsicologiaappointmentservice.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {

    List<Appointment> findByPatientIdAndStatus(String patientId, Appointment.AppointmentStatus status);

    List<Appointment> findByPsychologistIdAndStatus(String psychologistId, Appointment.AppointmentStatus status);
}
