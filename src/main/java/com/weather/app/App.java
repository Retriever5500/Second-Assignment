package com.weather.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class App {
    // Copy your API-KEY here
    public final static String apiKey = "a3078107abc9440fa9e170129232702";
    // TODO: Write main function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose a city to fetch its temperature and humidity: ");
        String city = scanner.next();
        scanner.close();

        String weatherData = getWeatherData(city);
        if(weatherData.contains("error")) {
            System.out.println("No such city exists, or you may have made a typo.");
        } else {
            double temperatureCelsius = getTemperatureCelsius(weatherData);
            double temperatureFahrenheit = getTemperatureFahrenheit(weatherData);
            int humidity = getHumidity(weatherData);
            double windSpeed = getWindSpeed(weatherData);
            String windDirection = getWindDirection(weatherData);

            String temperatureFormat = temperatureCelsius + "°C " + temperatureFahrenheit + "°F";
            System.out.println("Temperature: " + temperatureFormat);
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Wind speed: " + windSpeed + " kph");
            System.out.println("Wind direction: " + windDirection);
            
        }
    }

    /**
     * Retrieves weather data for the specified city.
     *
     * @param city the name of the city for which weather data should be retrieved
     * @return a string representation of the weather data, or null if an error occurred
     */
    public static String getWeatherData(String city) {
        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Write getTemperature function returns celsius temperature of city by given json string
    public static double getTemperatureCelsius(String weatherJson){
        double answer = 0.0;
        JSONObject jsonData = new JSONObject(weatherJson);
        answer = jsonData.getJSONObject("current").getDouble("temp_c");
        return answer;
    }

    public static double getTemperatureFahrenheit(String weatherJson){
        double answer = 0.0;
        JSONObject jsonData = new JSONObject(weatherJson);
        answer = jsonData.getJSONObject("current").getDouble("temp_f");
        return answer;
    }

    // TODO: Write getHumidity function returns humidity percentage of city by given json string
    public static int getHumidity(String weatherJson){
        int answer = 0;
        JSONObject jsonData = new JSONObject(weatherJson);
        answer = jsonData.getJSONObject("current").getInt("humidity");
        return answer;
    }

    public static double getWindSpeed(String weatherJson){
        double answer = 0;
        JSONObject jsonData = new JSONObject(weatherJson);
        answer = jsonData.getJSONObject("current").getDouble("wind_kph");
        return answer;
    }

    public static String getWindDirection(String weatherJson){
        String answer = "";
        JSONObject jsonData = new JSONObject(weatherJson);
        answer = jsonData.getJSONObject("current").getString("wind_dir");
        return answer;
    }

}

