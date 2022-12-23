package oodj.assignment.oopskylinecarrentalsystem.model;

import oodj.assignment.oopskylinecarrentalsystem.interfaces.FileWrite;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateRange implements FileWrite {
    private LocalDate startDate;
    private LocalDate endDate;
    private int duration;

    public DateRange(String startDate, String endDate) {            //Constructor
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        calculateDuration();
    }

    public DateRange(LocalDate startDate, LocalDate endDate) {          //Overloaded Constructor
        this.startDate = startDate;
        this.endDate = endDate;
        calculateDuration();
    }


    /**
     *
     * Getter and Setter for DateRange class
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;

        if (this.endDate != null) {
            calculateDuration();
        }
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;

        if (this.startDate != null) {
            calculateDuration();
        }
    }

    public int getDuration() {
        return duration;
    }

    private void calculateDuration() {
        duration = (int) DAYS.between(this.startDate, this.endDate);
    }

    public List<LocalDate> getDateList() {
        return startDate.datesUntil(endDate)
                .toList();
    }



    public String emailFormat() {       //Email format Reader method
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
    public String fileFormat() {            //Overriden method to join DateRange attributes
        return String.join(" || ", startDate.toString(), endDate.toString());
    }
}
