package ex.arnewport.controllers;

import ex.arnewport.domain.AppointmentService;
import ex.arnewport.models.Appointment;
import ex.arnewport.models.SearchCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> findById(@PathVariable int appointmentId) {
        Appointment appointment = service.findById(appointmentId);
        if (appointment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(appointment);
    }

    // TODO: separate concerns; handle errors
    @PostMapping("/search")
    public List<Appointment> findByParameters(
            @RequestBody SearchCriteria search
    ) {
        return service.findByParameters(search);
    }
}
