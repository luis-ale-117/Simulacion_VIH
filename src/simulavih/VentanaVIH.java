
package simulavih;

import javax.swing.JFrame;
//import javax.swing.JPanel;
import java.awt.Color;
//import java.awt.Dimension;//
import java.awt.Graphics;


public class VentanaVIH extends JFrame{
    public VentanaVIH(int dimension,int barraTools){
        this.setSize(dimension+barraTools,dimension);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Simulacion de propagacion del VIH");
        this.setResizable(false);
        this.setLayout(null);
    }
}
