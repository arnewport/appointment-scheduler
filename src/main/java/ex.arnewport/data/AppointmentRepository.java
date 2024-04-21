package ex.arnewport.data;

import ex.arnewport.models.Appointment;
import ex.arnewport.models.SearchCriteria;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository {
    Appointment findById(int id);
    List<Appointment> findByParameters(SearchCriteria search);
    Appointment create(Appointment appointment);
}
