import java.time.Instant;

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
        //TODO: sjekke timezones og local offset
        this.start = Instant.parse(start + "Z");
        this.end = Instant.parse(end + "Z");
    }

    @Override
    public String toString() {
        return "Timeslot{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    //antar at slots ikke kan deles opp
    public boolean isBeforeSearchingPeriod(Instant periodStart) {
        return this.start.compareTo(periodStart) < 0;
    }

    public boolean isAfterSearchingPeriod(Instant periodEnd) {
        return this.end.compareTo(periodEnd) > 0;
    }
}
