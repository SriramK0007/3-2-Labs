#define ECHOPin 5
#define TRIGPin 6
long duration;
int distance;
void setup() {
  pinMode(TRIGPin,OUTPUT);
  pinMode(ECHOPin,INPUT);
  Serial.begin(9600);
  Serial.println("Test of the ultrasonic sensor HC-SR04");
  Serial.println("with the Arduino UNO R3 board");
}

void loop() {
  digitalWrite(TRIGPin,LOW);
  delayMicroseconds(4);
  digitalWrite(TRIGPin,HIGH);
  delayMicroseconds(15);
  digitalWrite(TRIGPin,LOW);
  duration=pulseIn(ECHOPin,HIGH);
  distance=duration*(0.034/2);
  Serial.print("Distance");
  Serial.print(distance);
  Serial.println("cm");

}
