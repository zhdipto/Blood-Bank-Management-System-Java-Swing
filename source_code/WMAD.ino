#include <SoftwareSerial.h>
#include <SFE_BMP180.h>
#include <DHT.h>
#include <LiquidCrystal_I2C.h>
#include <Wire.h>

#define DHTPIN 6
#define DHTTYPE DHT11
DHT dht(DHTPIN,DHTTYPE);

#define ALTITUDE 19.0
SFE_BMP180 bmp;
double T, P;
char status;

LiquidCrystal_I2C mylcd(0x3F,20, 4);

#define RX 2 // TX of esp8266 in connected with Arduino pin 2

#define TX 3 // RX of esp8266 in connected with Arduino pin 3

String WIFI_SSID = "Dipto";// WIFI NAME

String WIFI_PASS = "dipto112233"; // WIFI PASSWORD

String API = "V5I3NCQTSU0UU48V";// Write API KEY

String HOST = "api.thingspeak.com";

String PORT = "80";

int countTrueCommand;

int countTimeCommand;
int timeCal = 0;

boolean found = false;   

SoftwareSerial esp8266(RX,TX); 
const int aqsensor = A0;

void setup() {

  dht.begin();
  Serial.begin(9600);
  esp8266.begin(9600);
  bmp.begin();
  mylcd.begin();
  pinMode (aqsensor,INPUT);

  sendCommand("AT",5,"OK");

  sendCommand("AT+CWMODE=1",5,"OK");

  sendCommand("AT+CWJAP=\""+ WIFI_SSID +"\",\""+ WIFI_PASS +"\"",20,"OK");

}

void loop() {
  float h = dht.readHumidity();
  float t = dht.readTemperature();
  int pm = analogRead(aqsensor);
  status =  bmp.startTemperature();
  if (status != 0) {
    delay(status);
    status = bmp.getTemperature(T);

    status = bmp.startPressure(3);// 0 to 3
    if (status != 0) {
      delay(status);
      status = bmp.getPressure(P, T);
      if (status != 0) {

      }
    }
  }
   // lcd display
  mylcd.setCursor(0,0);
  mylcd.print("Temperature=");
  mylcd.print(T);
  mylcd.print((char)223);
  mylcd.print("C");

  mylcd.setCursor(0,1);
  mylcd.print("Humidity = ");
  mylcd.print(h);
  mylcd.print("%");

  mylcd.setCursor(0,2);
  mylcd.print("Pressure = ");
  mylcd.print(P);
  mylcd.print("mb");

  mylcd.setCursor(0,3);
  mylcd.print("Air Quality=");
  mylcd.print(pm);
  mylcd.println("PPM");
  
  // lcd display
  if(timeCal==600000){
    String getData="GET /update?api_key="+ API+"&field1="+T+"&field2="+h+"&field3="+P+"&field4="+pm;

    sendCommand("AT+CIPMUX=1",5,"OK");

    sendCommand("AT+CIPSTART=0,\"TCP\",\""+ HOST +"\","+ PORT,15,"OK");

    sendCommand("AT+CIPSEND=0," +String(getData.length()+4),4,">");

    esp8266.println(getData);

    countTrueCommand++;

    sendCommand("AT+CIPCLOSE=0",5,"OK");

    timeCal=0;
  }
 delay(2000);
 timeCal+=2000;

}

void sendCommand(String command, int maxTime, char readReplay[]) {

  Serial.print(countTrueCommand);

  Serial.print(". at command => ");

  Serial.print(command);

  Serial.print(" ");
  while(countTimeCommand < (maxTime*1))

  {

    esp8266.println(command);//at+cipsend

    if(esp8266.find(readReplay))//ok

    {

      found = true;

      break;

    }

    countTimeCommand++;

  } 

  if(found == true)
  {

    Serial.println("OK");

    countTrueCommand++;

    countTimeCommand = 0;

  }
  if(found == false)
  {

    Serial.println("Fail");

    countTrueCommand = 0;

    countTimeCommand = 0;

  } 

  found = false;

 }
