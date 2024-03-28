#include<SPI.h>
#include<MFRC522.h>

#define SS_PIN 10
#define RST_PIN A5

MFRC522 rfid(SS_PIN,RST_PIN);
MFRC522::MIFARE_Key key;

// Init array that will store new NUID
byte nuidPICC[4];


void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  SPI.begin();
  rfid.PCD_Init();
  for(byte i=0;i<6;i++){
    key.keyByte[i]=0xFF;
  }
}

void loop() {
  // put your main code here, to run repeatedly:
  if(!rfid.PICC_IsNewCardPresent())
    return;
  if(!rfid.PICC_ReadCardSerial())
    return;
  for(byte i=0;i<4;i++){
    nuidPICC[i]=rfid.uid.uidByte[i];
  }
  printHex(rfid.uid.uidByte, rfid.uid.size);
  Serial.println();
  rfid.PICC_HaltA();
  rfid.PCD_StopCrypto1();
}

void printHex(byte *buffer,byte bufferSize){
  for(byte i=0;i<bufferSize;i++){
    Serial.print(buffer[i]<0x10?"0":" ");
    Serial.print(buffer[i],HEX);
  }
}
