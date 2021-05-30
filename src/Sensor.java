import jssc.SerialPort;
import jssc.SerialPortException;
// importere jssc librariet

public class Sensor {
    private SerialPort sensor = new SerialPort("COM6"); // laver et sensor objekt
    public Sensor() { /*laver en konstruktor, hvor vi indstiller vores parametre  Sensor konstruktør åbner porten
         og sætter parameterne
        JSSC biblotek kommer fra  https://github.com/java-native/jssc/releases*/
        try {
            sensor.openPort();//prøve at åbne porten();
            sensor.setParams(115200, 8, 1, 0);//sætter parametre
            sensor.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);// sætter ingen flowcontrole, altså ingen begrænsing
            // på data modtagelse.

            sensor.purgePort(SerialPort.PURGE_TXCLEAR | SerialPort.PURGE_RXCLEAR);//prøver at slette data, hvis
            // der er data fra forrige læsning
        } catch (SerialPortException ex) {// catcher vi serialportexception
            System.out.println("FEJL SERIALPORTEXCEPTION");
        }
    }

    public String maaling() { //dette er vores metode til at læse fra porten

        try {
            if (sensor.getInputBufferBytesCount() > 0) {// her controllere vi at der læses noget.
                return sensor.readString();//så returner vi den læste værdi.
            } else {
                return null;// hvis ikke der læses returner vi null.
            }
        } catch (SerialPortException ex) {
            System.out.println("fejl: " + ex);
        }
        return null;// her returnes null.
    }
}

