import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;
import static util.FileUtil.readFileToJSONObject;

public class Calender {

    UUID id;
    String name;
    String filename;
    ArrayList<Appointment> appointments = new ArrayList<>();
    ArrayList<Timeslot> timeslots = new ArrayList<>();


    public ArrayList<Timeslot> getTimeslots() {
        return timeslots;
    }


    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }


    public Calender(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.filename = String.format("calendar_data/%s.json", name);
    }


    public void readFromFile() {
        JSONObject jsonCalender;
        try {
            jsonCalender = readFileToJSONObject(this.filename);
        } catch (Exception e) {
            System.out.printf("Calender for %s hasn't been loaded.\n", this.name);
            e.printStackTrace();
            return;
        }
        loadAppointments(jsonCalender);
        loadTimeslots(jsonCalender);
    }


    void loadAppointments(JSONObject jsonCalender) {
        JSONArray appointments = (JSONArray) jsonCalender.get("appointments");
        for (Object appointment : appointments) {
            JSONObject jsonAppointment = (JSONObject) appointment;
            String id = (String) jsonAppointment.get("id");
            String start = (String) jsonAppointment.get("start");
            String end = (String) jsonAppointment.get("end");
            String patientId = (String) jsonAppointment.get("patient_id");
            this.appointments.add(new Appointment(id, start, end, patientId));
        }
    }


    void loadTimeslots(JSONObject jsonCalender) {
        JSONArray timeslots = (JSONArray) jsonCalender.get("timeslots");
        for (Object timeslot : timeslots) {
            JSONObject jsonTimeslot = (JSONObject) timeslot;
            String id = (String) jsonTimeslot.get("id");
            String start = (String) jsonTimeslot.get("start");
            String end = (String) jsonTimeslot.get("end");
            this.timeslots.add(new Timeslot(id, start, end));
        }
    }


    ArrayList<Timeslot> getAvailableTimeslots(Instant periodStart, Instant periodEnd) {
        this.appointments.sort(Comparator.comparing(Appointment::getStart));
        this.timeslots.sort(Comparator.comparing(Timeslot::getStart));

        ArrayList<Timeslot> availableTimeslots = new ArrayList<>();
        for (Timeslot timeslot : timeslots) {
            if (timeslot.isBeforeSearchingPeriod(periodStart)) {
                continue;
            }
            if (timeslot.isAfterSearchingPeriod(periodEnd)) {
                break;
            }
            if (isTimeslotFree(timeslot, appointments)) {
                availableTimeslots.add(timeslot);
            }
        }
        return availableTimeslots;
    }


    public boolean isTimeslotFree(Timeslot timeslot, ArrayList<Appointment> appointments) {
        boolean isTimeslotBusy = false;
        for (Appointment appointment : appointments) {
            if (appointment.isBeforeTimeslot(timeslot)) {
                continue;
            }
            if (appointment.isAfterTimeslot(timeslot)) {
                break;
            }
            isTimeslotBusy = true;
            break;
        }
        return !isTimeslotBusy;
    }


}
