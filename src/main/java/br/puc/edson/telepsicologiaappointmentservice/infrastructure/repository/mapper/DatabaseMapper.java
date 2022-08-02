package br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.mapper;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.PatientRepository;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.PsychologistRepository;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.mongo.model.AppointmentDatabaseModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { PsychologistRepository.class, PatientRepository.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DatabaseMapper {

    DatabaseMapper INSTANCE = Mappers.getMapper( DatabaseMapper.class );

    @Mapping(source = "patientId", target = "patient")
    @Mapping(source = "psychologistId", target = "psychologist")
    Appointment databaseToModel(AppointmentDatabaseModel appointmentDatabaseModel);


    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "psychologist.id", target = "psychologistId")
    AppointmentDatabaseModel modelToDatabase(Appointment appointment);

}