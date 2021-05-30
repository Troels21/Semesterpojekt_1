import java.util.Arrays;

public class Lommeregner {
    double SPO2;
    double counter;
    double puls;
    double fluffer;
    double timer = 5000;
    double gemtPuls;
    double omgange=1;
    double gemtplads1=-15;
    double gemtplads2=-15;

    double irMAX1;  //burger alt imellem til SPO2
    double redMAX1;
    double irMIN1;
    double redMIN1;
    double irAverage1;
    double redAverage1;
    double irMAX2;
    double redMAX2;
    double irMIN2;
    double redMIN2;
    double irAverage2;
    double redAverage2; // Bruger alt imellem til SPO2

    double[] RED1 = new double[250];
    double[] IR1 = new double[250];
    double[] RED2 = new double[250];
    double[] IR2 = new double[250];


    //Metode til at sætte ARRAY, delt op i 2 således at MAX MIN bliver mere præcise, hvis der kommer fejlmålninger.
    public void setARRAY(double[] REDP, double[] IRP) {
        for (int i = 0; i < 250; i++) {
            RED1[i] = REDP[i];
            IR1[i] = IRP[i];
        }
        for (int i = 0; i < 250; i++) {
            RED2[i] = REDP[250 + i];
            IR2[i] = IRP[250 + i];
        }

        irMAX1 = Arrays.stream(IR1).max().getAsDouble();
        irMIN1 = Arrays.stream(IR1).min().getAsDouble();
        irAverage1 = Arrays.stream(IR1).average().getAsDouble();
        redMAX1 = Arrays.stream(RED1).max().getAsDouble();
        redMIN1 = Arrays.stream(RED1).min().getAsDouble();
        redAverage1 = Arrays.stream(RED1).average().getAsDouble();
        irMAX2 = Arrays.stream(IR2).max().getAsDouble();
        irMIN2 = Arrays.stream(IR2).min().getAsDouble();
        irAverage2 = Arrays.stream(IR2).average().getAsDouble();
        redMAX2 = Arrays.stream(RED2).max().getAsDouble();
        redMIN2 = Arrays.stream(RED2).min().getAsDouble();
        redAverage2 = Arrays.stream(RED2).average().getAsDouble();

    }
    //SPO2 Algoritme: 104-17*R=SPO2          R=AC660-DC660/AC900-DC900
    public void SPO2() {
        SPO2 =(104-(17* (((((redMAX1 + redMAX2) / 2) - ((redMIN1 + redMIN2) / 2)) / ((redAverage1 + redAverage2)
                / 2))/((((irMAX1 + irMAX2) / 2) - ((irMIN1 + irMIN2) / 2)) / ((irAverage1 + irAverage2) / 2)))));
        // SPO2 formel fundet ihttps: //
        // pdfserv.maximintegrated.com/en/an/AN6409.pdf?fbclid=IwAR1gm1NcIz4BQ-swpkue_Nj3YnJ39II-gwEV-pllvaRnPd_s3bHVjlg8KrA - 07/01/21
    }

    //Puls algorimte:
    public void Puls() {
        for (int a = 0; a < 248; a++) { //PULS algorimte første array.
            fluffer = (IR1[a + 1] - IR1[a]);//Hvis ydelta er lavere og der er gået minimum 15 målinger efter en optælling
            if (fluffer <= -30 && ((gemtplads1 - a) <= -15)){
                if ((IR1[a + 2] - IR1[a + 1]) > fluffer) {
                    counter++;
                    fluffer = 0;
                    gemtplads1=a;//gemt plads er ved start -15

                }
            }
        }

        for (int a = 0; a < 248; a++) {//PULS algorimte andet array.
            fluffer = (IR2[a + 1] - IR2[a]);//Hvis ydelta er lavere og der er gået minimum 15 målinger efter en optælling
            if (fluffer <= -30 && ((gemtplads2 - a) <= -15)){
                if ((IR2[a + 2] - IR2[a + 1]) > fluffer) {
                    counter++;
                    fluffer = 0;
                    gemtplads2=a; //gemt plads er ved start -15
                }
            }
        }

        puls = (60000 / (timer / (counter - omgange))); //Puls beregning, tæller op til gennemsnit af 4 målinger
        gemtplads1=-20;
        gemtplads2=-20;
        timer= timer+5000;
        omgange++;
        if (timer==25000) //BPM beregning
        {
            gemtPuls=puls; //gemmer
            timer=5000;// nulstiller alt til startværdi efter
            omgange=1;
            counter=0;
        }

        System.out.println("Puls: " + puls+"         Gennemsnitpuls: "+gemtPuls);
        }

    public double getSPO2() {
        return SPO2;
    }

    public double getPuls() {
        return puls;
    }
    public double getBPM(){
        return gemtPuls;
    }
}
