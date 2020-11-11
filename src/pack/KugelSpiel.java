package pack;

import java.util.Random;

public class KugelSpiel {
    public static final int MR = 100; //MAX_RUNDEN
    public static final int MK = 200; //MAX_KUGELN
    public static final boolean DEBUG = false;

    public  static int Faktor(){ //Kugeln pro Runde
        int faktor = MK;
        faktor = faktor / MR;
        return  faktor;
    }

    public static int StratZufall(int rest){
        Random random = new Random();
        int zufall = random.nextInt(9);
        int kugel = 0;
        switch (zufall){
            case 0:
            case 1:
            case 2:
                kugel = Faktor() -1;
                break;
            case 3:
            case 4:
            case 5:
                kugel = Faktor() ;
                break;
            case 6:
            case 7:
            case 8:
                kugel = Faktor() +1;
                break;
        }

        if(rest > kugel){
            return kugel;
        }
        return rest;
    }

    public static int StratSchlauerAlsZufall(int rest){
        Random random = new Random();
        int zufall = random.nextInt(9);
        int kugel = 0;
        switch (zufall){
            case 0:
            case 1:
            case 2:
                kugel = 0;
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                kugel = Faktor() +1;
        }

        if(rest > kugel){
            return kugel;
        }
        return rest;
    }
    public static  void ausgRunde(int von, int bis){
        if(von > 1){
            System.out.println();
        }
        System.out.println("Runde "+ von+ " von "+ bis);
    }
    public static void ausgDebug(int p, int k,int s, int r){
        System.out.println("\tSpieler"+p+" stetzt: "+k+" Kugel(n), Spielstand: "+s+" Punkt(e) und "+r+" Restkugel(n)");
    }
    public static void ausgErg(int p,int s, int r){
        System.out.println("\tSpieler"+p+": Spielstand: "+s+" Punkt(e) und "+r+" Restkugel(n)");
    }


    public static  void AlleRunden(){
        int KS1 = MK; //Kugeln Spieler 1
        int KS2 = MK; //Kugeln Spieler 2

        int SiegS1 =0 ; //Siege Spieler 1
        int SiegS2 =0 ; //Siege Spieler 1

        for(int r = 0; r < MR; r++){
            int k1 = StratZufall(KS1);
            int k2 = StratSchlauerAlsZufall(KS2);

            //Kugeln abziehn
            KS1 = KS1 - k1;
            KS2 = KS2 - k2;

            //siege
            if(k1 > k2){
                SiegS1 = SiegS1 +1;
            }
            if(k1 < k2){
                SiegS2 = SiegS2 +1;
            }

            if(DEBUG){
                ausgRunde(r, MR);
                ausgDebug(1,k1,SiegS1,KS1);
                ausgDebug(2,k2,SiegS2,KS2);
            }

        }
        if(!DEBUG){
            ausgErg(1,SiegS1,KS1);
            ausgErg(2,SiegS2,KS2);
        }
    }

    public static void main(String[] args) {
	// write your code here
        AlleRunden();
    }
}