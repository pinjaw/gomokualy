
package logiikka;

/**
 * Pelilauta.
 */

public class Lauta {
    
    private int pituus;
    private char[][] lauta;
    private int[][] suunnat;
    private char vuorossa;
    private int vuoroja;
    private boolean tasapeli;
    
    public Lauta() {
        pituus = 15;
        lauta = new char[pituus][pituus];
        suunnat = new int[][]{{0, 1, 0, -1}, {1, 0, -1, 0}, {-1, -1, 1, 1}, {-1, 1, 1, -1}};
        alustaLauta();
        vuorossa = 'X';
        vuoroja = 0;
        tasapeli = false;
    }

    public boolean getTasapeli() {
        return tasapeli;
    }

    public int getPituus() {
        return pituus;
    }

    public char[][] getLauta() {
        return lauta;
    }

    public char getVuoro() {
        return vuorossa;
    }
    
    /**
     * Sijoittaa vuorossa olevan pelaajan merkin laudalle annettuihin koordinaatteihin.
     * @param x koordinaatti.
     * @param y koordinaatti.
     * @return true, jos peli p��ttyi ja false, jos peli jatkuu.
     */
    public boolean sijoita(int x, int y) {
        lauta[x][y] = vuorossa;
        vuoroja++;
        int tulos = tarkistaVoitto(x, y);
        if(tulos >= 0) {
            if(tulos == 0) tasapeli = true;
            return true;
        }
        vaihdaVuoro();
        return false;
    }
    
    private void vaihdaVuoro() {
        if(vuorossa == 'X') vuorossa = 'O';
        else vuorossa = 'X';
    }
    
    /**
     * Tarkistaa, onko annettu siirto lopettanut pelin.
     * @param x koordinaatti.
     * @param y koordinaatti.
     * @return -1, jos jatketaan peli�, 1, jos peli p��ttyi ja 0 jos tuli tasapeli.
     */
    private int tarkistaVoitto(int x, int y) {
        boolean voitto = false;
        char merkki = lauta[x][y];
        for (int i = 0; i < 4; i++) {
            if (laskePisinSuora(x, y, suunnat[i], merkki) >= 5) {
                voitto = true;
                break;
            }
        }
        if (voitto) return 1;
        else if (vuoroja == pituus * pituus) return 0;
        return -1;
    }
    
    /**
     * Metodi laskee, mik� on pisin annetun suuntainen suora, jossa koordinaateissa oleva pelimerkki on mukana.
     * @param x koordinaatti.
     * @param y koordinaatti.
     * @param suunta tutkittava suunta (vaakasuora, pystysuora tai vasen- tai oikea vinosuora).
     * @param merkki pelimerkki, X tai O. Kertoo kumman pelaajan suoraa lasketaan.
     * @return pisimm�n annetun suuntaisen suoran pituus.
     */
    private int laskePisinSuora(int x, int y, int[] suunta, char merkki) {
        int summa = 0;
        int alkux = x;
        int alkuy = y;
        while (laudalla(alkux, alkuy) && lauta[alkux][alkuy] == merkki) {
            summa++;
            alkux += suunta[0];
            alkuy += suunta[1];
        }
        alkux = x + suunta[2];
        alkuy = y + suunta[3];
        while (laudalla(alkux, alkuy) && lauta[alkux][alkuy] == merkki) {
            summa++;
            alkux += suunta[2];
            alkuy += suunta[3];
        }
        return summa;
    }
    
    /**
     * Alustaa pelilaudan tyhj�ll� merkill�.
     */
    private void alustaLauta() {
        for (int i = 0; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                lauta[i][j] = '+';
            }
        }
    }
    
    private boolean laudalla(int x, int y) {
        if (x >= 0 && x < pituus && y >= 0 && y < pituus) return true;
        return false;
    }
    
    /**
     * Tulostaa pelilaudan.
     */
    public void tulostaLauta() {
        System.out.println("");
        System.out.println("ohjeet(o) pelis��nn�t(p) lopeta(x)");
        int vaakarivi = pituus;
        for (int i = 0; i < pituus; i++) {
            if (vaakarivi < 10) System.out.print(" ");
            System.out.print(vaakarivi + " ");
            for (int j = 0; j < pituus; j++) {
                if (j == pituus - 1) System.out.print(lauta[i][j]);
                else System.out.print(lauta[i][j] + " ");
            }
            System.out.println("");
            vaakarivi--;
        }
        System.out.print("   ");
        char asti = 'S';
        if (pituus == 15) asti = 'O';
        for (char i = 'A'; i <= asti; i++) {
            if (i == asti) System.out.print(i);
            else System.out.print(i + " ");
        }
        System.out.println("");
    }
    
    /**
     * Muuttaa pelitilannetta kuvaavan char[][]-taulukon merkkijonoksi.
     * @param lauta
     * @return pelitilanne merkkijonona.
     */
    static String muutaMerkkijonoksi(char[][] lauta) {
        char[] merkkijono = new char[lauta.length * lauta.length];
        int idx = 0; 
        for (int i = 0; i < lauta.length; i++) {
            for (int j = 0; j < lauta.length; j++) {
                merkkijono[idx] = lauta[i][j];
                idx++;
            }
        }
        return new String(merkkijono);
    }
    
    
    //TESTI-METODIT
    
    /**
     * Testimetodi. Metodia k�ytet��n apuna JUnit-testauksessa.
     * @param x 
     */
    public void setVariTesti(char x) {
        this.vuorossa = x;
    }
    
    /**
     * Testimetodi. Metodia k�ytet��n apuna JUnit-testauksessa.
     */
    public void alustaLautaTesti() {
        for (int i = 0; i < pituus; i++) {
            for (int j = 0; j < pituus; j++) {
                lauta[i][j] = '+';
            }
        }
    }
}