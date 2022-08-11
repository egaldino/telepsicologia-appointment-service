package br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.mapper;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.PatientRepository;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.PsychologistRepository;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.mongo.model.AppointmentDatabaseModel;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatabaseMapperTest {

    @Mock
    private PsychologistRepository psychologistRepository;

    @Mock
    private PatientRepository patientRepository;

    @Test
    void shouldConvertDatabaseToModel() {
        String token = "token";

        EasyRandom easyRandom = new EasyRandom();
        AppointmentDatabaseModel appointmentDatabaseModel = easyRandom.nextObject(AppointmentDatabaseModel.class);
        List<Appointment.AppointmentStatus> appointmentStatusList = Arrays.asList(Appointment.AppointmentStatus.values());
        Random rand = new Random();
        appointmentDatabaseModel.setStatus(appointmentStatusList.get(rand.nextInt(appointmentStatusList.size())).name());

        when(psychologistRepository.findPsychologistById(appointmentDatabaseModel.getPsychologistId(), token))
                .thenReturn(Appointment.Psychologist.builder().id(appointmentDatabaseModel.getPsychologistId()).build());

        when(patientRepository.findPatientById(appointmentDatabaseModel.getPatientId(), token))
                .thenReturn(Appointment.Patient.builder().id(appointmentDatabaseModel.getPatientId()).build());

        DatabaseMapper databaseMapper = new DatabaseMapperImpl();
        databaseMapper.patientRepository = patientRepository;
        databaseMapper.psychologistRepository = psychologistRepository;

        Appointment appointment = databaseMapper.databaseToModel(appointmentDatabaseModel, new ApiTokenContext(token));

        assertEquals(appointmentDatabaseModel.getId(), appointment.getId());
        assertEquals(appointmentDatabaseModel.getStatus(), appointment.getStatus().name());
        assertEquals(appointmentDatabaseModel.getDate(), appointment.getDate());
        assertEquals(appointmentDatabaseModel.getPatientId(), appointment.getPatient().getId());
        assertEquals(appointmentDatabaseModel.getPsychologistId(), appointment.getPsychologist().getId());
    }

    @Test
    void shouldConvertModelToDatabase() {
        String token = "token";

        EasyRandom easyRandom = new EasyRandom();
        Appointment appointment = easyRandom.nextObject(Appointment.class);

        DatabaseMapper databaseMapper = new DatabaseMapperImpl();
        databaseMapper.patientRepository = patientRepository;
        databaseMapper.psychologistRepository = psychologistRepository;

        AppointmentDatabaseModel appointmentDatabaseModel = databaseMapper.modelToDatabase(appointment);

        assertEquals(appointment.getId(), appointmentDatabaseModel.getId());
        assertEquals(appointment.getStatus().name(), appointmentDatabaseModel.getStatus());
        assertEquals(appointment.getDate(), appointmentDatabaseModel.getDate());
        assertEquals(appointment.getPatient().getId(), appointmentDatabaseModel.getPatientId());
        assertEquals(appointment.getPsychologist().getId(), appointmentDatabaseModel.getPsychologistId());
    }
}