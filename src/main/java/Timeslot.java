import java.time.Instant;

//TODO: superclasse for Appointment og Timeslot?
public class Timeslot {
    String id;
    Instant start;
    Instant end;


    public Timeslot (String id, String start, String end) {
        this.id = id;
        //TODO: sjekke timezones og local offset
        this.start = Instant.parse(start+"Z");
        this.end = Instant.parse(end+"Z");
    }

    @Override
    public String toString() {
        return "Timeslot{" +
                "id='" + id + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
