import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello!");
        readFileToJSONObject("calendar_data/Danny boy.json");
    }


    public static void readFileToJSONObject(String filename) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filename));
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
