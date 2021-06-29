
package simulavih;

/* Una celula del sistema inmune puede estar en los siguientes estados:
 * 'S' : sana
 * 'A' : infectada A
 * 'B' : infectada B
 * 'M' : muerta
 */

public class Celula {
    private char estado;
    static private int resA;//Resistencia limite a celulas A
    static private int resB;//Resistencia limite a celulas B
    static private int tau;//Pasos para cambiar a B o M
    private int timeLag;//Contador de pasos para cambiar a B o M

    public Celula(char estado){
        this.estado = estado;
        this.timeLag = 0;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public static int getResA() {
        return resA;
    }

    public static void setResA(int resA) {
        Celula.resA = resA;
    }

    public static int getResB() {
        return resB;
    }

    public static void setResB(int resB) {
        Celula.resB = resB;
    }

    public int getTimeLag() {
        return timeLag;
    }

    public void setTimeLag(int timeLag) {
        this.timeLag = timeLag;
    }

    public static int getTau() {
        return tau;
    }

    public static void setTau(int tau) {
        Celula.tau = tau;
    }
    
    public Celula getCelula(){
        Celula celAux = new Celula(this.estado);
        celAux.setTimeLag(this.timeLag);
        return celAux;
    }
    
    public void copiaCelula(Celula copia){
        this.estado = copia.getEstado();
        this.timeLag = copia.getTimeLag();
    }
    
    public void avanzaTiempoCelula(){
        if(this.estado == 'A' && this.timeLag < tau){
            this.timeLag++;
        }
        else if(this.estado == 'A' && this.timeLag == tau){
            this.timeLag++;
            this.estado = 'B';
        }
        else if(this.estado == 'B' && this.timeLag > tau){
            this.estado = 'M';
        }
    }
    public void avanzaTiempoCelula(Celula presente){
        if(presente.estado == 'A' && presente.timeLag < tau){
            this.estado = 'A';
            this.timeLag = presente.timeLag + 1;
        }
        else if(presente.estado == 'A' && presente.timeLag == tau){
            this.timeLag = tau+1;
            this.estado = 'B';
        }
        else if(presente.estado == 'B' && presente.timeLag > tau){
            this.estado = 'M';
        }
    }
    
    public boolean esSana(){
        return this.estado=='S';
    }
    public boolean esInfectadaA(){
        return this.estado=='A';
    }
    public boolean esInfectadaB(){
        return this.estado=='B';
    }
    public boolean esMuerta(){
        return this.estado=='M';
    }
}