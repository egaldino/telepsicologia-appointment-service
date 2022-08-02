package br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.mongo;

import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.mongo.model.AppointmentDatabaseModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoAppointmentRepository extends MongoRepository<AppointmentDatabaseModel, String> {

    List<AppointmentDatabaseModel> findByPatientIdAndStatus(String patientId, String status);

    List<AppointmentDatabaseModel> findByPsychologistIdAndStatus(String psychologistId, String status);
}
