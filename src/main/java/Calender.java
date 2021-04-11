import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

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
            System.out.printf("Hurra! Calender for %s readed!\n", this.name);
        } catch (Exception e) {
            System.out.printf("Calender for %s hasn't been loaded.\n", this.name);
            e.printStackTrace();
            return;
        }
        loadAppointments(jsonCalender);
        loadTimeslots(jsonCalender);
    }

    public static JSONObject readFileToJSONObject(String filename) throws Exception {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filename));
        return (JSONObject) obj;
    }

    void loadAppointments(JSONObject jsonCalender) {
        JSONArray appointments = (JSONArray) jsonCalender.get("appointments");
        for(Object appointment: appointments){
            JSONObject jsonAppointment = (JSONObject) appointment;
            String id = (String) jsonAppointment.get("id");
            String start = (String) jsonAppointment.get("start");
            String end = (String) jsonAppointment.get("end");
            this.appointments.add(new Appointment(id, start, end));
        }
    }

    void loadTimeslots(JSONObject jsonCalender) {
        JSONArray timeslots = (JSONArray) jsonCalender.get("timeslots");
        for(Object timeslot: timeslots){
            JSONObject jsonTimeslot = (JSONObject) timeslot;
            String id = (String) jsonTimeslot.get("id");
            String start = (String) jsonTimeslot.get("start");
            String end = (String) jsonTimeslot.get("end");
            this.timeslots.add(new Timeslot(id, start, end));
        }
    }

}
