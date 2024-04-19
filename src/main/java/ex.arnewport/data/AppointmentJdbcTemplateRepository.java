package ex.arnewport.data;

import ex.arnewport.data.mappers.AppointmentMapper;
import ex.arnewport.models.Appointment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;




@Repository
public class AppointmentJdbcTemplateRepository implements AppointmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public AppointmentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Appointment findById(int id) {

        final String sql = """
                select *
                from appointment
                where appointment_id = ?;
                """;

        return jdbcTemplate.query(sql, new AppointmentMapper(), id).stream()
                .findFirst().orElse(null);
    }

    @Override
    public List<Appointment> findByParameters(Appointment appointment, boolean ascending, LocalDate afterDate, LocalDate beforeDate) {
        StringBuilder sqlQuery = new StringBuilder("SELECT * FROM appointments WHERE 1=1");
        if (!appointment.getPatientFirstName().isEmpty()) {
            sqlQuery.append(" AND patient_first_name = ")
                    .append(appointment.getPatientFirstName());
        }
        if (!appointment.getPatientLastName().isEmpty()) {
            sqlQuery.append(" AND patient_last_name = ")
                    .append(appointment.getPatientLastName());
        }
        if (!appointment.getProviderFirstName().isEmpty()) {
            sqlQuery.append(" AND provider_first_name = ")
                    .append(appointment.getProviderFirstName());
        }
        if (!appointment.getProviderLastName().isEmpty()) {
            sqlQuery.append(" AND provider_last_name = ")
                    .append(appointment.getProviderLastName());
        }
        if (afterDate != null && beforeDate != null) {
            sqlQuery.append(" AND (date BETWEEN ")
                    .append(afterDate)
                    .append(" AND ")
                    .append(beforeDate)
                    .append(")");
        }
        if (afterDate != null) {
            sqlQuery.append(" AND date >= ")
                    .append(afterDate);
        }
        if (beforeDate != null) {
            sqlQuery.append(" AND date <= ")
                    .append(beforeDate);
        }
        if (ascending) {
            sqlQuery.append(" ORDER BY date ASC");
        } else {
            sqlQuery.append(" ORDER BY date DESC");
        }
        String sql = sqlQuery.toString();

        return jdbcTemplate.query(sql, new AppointmentMapper());
    }

    @Override
    @Transactional
    public Appointment create(Appointment appointment) {
        return null;
    }
}
