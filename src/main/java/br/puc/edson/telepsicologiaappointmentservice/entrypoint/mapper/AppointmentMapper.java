package br.puc.edson.telepsicologiaappointmentservice.entrypoint.mapper;

import br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto.AppointmentDto;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto.NewAppointmentDto;
import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppointmentMapper {

    AppointmentMapper INSTANCE = Mappers.getMapper( AppointmentMapper.class );

    @Mapping(source = "date", target = "date", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "date", target = "hour", dateFormat = "HH:mm")
    AppointmentDto toDto(Appointment appointment);

    @Mapping(source = "crp", target = "psychologist.id")
    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(constant = "REQUESTED", target = "status")
    Appointment newScheduletoAppointment(NewAppointmentDto newAppointmentDto);

}
