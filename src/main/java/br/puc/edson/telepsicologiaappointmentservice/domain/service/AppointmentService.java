package br.puc.edson.telepsicologiaappointmentservice.domain.service;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto.ReplyRequestDto;

import java.util.List;

public interface AppointmentService {

    List<Appointment> getPsychologistAppointmentsRequests(String psychologistId, String token);

    List<Appointment> getPsychologistAppointmentsSchedulled(String psychologistId, String token);

    List<Appointment> getPsychologistAppointmentsDone(String psychologistId, String token);

    List<Appointment> getPatientAppointmentsRequests(String patientId, String token);

    List<Appointment> getPatientAppointmentsSchedulled(String patientId, String token);

    List<Appointment> getPatientAppointmentsDone(String patientId, String token);

    Appointment scheduleNewAppointment(Appointment appointment, String token);

    Appointment acceptRequest(ReplyRequestDto replyRequestDto, String token);

    Appointment denyRequest(ReplyRequestDto replyRequestDto, String token);
}
