package lv5.generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WordService {


    public static String fetchRandomWord(int length) throws IOException {
        URL url = new URL("https://random-word-api.herokuapp.com/word?length=" + length);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder content = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            connection.disconnect();

            // Extract the word from the JSON response
            return extractWord(content.toString());
        } else {
            throw new IOException("GET request failed. Response Code: " + responseCode + "\n[500] 내부서버 오류. 헬모드 발동: 뜻이 없는 영단어가 생성됩니다. ");
        }
    }

    private static String extractWord(String jsonResponse) {
        // Extract the word from the JSON response
        return jsonResponse.substring(2, jsonResponse.length() - 2); // Removes leading and trailing brackets and quotes
    }
}