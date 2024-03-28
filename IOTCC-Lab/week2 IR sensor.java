int sensorPin=2;
int outputPin=13;
void setup() 
{
  pinMode(outputPin,OUTPUT);
  pinMode(sensorPin,INPUT);
  Serial.begin(9600);
}

void loop() 
{
  int sensorValue=digitalRead(sensorPin);
  Serial.print("SensorPin value: ");
  Serial.println(sensorValue);
  delay(1000);
  if(sensorValue==LOW)
  {
    digitalWrite(outputPin,HIGH);
  }
  else
  {
    digitalWrite(outputPin,LOW);
  }
}
