package ex.arnewport.domain;

import ex.arnewport.data.AppointmentRepository;
import ex.arnewport.models.Appointment;
import ex.arnewport.models.SearchCriteria;
import org.springframework.stereotype.Service;

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

    public Result<Appointment> create(Appointment appointment) {

        Result<Appointment> result = validate(appointment);
        if (!result.isSuccess()) {
            return result;
        }

        if (appointment.getAppointmentId() != 0) {
            result.addMessage("appointment id cannot be set for `add` operation.");
            return result;
        }

        if (result.isSuccess()) {
            Appointment a = repository.create(appointment);
            result.setPayload(a);
        }

        if (result.getPayload() == null) {
            result.addMessage("conflict");
            return result;
        }

        return result;
    }

    // TODO: expand validation
    public Result<Appointment> validate(Appointment appointment) {
        Result<Appointment> result = new Result<>();
        if (appointment == null) {
            result.addMessage("appointment cannot be null");
            return result;
        }
        return result;
    }
}
