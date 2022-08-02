package br.puc.edson.telepsicologiaappointmentservice.domain.service;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto.ReplyRequestDto;

import java.util.Collection;
import java.util.List;

public interface AppointmentService {

    List<Appointment> getPsychologistAppointmentsRequests(String psychologistId);

    List<Appointment> getPsychologistAppointmentsSchedulled(String psychologistId);

    List<Appointment> getPsychologistAppointmentsDone(String psychologistId);

    List<Appointment> getPatientAppointmentsRequests(String patientId);

    List<Appointment> getPatientAppointmentsSchedulled(String patientId);

    List<Appointment> getPatientAppointmentsDone(String patientId);

    Appointment scheduleNewAppointment(Appointment appointment);

    Appointment acceptRequest(ReplyRequestDto replyRequestDto);

    Appointment denyRequest(ReplyRequestDto replyRequestDto);
}
