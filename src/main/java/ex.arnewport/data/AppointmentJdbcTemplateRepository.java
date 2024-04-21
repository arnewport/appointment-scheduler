package ex.arnewport.data;

import ex.arnewport.data.mappers.AppointmentMapper;
import ex.arnewport.models.Appointment;
import ex.arnewport.models.SearchCriteria;
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
            sqlQuery.append(" AND patient_first_name = ")
                    .append(search.getPatientFirstName());
        }
        if (!search.getPatientLastName().isEmpty()) {
            sqlQuery.append(" AND patient_last_name = ")
                    .append(search.getPatientLastName());
        }
        if (!search.getProviderFirstName().isEmpty()) {
            sqlQuery.append(" AND provider_first_name = ")
                    .append(search.getProviderFirstName());
        }
        if (!search.getProviderLastName().isEmpty()) {
            sqlQuery.append(" AND provider_last_name = ")
                    .append(search.getProviderLastName());
        }
        if (search.getAfterDate() != null && search.getBeforeDate() != null) {
            sqlQuery.append(" AND (date BETWEEN ")
                    .append(search.getAfterDate())
                    .append(" AND ")
                    .append(search.getBeforeDate())
                    .append(")");
        }
        if (search.getAfterDate() != null) {
            sqlQuery.append(" AND date >= ")
                    .append(search.getAfterDate());
        }
        if (search.getBeforeDate() != null) {
            sqlQuery.append(" AND date <= ")
                    .append(search.getBeforeDate());
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
        return null;
    }
}
