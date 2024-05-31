#include "ESP8266WiFi.h"
#include <WiFiUdp.h>
#include "DHT.h"
#define DHTTYPE DHT22
#define WIFI_SSID "333"
#define WIFI_PASS "123456789"
#define UDP_PORT 4210
DHT dht(D3, DHT22);
WiFiUDP UDP;
char packet[255];
char reply[] = "Packet Received!"; 

void setup() {
  Serial.begin(115200);
  Serial.println();
  Serial.println(F("DHTxx Test :"));
  dht.begin();
  WiFi.begin(WIFI_SSID, WIFI_PASS);
  Serial.print("Connecting to ...");
  Serial.print(WIFI_SSID);
  while(WiFi.status() != WL_CONNECTED){
    delay(2000);
    Serial.print(".");
  }
  Serial.println();
  Serial.println("Connected ! IP Address");
  Serial.println(WiFi.localIP());
  UDP.begin(UDP_PORT);
  Serial.print("Listening on UDP Port");
  Serial.println(UDP_PORT);
}

void loop() {
  int  h =dht.readHumidity();
  delay(2000);
  UDP.beginPacket(UDP.remoteIP(),UDP.remotePort());
  UDP.write(reply);
  UDP.println(h);
  UDP.endPacket();
  Serial.println(F("Humidity : \n"));
  Serial.println(h);
}
