package Opgaver;

public class Nedboer {
    private static int[] nedboerPrUge = { 20, 10, 12, 12, 13, 14, 15, 10, 8, 7, 13,
        15, 10, 9, 6, 8, 12, 22, 14, 16, 16, 18, 23, 12, 0, 2, 0, 0, 78, 0,
        0, 0, 34, 12, 34, 23, 23, 12, 44, 23, 12, 34, 22, 22, 22, 22, 18,
        19, 21, 32, 24, 13 };

    public static void main(String[] args) {
        System.out.println(bedsteTreFerieUger());
        System.out.println(bedsteFerieUgerStart(3));
        System.out.println(ensNedboer());
    }
    /**
     * Returnerer ugenummeret for den uge i året, hvor man skal starte ferien,
     * hvis man ønsker den minimale nedbørsmængde i de tre uger
     *
     * @return
     */

    public static int FerieUger() {
        int current = 1;
        int maxTræk = 1;
        int maxUgeITræk = 0;
        for (int i = 1; i < nedboerPrUge.length; i++) {
            if (nedboerPrUge[i] == nedboerPrUge[i - 1]) {
                current++;

                if (current > maxTræk) {
                    maxTræk = current;
                    maxUgeITræk = i - maxTræk + 1;
                }
            } else {
                current = 1;
            }
        }

        return maxUgeITræk + 1;
    }

    public static int bedsteTreFerieUger() {
        int min = nedboerPrUge[0] + nedboerPrUge[1] + nedboerPrUge[2];
        int current = 0;
        int uge = -1;

        for (int i = 1; i < nedboerPrUge.length; i++) {
            if (i <= 49) {
                current = nedboerPrUge[i] + nedboerPrUge[i + 1] + nedboerPrUge[i + 2];
            }
            if (i == nedboerPrUge[nedboerPrUge.length - 2]) {
                current = nedboerPrUge[i] + nedboerPrUge[i + 1] + nedboerPrUge[0];
            } else if (i == nedboerPrUge[nedboerPrUge.length - 1]) {
                current = nedboerPrUge[i] + nedboerPrUge[0] + nedboerPrUge[1];
            }
            if (current < min) {
                uge = i;
                min = current;
            }
        }
        // + 1 så vi ikke får indeks men ugenummer
        return uge + 1;
    }
    
    /**
     * Returnerer ugenummeret for den uge i året, hvor man skal starte ferien,
     * hvis man ønsker den minimale nedbørsmængde i det "antal" uger, der er
     * angivet i paramtereren
     *
     * @return
     */
    
    public static int bedsteFerieUgerStart(int antal) {
        int current = 0;
        int min = Integer.MAX_VALUE;
        int uge = 0;



        for (int i = 0; i < nedboerPrUge.length - antal; i++) {
            current = 0;
            for (int j = i; j < i + antal; j++) {
                current += nedboerPrUge[j];
            }


            if (current < min) {
                min = current;
                uge = i;
            }
        }

        return uge + 1;
    }
    
    /**
     * Returnerer ugenummeret på den første uge hvor nedbøren har været præcis
     * den samme flest uger i træk
     *
     * @return
     */


    public static int ensNedboer() {
        int ugerITræk = 1;
        int maxUgerITræk = 1;
        int ugenr = 0;

        for (int i = 1; i < nedboerPrUge.length; i++) {
            if (nedboerPrUge[i] == nedboerPrUge[i - 1]) {
                ugerITræk++;
            } else {
                if (ugerITræk > maxUgerITræk) {
                    maxUgerITræk = ugerITræk;
                    ugenr = i - maxUgerITræk;
                }
                ugerITræk = 1;
            }
        }
        return ugenr + 1;
    }
}

