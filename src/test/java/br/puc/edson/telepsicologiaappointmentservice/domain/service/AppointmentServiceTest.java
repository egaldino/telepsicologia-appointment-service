package br.puc.edson.telepsicologiaappointmentservice.domain.service;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.domain.repository.AppointmentRepository;
import br.puc.edson.telepsicologiaappointmentservice.domain.service.impl.AppointmentServiceImpl;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
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

        EasyRandom generator = new EasyRandom();
        List<Appointment> databaseReturn = generator
                .objects(Appointment.class, 10)
                .peek(appointment -> appointment.setStatus(Appointment.AppointmentStatus.REQUESTED))
                .collect(Collectors.toList());

        when(repository.findByPsychologistIdAndStatus(psychologistId, Appointment.AppointmentStatus.REQUESTED)).thenReturn(databaseReturn);

        List<Appointment> result = service.getPsychologistAppointmentsRequests(psychologistId);
        assertEquals(databaseReturn, result);


    }

    @Test
    public void shouldGetPsychologistAppointmentsScheduled(){
        String psychologistId = "crp";

        EasyRandom generator = new EasyRandom();
        List<Appointment> databaseReturn = generator
                .objects(Appointment.class, 10)
                .peek(appointment -> appointment.setStatus(Appointment.AppointmentStatus.SCHEDULED))
                .collect(Collectors.toList());

        when(repository.findByPsychologistIdAndStatus(psychologistId, Appointment.AppointmentStatus.SCHEDULED)).thenReturn(databaseReturn);

        List<Appointment> result = service.getPsychologistAppointmentsSchedulled(psychologistId);
        assertEquals(databaseReturn, result);
    }

    @Test
    public void shouldGetPsychologistAppointmentsDone(){
        String psychologistId = "crp";

        EasyRandom generator = new EasyRandom();
        List<Appointment> databaseReturn = generator
                .objects(Appointment.class, 10)
                .peek(appointment -> appointment.setStatus(Appointment.AppointmentStatus.DONE))
                .collect(Collectors.toList());

        when(repository.findByPsychologistIdAndStatus(psychologistId, Appointment.AppointmentStatus.DONE)).thenReturn(databaseReturn);

        List<Appointment> result = service.getPsychologistAppointmentsDone(psychologistId);
        assertEquals(databaseReturn, result);
    }

    @Test
    public void shouldGetPatientAppointmentsRequests(){
        String patientId = "cpf";

        EasyRandom generator = new EasyRandom();
        List<Appointment> databaseReturn = generator
                .objects(Appointment.class, 10)
                .peek(appointment -> appointment.setStatus(Appointment.AppointmentStatus.REQUESTED))
                .collect(Collectors.toList());

        when(repository.findByPatientIdAndStatus(patientId, Appointment.AppointmentStatus.REQUESTED)).thenReturn(databaseReturn);

        List<Appointment> result = service.getPatientAppointmentsRequests(patientId);
        assertEquals(databaseReturn, result);
    }

    @Test
    public void shouldGetPatientAppointmentsScheduled(){
        String patientId = "cpf";

        EasyRandom generator = new EasyRandom();
        List<Appointment> databaseReturn = generator
                .objects(Appointment.class, 10)
                .peek(appointment -> appointment.setStatus(Appointment.AppointmentStatus.SCHEDULED))
                .collect(Collectors.toList());

        when(repository.findByPatientIdAndStatus(patientId, Appointment.AppointmentStatus.SCHEDULED)).thenReturn(databaseReturn);

        List<Appointment> result = service.getPatientAppointmentsSchedulled(patientId);
        assertEquals(databaseReturn, result);
    }

    @Test
    public void shouldGetPatientAppointmentsDone(){
        String patientId = "cpf";

        EasyRandom generator = new EasyRandom();
        List<Appointment> databaseReturn = generator
                .objects(Appointment.class, 10)
                .peek(appointment -> appointment.setStatus(Appointment.AppointmentStatus.DONE))
                .collect(Collectors.toList());

        when(repository.findByPatientIdAndStatus(patientId, Appointment.AppointmentStatus.DONE)).thenReturn(databaseReturn);

        List<Appointment> result = service.getPatientAppointmentsDone(patientId);
        assertEquals(databaseReturn, result);
    }

    @Test
    void shouldScheduleNewAppointment() {
        EasyRandom generator = new EasyRandom();
        Appointment appointment = generator.nextObject(Appointment.class);
        appointment.setStatus(Appointment.AppointmentStatus.REQUESTED);

        when(repository.save(appointment)).thenReturn(appointment);

        Appointment result = service.scheduleNewAppointment(appointment);

        assertEquals(appointment, result);
        verify(repository, times(1)).save(appointment);

    }
}