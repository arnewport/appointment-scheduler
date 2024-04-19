package ex.arnewport.data;

import ex.arnewport.models.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository {
    Appointment findById(int id);
    List<Appointment> findByParameters(Appointment appointment, boolean ascending, LocalDate afterDate, LocalDate beforeDate);
    Appointment create(Appointment appointment);
}
