
package simulavih;

import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SimulaVIH {
    
    private static final int dimensionSimul=700;
    private static final int dimenTools=200;

    public static void main(String[] args) {
        /*  GUI  */
        VentanaVIH v1 = new VentanaVIH(dimensionSimul,dimenTools);
        v1.setVisible(true);
        SimulPanel l = new SimulPanel(dimensionSimul);
        v1.add(l);
        ToolsPanel tool = new ToolsPanel(dimensionSimul,dimenTools);
        v1.add(tool);
        
        BufferedImage buffImg = new BufferedImage(dimensionSimul,dimensionSimul,BufferedImage.TYPE_INT_RGB);
        int colorAlive,colorDead,colorInfecA,colorInfecB;
        colorAlive = 0x0000ff;//Blue
        colorDead = 0xff0000;//Red
        colorInfecA = 0xffff00;//Yellow
        colorInfecB = 0x00ff00;//Green
        
        /*  VIH  */
        Celula.setResA(1);
        Celula.setResB(4);
        Celula.setTau(4);
        
        SistInmune sisPresente = new SistInmune(dimensionSimul,0.99,0.0001,0.05);
        SistInmune copyFuturo = new SistInmune(dimensionSimul,0.99,0.0001,0.05);
        SistInmune aux;
        sisPresente.iniciaSistInmuneInfectado();
        copyFuturo.iniciaSistInmuneSano();
        //copyFuturo.copiaSistInmune(sisPresente);
        
        int i=0;
       
        while(i<1000){
            /*Asigna la regilla de celulas del sistema original*/
            for(int y = 0;y<dimensionSimul;y++){
                for(int x = 0;x<dimensionSimul;x++){
                    /*Muestra las celulas*/
                    if(sisPresente.regilla[y][x].esSana()){
                        buffImg.setRGB(x, y, colorAlive);
                    }else if(sisPresente.regilla[y][x].esInfectadaA()){
                        buffImg.setRGB(x, y, colorInfecA);
                    }else if(sisPresente.regilla[y][x].esInfectadaB()){
                        buffImg.setRGB(x, y, colorInfecB);
                    }else{
                        buffImg.setRGB(x, y, colorDead);
                    }
                }
            }
            
            tool.actualizaDatos(i+1,sisPresente.getCantidadTotalInfectadas()
                    ,sisPresente.getCantidadInfectadasA(),sisPresente.getCantidadInfectadasB()
                    ,sisPresente.getCantidadMuertas(),sisPresente.getCantidadSanas());
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(SimulaVIH.class.getName()).log(Level.SEVERE, null, ex);
            }
            l.actualizaVistaSisInmune(buffImg);
            
            //copyFuturo.copiaSistInmune(sisPresente);//Sistema copia (futuro)  = original (presente)s
            copyFuturo.futuroSistInmune(sisPresente);//Sistema copia ya es el futuro de original, es sobreescrita
            
            aux = sisPresente;
            sisPresente = copyFuturo;
            copyFuturo = aux;
            
            i++;
                        
        }                               
    }    
}
