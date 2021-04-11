import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class Timeslot {
    UUID id;
    Instant start;
    Instant end;

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }

    public Timeslot(String id, String start, String end) {
        this.id = UUID.fromString(id);
        this.start = Instant.parse(start + "Z");
        this.end = Instant.parse(end + "Z");
    }

    public Timeslot(Instant start, Instant end) {
        this.id = UUID.randomUUID();
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

    public boolean isMergeableWithTimeslot(Timeslot timeslot) {
        return end.equals(timeslot.getStart());
    }

    public Timeslot getMergedTimeslot(Timeslot timeslot) {
        return new Timeslot(start, timeslot.getEnd());
    }

    public Timeslot getIntersection(Timeslot timeslot) {
        Instant start = timeslot.getStart();
        Instant end = timeslot.getEnd();
        Instant commonAvailableStart = this.start.compareTo(start) > 0 ? this.start : start;
        Instant commonAvailableEnd = this.end.compareTo(end) < 0 ? this.end : end;
        return new Timeslot(commonAvailableStart, commonAvailableEnd);
    }

    public int getDuration() {
        Duration duration = Duration.between(start, end);
        return (int) duration.toMinutes();
    }
}
