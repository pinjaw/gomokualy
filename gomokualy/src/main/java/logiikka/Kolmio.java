
package logiikka;

public class Kolmio implements Comparable<Kolmio> {
    
    private long arvo;
    private int x;
    private int y;
    
    public Kolmio(long arvo, int x, int y) {
        this.arvo = arvo;
        this.x = x;
        this.y = y;
    }

    public long getArvo() {
        return arvo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setArvo(int arvo) {
        this.arvo = arvo;
    }
    
    public int compareTo(Kolmio k) {
        if(this.arvo -  k.arvo < 0) return -1;
        else if (this.arvo -  k.arvo > 0) return 1;
        return 0; 
    }
    
}
