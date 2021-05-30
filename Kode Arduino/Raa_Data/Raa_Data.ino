#include <Wire.h>
#include "MAX30105.h" //Dette biblotek kommer fra https://github.com/Protocentral/Pulse_MAX30102
MAX30105 sensor;

void setup() {
  Serial.begin(115200); 
  sensor.begin(Wire, I2C_SPEED_FAST);
  
 
  byte ledBrightness = 0x1F; //Options: 0=Off to 255=50mA
  byte sampleAverage = 4; //Options: 1, 2, 4, 8, 16, 32
  byte ledMode = 2; //Options: 1 = Red only, 2 = Red + IR, 3 = Red + IR + Green
  int sampleRate = 400; //Options: 50, 100, 200, 400, 800, 1000, 1600, 3200
  int pulseWidth = 411; //Options: 69, 118, 215, 411
  int adcRange = 4096; 
   sensor.setup(ledBrightness,sampleAverage,ledMode,sampleRate,pulseWidth,adcRange);
}

void loop() {

Serial.print(sensor.getIR());
Serial.print("B");
Serial.print(sensor.getRed());
Serial.print("A");




 
}
