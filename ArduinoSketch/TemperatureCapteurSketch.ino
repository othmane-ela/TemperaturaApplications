//--------------------------declaration bib---------------------------
#include <WiFi.h>
#include <HTTPClient.h>
#include <Arduino_JSON.h>
#include "DHT.h"

//-------------------------declaration PIn----------------------
#define DHTPIN 4     // Digital pin connected to the DHT sensor
const char* ssid     = "LINK";
const char* password = "#CICADA3#";
const char* serverName = "http://192.168.1.100:8080/api/v1/node/data";
String node = "1";


// Uncomment whatever type you're using!
#define DHTTYPE DHT11   // DHT 11
DHT dht(DHTPIN, DHTTYPE);
//------------------------setup(Wifi, Bps ...)-------------------------
void setup() {
  Serial.begin(9600);
  Serial.println(F("DHTxx test!"));

  dht.begin();
//------------------------Wifi-------------------------
  WiFi.begin(ssid, password);
  Serial.println("Connecting");
  while(WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print("WIFI NOT CONNECTED");

   
  }
  Serial.println("");
  Serial.print("Connected to WiFi network with IP Address: ");
  Serial.println(WiFi.localIP());


}
//------------------------programme à éxecuter-----------------------
void loop() {
//if ((millis() - lastTime) > timerDelay) {
    // Reading temperature or humidity takes about 250 milliseconds!
            // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
            float h = dht.readHumidity();
            // Read temperature as Celsius (the default)
            float t = dht.readTemperature();
            // Read temperature as Fahrenheit (isFahrenheit = true)
            float f = dht.readTemperature(true);

               
          
            // Check if any reads failed and exit early (to try again).
            if (isnan(h) || isnan(t) || isnan(f)) {
              Serial.println(F("Failed to read from DHT sensor!"));
              return;
            }
          
            // Compute heat index in Fahrenheit (the default)
            float hif = dht.computeHeatIndex(f, h);
            // Compute heat index in Celsius (isFahreheit = false)
            float hic = dht.computeHeatIndex(t, h, false);
          
            Serial.print(F("Humidity: "));
            Serial.print(h);
            Serial.print(F("%  Temperature: "));
            Serial.print(t);
            Serial.print(F("°C "));

            
            

    //Check WiFi connection status
    if(WiFi.status()== WL_CONNECTED){
      WiFiClient client;
      HTTPClient http;
      http.begin(client, serverName);
      http.addHeader("Content-Type", "application/json");
      String jsonBody = "{\"node\":" +String(node) + ",\"temperature\":"+ String(t) +",\"humidity\":"+ String(h) +"}";
      const char *jsonBodyFormated = jsonBody.c_str();
      int httpResponseCode = http.POST(jsonBodyFormated);
       Serial.println(jsonBodyFormated);
      
      if (httpResponseCode>0) {
                 Serial.print("HTTP Response code: ");
                 Serial.println(httpResponseCode);
                }
                else {
                   Serial.println(jsonBodyFormated);
                  Serial.print("Error code: ");
                  Serial.println(httpResponseCode);
                  
                }
                // Free resources
                http.end();
              }
              else {
                Serial.println("WiFi Disconnected");
              }
              //Send an HTTP POST request every 30 seconds
              delay(500);  
//}

//}
}
