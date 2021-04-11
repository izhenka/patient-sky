import java.time.Instant;
import java.util.UUID;

//TODO: superclasse for Appointment og Timeslot?
public class Timeslot {
    private final String id;
    private final Instant start;
    private final Instant end;

    public String getId() {
        return id;
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }

    public Timeslot(String id, String start, String end) {
        this.id = id;
        this.start = Instant.parse(start + "Z");
        this.end = Instant.parse(end + "Z");
    }

    public Timeslot(Instant start, Instant end) {
        this.id = UUID.randomUUID().toString();
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "\nTimeslot{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    //antar at slots ikke kan deles opp
    public boolean isBeforeSearchingPeriod(Instant periodStart) {
        return start.compareTo(periodStart) < 0;
    }

    public boolean isAfterSearchingPeriod(Instant periodEnd) {
        return end.compareTo(periodEnd) > 0;
    }

    public boolean isBeforeTimeslot(Timeslot timeslot) {
        return end.compareTo(timeslot.getStart()) <= 0;
    }

    public boolean isAfterTimeslot(Timeslot timeslot) {
        return start.compareTo(timeslot.getEnd()) >= 0;
    }

    public Timeslot getIntersection(Timeslot timeslot) {
        Instant start = timeslot.getStart();
        Instant end = timeslot.getEnd();
        Instant commonAvailableStart = this.start.compareTo(start) > 0 ? this.start : start;
        Instant commonAvailableEnd = this.end.compareTo(end) < 0 ? this.end : end;
        return new Timeslot(commonAvailableStart, commonAvailableEnd);
    }

}
