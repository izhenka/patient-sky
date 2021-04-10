import java.time.Instant;
import java.util.Date;

public class Appointment {
    private String id;

    public String getId() {
        return id;
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }

    private Instant start;
    private Instant end;


    public Appointment (String id, String start, String end) {
        this.id = id;
        //TODO: sjekke timezones og local offset
        this.start = Instant.parse(start+"Z");
        this.end = Instant.parse(end+"Z");
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
