# Assignment Report

A brief decription: The code fetches weather data from a given city using api and extracts its humidity, temperature, wind speed and direction and prints the output to the console.

Now getting more specific, we create an api key and use it in `http://api.weatherapi.com`, which allows us to get weather data for our desired city. If the city doesn't exist, then the api will return the error code 400 which we use it to inform the user that they typed the city name wrongly. If it returns another error code then it could be that the user's connection is down(or something else), but if it doesn't return an error then it means it has fetched the data.

Afterwards we use the fetched data and extract the information we want. Basically getSomething function gets the data as an input and puts the appropriate keys to obtain the desired input.

Finally we print the information we just got from the functions and print them to the console.