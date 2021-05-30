void setup() {
  Serial.begin(115200);

}
long a = 0;
void loop() {
  if(a>=262144){
    a=0;}
  Serial.print(a);
  a=a+1;
  Serial.print("A");
  Serial.print(millis());
  Serial.print("B");
  delay(10);
  }
