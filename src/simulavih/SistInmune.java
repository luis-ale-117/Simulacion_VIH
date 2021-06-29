
package simulavih;

public class SistInmune {
    public Celula[][] regilla;//Representacion del sistema inmune
    private double probReplace;//Probabilidad de remplazar una celula muerta
    private double probInfect;//Probabilidad de que el reemplzo se infecte al nacer
    private int dimension;//Tamano de una simulacion cuadrada
    private double porcenInfectadasInicial;//Porcentaje de celulas infectadas al inicio de la simulacion
    
    private int cantidadSanas;
    private int cantidadInfectA;
    private int cantidadInfectB;
    private int cantidadMuertas;
    private int cantidadTotalIntectadas;

    public SistInmune(int L,double probReplace,double probInfect,double porcenInfectadasInicial){
        this.dimension = L;
        this.regilla = new Celula[L][L];
        this.probReplace=probReplace;
        this.probInfect = probInfect;
        this.porcenInfectadasInicial = porcenInfectadasInicial;
        
        this.cantidadSanas = 0;
        this.cantidadInfectA = 0;
        this.cantidadInfectB = 0;
        this.cantidadMuertas = 0;
        this.cantidadTotalIntectadas = 0;
    }

    public Celula[][] getRegilla(){
        Celula[][] regAux = new Celula[dimension][dimension];
        for(int y=0;y<dimension;y++){
            for(int x=0;x<dimension;x++){
             regAux[y][x] = this.regilla[y][x].getCelula();
            }            
        }
        return regAux;
    }

    /*Inicializa la regilla de celulas con celulas infectadas aleatoriamente*/
    public void iniciaSistInmuneInfectado(){
        for(int y=0;y<dimension;y++){
            for(int x=0;x<dimension;x++){
                if(Math.random()<=this.porcenInfectadasInicial){
                    this.regilla[y][x] = new Celula('A');
                    this.cantidadInfectA++;
                    this.cantidadTotalIntectadas++;
                }
                else{
                    this.regilla[y][x] = new Celula('S');
                    this.cantidadSanas++;
                }
            }            
        }
    }
    
    /*Inicializa la regilla de celulas con celulas sanas*/
    public void iniciaSistInmuneSano(){
        for(int y=0;y<dimension;y++){
            for(int x=0;x<dimension;x++){
                this.regilla[y][x] = new Celula('S');
                this.cantidadSanas++;
            }            
        }
    }
    
    /*Copia un sistema inmune en otro*/
    public void copiaSistInmune(SistInmune sis){
        for(int y=0;y<dimension;y++){
            for(int x=0;x<dimension;x++){
                this.regilla[y][x].copiaCelula(sis.regilla[y][x]);
            }
        }
    }
    
    /*Devuelve el tipo de celula en que se convertira una celula sana en la siguiente
    iteracion de acuerdo al numero de vecinos infectados que rodeen a la celula*/
    /*De acuerdo a los vecinos de la celula sana, esta se infecta o no.
    Nota: para las celulas de los bordes se considera que los vecinos
    fuera de los limietes son Sanas 'S'.*/
    private char futuroDeCelSana(int x,int y){
        int numInfA=0;
        int numInfB=0;
        for(int yAux =y-1 ; yAux<=y+1; yAux++){
            for(int xAux =x-1 ; xAux<=x+1; xAux++){
                /*Si no esta fuera de los bordes, checa el estado de los vecinos*/
                if(xAux != -1 && xAux != dimension && yAux!=-1 && yAux!=dimension){
                    /*Checa el numero de infectadas A*/
                    if(this.regilla[yAux][xAux].esInfectadaA()){
                        numInfA++;
                    }
                    /*Checa el numero de infectadas B*/
                    else if(this.regilla[yAux][xAux].esInfectadaB()){
                        numInfB++;
                    }
                }
            }
        }
        if(numInfA>=Celula.getResA() || numInfB>=Celula.getResB()){
            return 'A';
        }
        else{
            return 'S';
        }
    }

    /*Calula el futuro de la regilla de celulas de la entrada y lo asigna al objeto*/
    public void futuroSistInmune(SistInmune actual){
        char estadoAnterior;
        for(int y=0;y<dimension;y++){
            for(int x=0;x<dimension;x++){
                estadoAnterior = this.regilla[y][x].getEstado();
                if(actual.regilla[y][x].esSana()){
                    this.regilla[y][x].setEstado(actual.futuroDeCelSana(x, y));
                    this.regilla[y][x].setTimeLag(0);
                }
                else if(actual.regilla[y][x].esMuerta()){
                    if(Math.random()<=this.probReplace){
                        if(Math.random()<=this.probInfect){
                            this.regilla[y][x].setEstado('A');
                        }
                        else{
                            this.regilla[y][x].setEstado('S');
                        }
                        this.regilla[y][x].setTimeLag(0);
                    }
                }
                else{
                    this.regilla[y][x].avanzaTiempoCelula(actual.regilla[y][x]);
                }
                if(estadoAnterior!=this.regilla[y][x].getEstado()){
                    
                    cantidadMuertas -= estadoAnterior=='M'?1:0;
                    cantidadInfectA -= estadoAnterior=='A'?1:0;
                    cantidadInfectB -= estadoAnterior=='B'?1:0;
                    cantidadSanas   -= estadoAnterior=='S'?1:0;
                    
                    cantidadMuertas += this.regilla[y][x].esMuerta()?1:0;
                    cantidadInfectA += this.regilla[y][x].esInfectadaA()?1:0;
                    cantidadInfectB += this.regilla[y][x].esInfectadaB()?1:0;
                    cantidadSanas   += this.regilla[y][x].esSana()?1:0;
                }
            }
        }
    }
    
    public int getCantidadTotalInfectadas(){
        return cantidadInfectA + cantidadInfectB;
    }
    public int getCantidadInfectadasA(){
        return cantidadInfectA;
    }
    public int getCantidadInfectadasB(){
        return cantidadInfectB;
    }
    public int getCantidadMuertas(){
        return cantidadMuertas;
    }
    public int getCantidadSanas(){
        return cantidadSanas;
    }
    public int getTotal(){
        return cantidadInfectA + cantidadInfectB + cantidadSanas + cantidadMuertas;
    }
}