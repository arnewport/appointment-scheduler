package ex.arnewport.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Appointment {

    private int appointmentId;
    private String patientFirstName;
    private String patientLastName;
    private String providerFirstName;
    private String providerLastName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Appointment() {
    }

    public Appointment(int appointmentId, String patientFirstName, String patientLastName,
                       String providerFirstName, String providerLastName, LocalDate date,
                       LocalTime startTime, LocalTime endTime) {
        this.appointmentId = appointmentId;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.providerFirstName = providerFirstName;
        this.providerLastName = providerLastName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getProviderFirstName() {
        return providerFirstName;
    }

    public void setProviderFirstName(String providerFirstName) {
        this.providerFirstName = providerFirstName;
    }

    public String getProviderLastName() {
        return providerLastName;
    }

    public void setProviderLastName(String providerLastName) {
        this.providerLastName = providerLastName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return appointmentId == that.appointmentId && Objects.equals(patientFirstName, that.patientFirstName) && Objects.equals(patientLastName, that.patientLastName) && Objects.equals(providerFirstName, that.providerFirstName) && Objects.equals(providerLastName, that.providerLastName) && Objects.equals(date, that.date) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId, patientFirstName, patientLastName, providerFirstName, providerLastName, date, startTime, endTime);
    }
}
