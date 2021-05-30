public class Filter {

    String buffer = "";
    String[] data;
    String[] g;
    String[] IR = new String[500];
    String[] RED = new String[500];
    double[] IRP = new double[500];
    double[] REDP = new double[500];
    int h = 0;
    int d=0;

    Sensor sensor = new Sensor();
    public void Filtrering() {
        while (d < 500) {
            String s = sensor.maaling();// her sætter vi s til at være den maaling vi får, som vi bruger som et argument
        if (s != null) {// her kontrollere vi om der er læst noget.
            buffer = buffer + s;// her kobles rest, på den første nye værdi
            int i = buffer.indexOf("A");// her finder vi pladsen A er på
            if (i > -1) {// her validere vi om der var et A i strengen
                data = buffer.split("A");// her splitter vi for a
                if (data != null && data.length > 0) {// her kontrollere vi den er splittet for 2 værdi
                    if (data[0].indexOf("B") < 1) {
                        data[0] = null;// her sætter vi den 1 plads til null hvis b ikke er en del af den array plads.
                    }
                    if (buffer.charAt(buffer.length() - 1) != 65) {
                        buffer = data[data.length - 1];
                    } else {
                        buffer = "";
                    }/* her har vi kontrolleret om der er noget rest, hvis ja så er det sat lig med buffer så vi kan
                     bruge det til næste måling, hvis nej er buffer tom.*/
                    while (h< data.length-1 && data.length > 1) {// her køre vi et loop for at splitte data array for h i en ny array g
                        if (data[h]!=null){
                        g = data[h].split("B");
                        if (g.length > 1) {// her fylder vi IR og R arrays ud
                            if ((d+h) >= 500){ // vi har kun 500 pladser i array, så d og h må ikke blive 501
                                break;
                            }
                            IR[d + h] = g[0];
                            RED[d + h] = g[1];
                            IRP[d + h] = Double.parseDouble(IR[d + h]);// her laver vi arrays fra String til Double
                            REDP[d + h] = Double.parseDouble(RED[d + h]);
                        }
                        }
                        h++;// dette gøres så vi både spillet data[1] og data[0]
                    }
                    d=d+h;
                    h = 0;// efter at have splittet, så sætter vi h=0;
                }

            }
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }

    }
        d=0;

    }

    // her har vi get metode for IR og R arrays.
    public double[] getIRP() {
        return IRP;
    }

    public double[] getREDP() {
        return REDP;
    }
}
