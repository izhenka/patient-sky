import java.time.Instant;
import java.util.Date;

public class Appointment {
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

    public Appointment(String id, String start, String end) {
        this.id = id;
        this.start = Instant.parse(start + "Z");
        this.end = Instant.parse(end + "Z");
    }


    public boolean isIntersectsTimeslot(Timeslot timeslot) {
        return start.compareTo(timeslot.getEnd()) < 0
                && end.compareTo(timeslot.getStart()) > 0;
    }

    public boolean isAfterTimeslot(Timeslot timeslot) {
        return start.compareTo(timeslot.getEnd()) >= 0;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
