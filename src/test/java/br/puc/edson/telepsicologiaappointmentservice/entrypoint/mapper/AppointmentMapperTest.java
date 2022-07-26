package br.puc.edson.telepsicologiaappointmentservice.entrypoint.mapper;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto.AppointmentDto;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto.NewAppointmentDto;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment.AppointmentStatus.REQUESTED;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AppointmentMapperTest {

    @Test
    public void shouldConvertNewScheduleToAppointment() {
        EasyRandom easyRandom = new EasyRandom();
        NewAppointmentDto newAppointmentDto = easyRandom.nextObject(NewAppointmentDto.class);

        Appointment result = AppointmentMapper.INSTANCE.newScheduletoAppointment(newAppointmentDto);

        assertEquals(REQUESTED, result.getStatus());
        assertEquals(newAppointmentDto.getPatientId(), result.getPatient().getId());
        assertEquals(newAppointmentDto.getCrp(), result.getPsychologist().getId());
        assertEquals(newAppointmentDto.getDate(), result.getDate());
    }

    @Test
    public void shouldConvertAppointmentToDto() {
        EasyRandom easyRandom = new EasyRandom();
        Appointment appointment = easyRandom.nextObject(Appointment.class);

        AppointmentDto result = AppointmentMapper.INSTANCE.toDto(appointment);

        assertEquals(appointment.getId(), result.getId());
        assertEquals(appointment.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), result.getDate());
        assertEquals(appointment.getDate().format(DateTimeFormatter.ofPattern("HH:mm")), result.getHour());
        assertEquals(appointment.getStatus().name(), result.getStatus().name());
        assertEquals(appointment.getPatient().getId(), result.getPatient().getId());
        assertEquals(appointment.getPatient().getName(), result.getPatient().getName());
        assertEquals(appointment.getPsychologist().getId(), result.getPsychologist().getId());
        assertEquals(appointment.getPsychologist().getName(), result.getPsychologist().getName());

    }
}