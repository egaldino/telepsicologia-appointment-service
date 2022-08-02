package br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.domain.repository.AppointmentRepository;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.mapper.DatabaseMapper;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.mongo.MongoAppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final MongoAppointmentRepository mongoAppointmentRepository;
    
    private final DatabaseMapper databaseMapper;

    @Override
    public List<Appointment> findByPatientIdAndStatus(String patientId, Appointment.AppointmentStatus status) {
        return mongoAppointmentRepository
                .findByPatientIdAndStatus(patientId, status.name())
                .stream()
                .map(databaseMapper::databaseToModel)
                .collect(Collectors.toList());

    }

    @Override
    public List<Appointment> findByPsychologistIdAndStatus(String psychologistId, Appointment.AppointmentStatus status) {
        return mongoAppointmentRepository
                .findByPsychologistIdAndStatus(psychologistId, status.name())
                .stream()
                .map(databaseMapper::databaseToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Appointment save(Appointment appointment) {
        return Optional.of(appointment)
                .map(databaseMapper::modelToDatabase)
                .map(mongoAppointmentRepository::save)
                .map(databaseMapper::databaseToModel)
                .orElse(appointment);
    }
}
