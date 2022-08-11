package br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.mapper;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.PatientRepository;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.PsychologistRepository;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.mongo.model.AppointmentDatabaseModel;
import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = { PsychologistRepository.class, PatientRepository.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class DatabaseMapper {

    @Autowired
    protected  PsychologistRepository psychologistRepository;

    @Autowired
    protected  PatientRepository patientRepository;

    @Mapping(target = "patient" , expression="java( patientRepository.findPatientById(appointmentDatabaseModel.getPatientId(), apiTokenContext.token) )")
    @Mapping(target = "psychologist", expression="java( psychologistRepository.findPsychologistById(appointmentDatabaseModel.getPsychologistId(), apiTokenContext.token) )")
    public abstract Appointment databaseToModel(AppointmentDatabaseModel appointmentDatabaseModel, @Context ApiTokenContext apiTokenContext);


    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "psychologist.id", target = "psychologistId")
    public abstract AppointmentDatabaseModel modelToDatabase(Appointment appointment);

}
