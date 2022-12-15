package oodj.assignment.oopskylinecarrentalsystem.model;

public class BookingSlot implements FileWrite {
    private DateRange dateRange;
    private float slotAmount;

    public BookingSlot(DateRange dateRange, float slotAmount) {
        this.dateRange = dateRange;
        this.slotAmount = slotAmount;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public float getSlotAmount() {
        return slotAmount;
    }

    public void setSlotAmount(float slotAmount) {
        this.slotAmount = slotAmount;
    }


    @Override
    public String fileFormat() {
        return String.join(" = ", dateRange.fileFormat(), String.valueOf(slotAmount));
    }
}
