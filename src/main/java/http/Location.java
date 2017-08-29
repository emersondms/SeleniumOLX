package http;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Location {

    public static String getState() {
        HttpURLConnection httpConn = null;

        try {
            httpConn = (HttpURLConnection) new URL(
                "https://geoip-db.com/jsonp/").openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Accept", "application/json");

            String jsonOutput = new BufferedReader(new InputStreamReader(
                (httpConn.getInputStream()))).readLine()
                .replace("callback(", "")
                .replace(")", "");

            JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonOutput);

            return jsonObject.get("state").toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (httpConn != null)
                httpConn.disconnect();
        }

        return null;
    }
}
