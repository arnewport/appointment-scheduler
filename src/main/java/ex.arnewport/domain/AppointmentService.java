package ex.arnewport.domain;

import ex.arnewport.data.AppointmentRepository;
import ex.arnewport.models.Appointment;
import ex.arnewport.models.SearchCriteria;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public Appointment findById(int appointmentId) {
        return repository.findById(appointmentId);
    }

    public List<Appointment> findByParameters(SearchCriteria search) {
        return repository.findByParameters(search);
    }

    // TODO: add create

    // TODO: add validation
}
