package br.puc.edson.telepsicologiaappointmentservice.domain.service;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.domain.repository.AppointmentRepository;
import br.puc.edson.telepsicologiaappointmentservice.domain.service.impl.AppointmentServiceImpl;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto.ReplyRequestDto;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository repository;

    @InjectMocks
    private AppointmentServiceImpl service;

    @Test
    public void shouldGetPsychologistAppointmentsRequests(){
        String psychologistId = "crp";
        String token = "token";

        EasyRandom generator = new EasyRandom();
        List<Appointment> databaseReturn = generator
                .objects(Appointment.class, 10)
                .peek(appointment -> appointment.setStatus(Appointment.AppointmentStatus.REQUESTED))
                .collect(Collectors.toList());

        when(repository.findByPsychologistIdAndStatus(psychologistId, Appointment.AppointmentStatus.REQUESTED, token)).thenReturn(databaseReturn);

        List<Appointment> result = service.getPsychologistAppointmentsRequests(psychologistId, token);
        assertEquals(databaseReturn, result);


    }

    @Test
    public void shouldGetPsychologistAppointmentsScheduled(){
        String psychologistId = "crp";
        String token = "token";

        EasyRandom generator = new EasyRandom();
        List<Appointment> databaseReturn = generator
                .objects(Appointment.class, 10)
                .peek(appointment -> appointment.setStatus(Appointment.AppointmentStatus.SCHEDULED))
                .collect(Collectors.toList());

        when(repository.findByPsychologistIdAndStatus(psychologistId, Appointment.AppointmentStatus.SCHEDULED, token)).thenReturn(databaseReturn);

        List<Appointment> result = service.getPsychologistAppointmentsSchedulled(psychologistId, token);
        assertEquals(databaseReturn, result);
    }

    @Test
    public void shouldGetPsychologistAppointmentsDone(){
        String psychologistId = "crp";
        String token = "token";

        EasyRandom generator = new EasyRandom();
        List<Appointment> databaseReturn = generator
                .objects(Appointment.class, 10)
                .peek(appointment -> appointment.setStatus(Appointment.AppointmentStatus.DONE))
                .collect(Collectors.toList());

        when(repository.findByPsychologistIdAndStatus(psychologistId, Appointment.AppointmentStatus.DONE, token)).thenReturn(databaseReturn);

        List<Appointment> result = service.getPsychologistAppointmentsDone(psychologistId, token);
        assertEquals(databaseReturn, result);
    }

    @Test
    public void shouldGetPatientAppointmentsRequests(){
        String patientId = "cpf";
        String token = "token";

        EasyRandom generator = new EasyRandom();
        List<Appointment> databaseReturn = generator
                .objects(Appointment.class, 10)
                .peek(appointment -> appointment.setStatus(Appointment.AppointmentStatus.REQUESTED))
                .collect(Collectors.toList());

        when(repository.findByPatientIdAndStatus(patientId, Appointment.AppointmentStatus.REQUESTED, token)).thenReturn(databaseReturn);

        List<Appointment> result = service.getPatientAppointmentsRequests(patientId, token);
        assertEquals(databaseReturn, result);
    }

    @Test
    public void shouldGetPatientAppointmentsScheduled(){
        String patientId = "cpf";
        String token = "token";

        EasyRandom generator = new EasyRandom();
        List<Appointment> databaseReturn = generator
                .objects(Appointment.class, 10)
                .peek(appointment -> appointment.setStatus(Appointment.AppointmentStatus.SCHEDULED))
                .collect(Collectors.toList());

        when(repository.findByPatientIdAndStatus(patientId, Appointment.AppointmentStatus.SCHEDULED, token)).thenReturn(databaseReturn);

        List<Appointment> result = service.getPatientAppointmentsSchedulled(patientId, token);
        assertEquals(databaseReturn, result);
    }

    @Test
    public void shouldGetPatientAppointmentsDone(){
        String patientId = "cpf";
        String token = "token";

        EasyRandom generator = new EasyRandom();
        List<Appointment> databaseReturn = generator
                .objects(Appointment.class, 10)
                .peek(appointment -> appointment.setStatus(Appointment.AppointmentStatus.DONE))
                .collect(Collectors.toList());

        when(repository.findByPatientIdAndStatus(patientId, Appointment.AppointmentStatus.DONE, token)).thenReturn(databaseReturn);

        List<Appointment> result = service.getPatientAppointmentsDone(patientId, token);
        assertEquals(databaseReturn, result);
    }

    @Test
    void shouldScheduleNewAppointment() {
        EasyRandom generator = new EasyRandom();
        Appointment appointment = generator.nextObject(Appointment.class);
        appointment.setStatus(Appointment.AppointmentStatus.REQUESTED);
        String token = "token";


        when(repository.save(appointment, token)).thenReturn(appointment);

        Appointment result = service.scheduleNewAppointment(appointment, token);

        assertEquals(appointment, result);
        verify(repository, times(1)).save(appointment, token);

    }

    @Test
    void shouldAcceptAppointment() {
        EasyRandom generator = new EasyRandom();
        Appointment appointment = generator.nextObject(Appointment.class);
        appointment.setStatus(Appointment.AppointmentStatus.REQUESTED);
        String token = "token";


        when(repository.findById(appointment.getId(), token)).thenReturn(Optional.of(appointment));
        when(repository.save(appointment, token)).thenReturn(appointment);

        Appointment result = service.acceptRequest(ReplyRequestDto.builder().appointmentId(appointment.getId()).build(), token);

        ArgumentCaptor<Appointment> captor = ArgumentCaptor.forClass(Appointment.class);
        verify(repository, times(1)).save(captor.capture(), eq(token));

        Appointment savedObject = captor.getValue();
        assertEquals(savedObject, result);
        assertEquals(Appointment.AppointmentStatus.SCHEDULED, savedObject.getStatus());

    }

    @Test
    void shouldDenyAppointment() {
        EasyRandom generator = new EasyRandom();
        Appointment appointment = generator.nextObject(Appointment.class);
        appointment.setStatus(Appointment.AppointmentStatus.REQUESTED);
        String token = "token";


        when(repository.findById(appointment.getId(), token)).thenReturn(Optional.of(appointment));
        when(repository.save(appointment, token)).thenReturn(appointment);

        Appointment result = service.denyRequest(ReplyRequestDto.builder().appointmentId(appointment.getId()).build(), token);

        ArgumentCaptor<Appointment> captor = ArgumentCaptor.forClass(Appointment.class);
        verify(repository, times(1)).save(captor.capture(), eq(token));

        Appointment savedObject = captor.getValue();
        assertEquals(savedObject, result);
        assertEquals(Appointment.AppointmentStatus.DENIED, savedObject.getStatus());

    }
}