import java.io.*;

public class DataBase {

    Writer bw;


    public DataBase() {
        try {
            bw = new BufferedWriter(new FileWriter("Data"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void skrivData(double IR, double R) { //skriv Data til fil
        try {
            bw.write("IR:   " + IR + "        Red: " + R + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.flush();// der bruges flush, så vi ikke slette de forrige data i filien
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void skrivResultat(double SPO2, double HR, double BPM) { //skriv resultat til fil
        try {
            bw.write("SPO2   " + SPO2 + "    HR :   " + HR  +"    BPM :   "+ BPM +"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
