package br.puc.edson.telepsicologiaappointmentservice.mapper;

import br.puc.edson.telepsicologiaappointmentservice.dto.RequestDto;
import br.puc.edson.telepsicologiaappointmentservice.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppointmentMapper {

    AppointmentMapper INSTANCE = Mappers.getMapper( AppointmentMapper.class );

    @Mapping(source = "date", target = "date", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "date", target = "hour", dateFormat = "HH:mm")
    RequestDto toRequestDto(Appointment appointment);

}
