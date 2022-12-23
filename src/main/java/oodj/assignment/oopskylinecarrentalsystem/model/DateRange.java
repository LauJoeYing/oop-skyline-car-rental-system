package oodj.assignment.oopskylinecarrentalsystem.model;

import oodj.assignment.oopskylinecarrentalsystem.interfaces.FileWrite;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

// OOP Concept Implemented: Encapsulation
public class DateRange implements FileWrite {
    private LocalDate startDate;
    private LocalDate endDate;
    private int duration;

    // Constructor for creating a new DateRange object.
    public DateRange(String startDate, String endDate) {
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        calculateDuration();
    }

    public DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        calculateDuration();
    }

    // Returns the start date of the DateRange.
    public LocalDate getStartDate() {
        return startDate;
    }

    //  Sets the start date of the DateRange.
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;

        if (this.endDate != null) {
            calculateDuration();
        }
    }

    // Returns the end date of the DateRange.
    public LocalDate getEndDate() {
        return endDate;
    }

    //  Sets the end date of the DateRange.
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;

        if (this.startDate != null) {
            calculateDuration();
        }
    }

    // Returns the duration of the DateRange.
    public int getDuration() {
        return duration;
    }

    // Calculates the duration of the DateRange based on the start and end dates.
    private void calculateDuration() {
        duration = (int) DAYS.between(this.startDate, this.endDate);
    }

    public List<LocalDate> getDateList() {
        return startDate.datesUntil(endDate)
                .toList();
    }


    /**
     * Returns a formatted string representation of the DateRange for use in an email.
     * The returned string includes the start and end dates of the DateRange and the duration in days.
     * The string is formatted as an HTML unordered list, with each item in the list represented as a list item element.
     */
    public String emailFormat() {
        return String.format(
                        """
                        <ul style="padding-left: 1rem;">
                            <li>Start Date: <a style="font-weight:bold">%s</a></li>
                            <li>End Date: <a style="font-weight:bold">%s</a></li>
                            <li>Duration: <a style="font-weight:bold">%d Day%s</a></li>
                        </ul>
                        """
    , startDate.toString(), endDate.toString(), duration, duration > 1 ? "s" : "");
    }

    @Override
    public String fileFormat() {
        return String.join(" || ", startDate.toString(), endDate.toString());
    }
}
