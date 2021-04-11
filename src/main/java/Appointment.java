public class Appointment extends Timeslot{
    String patientId;

    public Appointment(String id, String start, String end, String patientId) {
        super(id, start, end);
        this.patientId = patientId;
    }


    @Override
    public String toString() {
        return "\nAppointment{" +
                "start=" + start +
                ", end=" + end +
                ", patientId=" + patientId +
                '}';
    }
}
