public class Main {
    static double rundter = 0;

    static Filter ft = new Filter();
    static Lommeregner lr = new Lommeregner();
    static DataBase db = new DataBase();

    public static void main(String[] args) {
        while (true) {
            ft.Filtrering();
            if (rundter >= 1) {
                lr.setARRAY(ft.getREDP(), ft.getIRP());
                lr.SPO2();
                lr.Puls();
                System.out.println("SPO2: " + lr.getSPO2() + "%");
                for (int i = 0; i < 500; i++) {
                    db.skrivData(ft.IRP[i], ft.REDP[i]);
                }
                db.skrivResultat(lr.getSPO2(), lr.getPuls(),lr.getBPM());
            }
            rundter++;
        }
    }
}
