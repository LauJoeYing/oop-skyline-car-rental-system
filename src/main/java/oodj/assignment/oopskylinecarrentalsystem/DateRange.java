package oodj.assignment.oopskylinecarrentalsystem;

import java.time.LocalDate;

public class DateRange {

    private LocalDate startDate;
    private LocalDate endDate;

    public DateRange() {}

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
