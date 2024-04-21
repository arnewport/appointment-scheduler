package ex.arnewport.models;

import java.time.LocalDate;
import java.util.Objects;

public class SearchCriteria {

    private String patientFirstName;
    private String patientLastName;
    private String providerFirstName;
    private String providerLastName;
    private boolean ascending;
    private LocalDate afterDate;
    private LocalDate beforeDate;

    public SearchCriteria() {
    }

    public SearchCriteria(String patientFirstName, String patientLastName, String providerFirstName, String providerLastName, boolean ascending, LocalDate afterDate, LocalDate beforeDate) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.providerFirstName = providerFirstName;
        this.providerLastName = providerLastName;
        this.ascending = ascending;
        this.afterDate = afterDate;
        this.beforeDate = beforeDate;
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

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public LocalDate getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(LocalDate afterDate) {
        this.afterDate = afterDate;
    }

    public LocalDate getBeforeDate() {
        return beforeDate;
    }

    public void setBeforeDate(LocalDate beforeDate) {
        this.beforeDate = beforeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchCriteria that = (SearchCriteria) o;
        return ascending == that.ascending && Objects.equals(patientFirstName, that.patientFirstName) && Objects.equals(patientLastName, that.patientLastName) && Objects.equals(providerFirstName, that.providerFirstName) && Objects.equals(providerLastName, that.providerLastName) && Objects.equals(afterDate, that.afterDate) && Objects.equals(beforeDate, that.beforeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientFirstName, patientLastName, providerFirstName, providerLastName, ascending, afterDate, beforeDate);
    }
}
