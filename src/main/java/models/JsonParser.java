package models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import static models.Constant.*;

public class JsonParser {
    private static final JSONParser parser = new JSONParser();

    public static JSONObject getJsonObject() {
        try (FileReader file = new FileReader(FILE_NAME_JSON)) {
            return (JSONObject) parser.parse(file);
        } catch (IOException | ParseException e) {
            LOGGER.error(e);
        }
        return null;
    }
}
