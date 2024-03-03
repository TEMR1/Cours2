package com.temr1.Lesson2_3_maven;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
public class Main {

    public static void main(String[] args) throws Exception {
        var con = getHttpURLConnection();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine).append("\n");
            }

            JSONObject jsonResponse = new JSONObject(response.toString());

            JSONArray choices = jsonResponse.getJSONArray("choices");
            JSONObject firstChoice = choices.getJSONObject(0);
            JSONObject message = firstChoice.getJSONObject("message");
            String messageText = message.getString("content");
            System.out.println("Response: " + messageText);
        }
    }

    private static HttpURLConnection getHttpURLConnection() throws IOException, URISyntaxException {
        String url = "https://api.openai.com/v1/chat/completions";
        URL obj = new URI(url).toURL();
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer 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");

        con.setDoOutput(true);

        String requestBody = "{ \"model\": \"gpt-3.5-turbo\", " +
                "\"messages\": [{\"role\": \"user\", \"content\": \"Say this is a test!\"}], " +
                "\"temperature\": 0.7 }";

        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(requestBody);
            wr.flush();
        }
        return con;
    }
}