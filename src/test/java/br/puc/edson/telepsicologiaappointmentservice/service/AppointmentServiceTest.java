package br.puc.edson.telepsicologiaappointmentservice.service;

import br.puc.edson.telepsicologiaappointmentservice.dto.RequestDto;
import br.puc.edson.telepsicologiaappointmentservice.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.repository.AppointmentRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository repository;

    @InjectMocks
    private AppointmentService service;

    @Test
    public void shouldGetPsychologistAppointmentsRequests(){
        String psychologistId = "crp";

        EasyRandom generator = new EasyRandom();
        List<Appointment> databaseReturn = generator
                .objects(Appointment.class, 10)
                .peek(appointment -> appointment.setStatus(Appointment.AppointmentStatus.REQUESTED))
                .collect(Collectors.toList());

        when(repository.findByPsychologistIdAndStatus(psychologistId, Appointment.AppointmentStatus.REQUESTED)).thenReturn(databaseReturn);

        List<RequestDto> result = service.getPsychologistAppointmentsRequests(psychologistId);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");

        assertEquals(databaseReturn.size(), result.size());
        for(int i=0; i < result.size(); i++){
            assertEquals(databaseReturn.get(i).getId(),result.get(i).getId());
            assertEquals(databaseReturn.get(i).getName(),result.get(i).getName());
            assertEquals(dateFormatter.format(databaseReturn.get(i).getDate()),result.get(i).getDate());
            assertEquals(hourFormatter.format(databaseReturn.get(i).getDate()),result.get(i).getHour());
        }
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

        List<RequestDto> result = service.getPsychologistAppointmentsSchedulled(psychologistId);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");

        assertEquals(databaseReturn.size(), result.size());
        for(int i=0; i < result.size(); i++){
            assertEquals(databaseReturn.get(i).getId(),result.get(i).getId());
            assertEquals(databaseReturn.get(i).getName(),result.get(i).getName());
            assertEquals(dateFormatter.format(databaseReturn.get(i).getDate()),result.get(i).getDate());
            assertEquals(hourFormatter.format(databaseReturn.get(i).getDate()),result.get(i).getHour());
        }
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

        List<RequestDto> result = service.getPsychologistAppointmentsDone(psychologistId);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");

        assertEquals(databaseReturn.size(), result.size());
        for(int i=0; i < result.size(); i++){
            assertEquals(databaseReturn.get(i).getId(),result.get(i).getId());
            assertEquals(databaseReturn.get(i).getName(),result.get(i).getName());
            assertEquals(dateFormatter.format(databaseReturn.get(i).getDate()),result.get(i).getDate());
            assertEquals(hourFormatter.format(databaseReturn.get(i).getDate()),result.get(i).getHour());
        }
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

        List<RequestDto> result = service.getPatientAppointmentsRequests(patientId);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");

        assertEquals(databaseReturn.size(), result.size());
        for(int i=0; i < result.size(); i++){
            assertEquals(databaseReturn.get(i).getId(),result.get(i).getId());
            assertEquals(databaseReturn.get(i).getName(),result.get(i).getName());
            assertEquals(dateFormatter.format(databaseReturn.get(i).getDate()),result.get(i).getDate());
            assertEquals(hourFormatter.format(databaseReturn.get(i).getDate()),result.get(i).getHour());
        }
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

        List<RequestDto> result = service.getPatientAppointmentsSchedulled(patientId);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");

        assertEquals(databaseReturn.size(), result.size());
        for(int i=0; i < result.size(); i++){
            assertEquals(databaseReturn.get(i).getId(),result.get(i).getId());
            assertEquals(databaseReturn.get(i).getName(),result.get(i).getName());
            assertEquals(dateFormatter.format(databaseReturn.get(i).getDate()),result.get(i).getDate());
            assertEquals(hourFormatter.format(databaseReturn.get(i).getDate()),result.get(i).getHour());
        }
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

        List<RequestDto> result = service.getPatientAppointmentsDone(patientId);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");

        assertEquals(databaseReturn.size(), result.size());
        for(int i=0; i < result.size(); i++){
            assertEquals(databaseReturn.get(i).getId(),result.get(i).getId());
            assertEquals(databaseReturn.get(i).getName(),result.get(i).getName());
            assertEquals(dateFormatter.format(databaseReturn.get(i).getDate()),result.get(i).getDate());
            assertEquals(hourFormatter.format(databaseReturn.get(i).getDate()),result.get(i).getHour());
        }
    }

}