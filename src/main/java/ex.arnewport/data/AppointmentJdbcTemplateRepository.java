package ex.arnewport.data;

import ex.arnewport.data.mappers.AppointmentMapper;
import ex.arnewport.models.Appointment;
import ex.arnewport.models.SearchCriteria;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.time.LocalDate;
import java.time.LocalTime;
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
                SELECT *
                FROM appointment
                WHERE appointment_id = ?;
                """;

        return jdbcTemplate.query(sql, new AppointmentMapper(), id).stream()
                .findFirst().orElse(null);
    }

    @Override
    public List<Appointment> findByParameters(SearchCriteria search) {
        StringBuilder sqlQuery = new StringBuilder("SELECT * FROM appointments WHERE 1=1");
        if (!search.getPatientFirstName().isEmpty()) {
            sqlQuery.append(" AND patient_first_name = '")
                    .append(search.getPatientFirstName())
                    .append("'");
        }
        if (!search.getPatientLastName().isEmpty()) {
            sqlQuery.append(" AND patient_last_name = '")
                    .append(search.getPatientLastName())
                    .append("'");
        }
        if (!search.getProviderFirstName().isEmpty()) {
            sqlQuery.append(" AND provider_first_name = '")
                    .append(search.getProviderFirstName())
                    .append("'");
        }
        if (!search.getProviderLastName().isEmpty()) {
            sqlQuery.append(" AND provider_last_name = '")
                    .append(search.getProviderLastName())
                    .append("'");
        }
        if (search.getAfterDate() != null && search.getBeforeDate() != null) {
            sqlQuery.append(" AND (date BETWEEN '")
                    .append(search.getAfterDate())
                    .append("' AND '")
                    .append(search.getBeforeDate())
                    .append("')");
        }
        if (search.getAfterDate() != null) {
            sqlQuery.append(" AND date >= '")
                    .append(search.getAfterDate())
                    .append("'");
        }
        if (search.getBeforeDate() != null) {
            sqlQuery.append(" AND date <= '")
                    .append(search.getBeforeDate())
                    .append("'");
        }
        if (search.isAscending()) {
            sqlQuery.append(" ORDER BY date ASC LIMIT 100");
        } else {
            sqlQuery.append(" ORDER BY date DESC LIMIT 100");
        }
        String sql = sqlQuery.toString();

        return jdbcTemplate.query(sql, new AppointmentMapper());
    }

    @Override
    @Transactional
    public Appointment create(Appointment appointment) {

        final String providerConflict = """
                SELECT *
                FROM appointments
                WHERE date = ?
                AND provider_first_name = ?
                AND provider_last_name = ?
                AND ? < end_time
                AND ? > start_time
                """;

        Appointment providerConflicts = jdbcTemplate.query(
                        providerConflict,
                        new AppointmentMapper(),
                        appointment.getDate(),
                        appointment.getProviderFirstName(),
                        appointment.getProviderLastName(),
                        appointment.getStartTime(),
                        appointment.getEndTime()
                ).stream()
                .findFirst().orElse(null);

        if (providerConflicts != null) {
            return null;
        }

        final String patientConflict = """
                SELECT *
                FROM appointments
                WHERE date = ?
                AND patient_first_name = ?
                AND patient_last_name = ?
                AND ? < end_time
                AND ? > start_time
                """;

        Appointment patientConflicts = jdbcTemplate.query(
                        providerConflict,
                        new AppointmentMapper(),
                        appointment.getDate(),
                        appointment.getProviderFirstName(),
                        appointment.getProviderLastName(),
                        appointment.getStartTime(),
                        appointment.getEndTime()
                ).stream()
                .findFirst().orElse(null);

        if (patientConflicts != null) {
            return null;
        }

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("appointments")
                .usingGeneratedKeyColumns("appointment_id");

        HashMap<String, Object> args = new HashMap<>();
            args.put("patient_first_name", appointment.getPatientFirstName());
            args.put("patient_last_name", appointment.getPatientLastName());
            args.put("provider_first_name", appointment.getProviderFirstName());
            args.put("provider_last_name", appointment.getProviderLastName());
            args.put("date", appointment.getDate());
            args.put("start_time", appointment.getStartTime());
            args.put("end_time", appointment.getEndTime());

        int id = insert.executeAndReturnKey(args).intValue();
        appointment.setAppointmentId(id);

        return appointment;
    }
}
