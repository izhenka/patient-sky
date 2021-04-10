import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

public class Calender {
    UUID id;
    String name;
    String filename;
    ArrayList<Appointment> appointments;
    ArrayList<Timeslot> timeslots;

    public Calender(String name, String id) {
        this.id = UUID.fromString(id);
        this.name = name;
        this.filename = String.format("calendar_data/%s.json", name);
    }

    public void readFromFile() {
        try {
            JSONObject jsonObject = readFileToJSONObject(this.filename);
            System.out.printf("Hurra! Calender for %s readed!\n", this.name);
        } catch (Exception e) {
            System.out.printf("Calender for %s hasn't been loaded.\n", this.name);
            e.printStackTrace();
        }

    }

    public static JSONObject readFileToJSONObject(String filename) throws Exception {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filename));
        return (JSONObject) obj;
    }

}
