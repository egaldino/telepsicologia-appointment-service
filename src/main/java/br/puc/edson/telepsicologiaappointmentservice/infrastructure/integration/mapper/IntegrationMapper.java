package br.puc.edson.telepsicologiaappointmentservice.infrastructure.integration.mapper;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.integration.dto.PatientIntegrationDto;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.integration.dto.PsychologistIntegrationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IntegrationMapper {

    IntegrationMapper INSTANCE = Mappers.getMapper( IntegrationMapper.class );

    @Mapping(source = "crp", target = "id")
    Appointment.Psychologist dtoToModel(PsychologistIntegrationDto appointmentDatabaseModel);

    @Mapping(source = "cpf", target = "id")
    Appointment.Patient dtoToModel(PatientIntegrationDto appointmentDatabaseModel);
}
