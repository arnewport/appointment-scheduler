package ex.arnewport.data.mappers;

import ex.arnewport.models.Appointment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentMapper implements RowMapper<Appointment> {

    @Override
    public Appointment mapRow(ResultSet resultSet, int i) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(resultSet.getInt("appointment_id"));
        appointment.setPatientFirstName(resultSet.getString("patient_first_name"));
        appointment.setPatientLastName(resultSet.getString("patient_last_name"));
        appointment.setProviderFirstName(resultSet.getString("provider_first_name"));
        appointment.setProviderLastName(resultSet.getString("provider_last_name"));
        appointment.setDate(resultSet.getObject("date", LocalDate.class));
        appointment.setStartTime(resultSet.getObject("start_time", LocalTime.class));
        appointment.setEndTime(resultSet.getObject("end_time", LocalTime.class));
        return appointment;
    }
}
