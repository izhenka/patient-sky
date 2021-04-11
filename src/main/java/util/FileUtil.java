package util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.time.Instant;

public class FileUtil {

    public static JSONObject readFileToJSONObject(String filename) throws Exception {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filename));
        return (JSONObject) obj;
    }
}
