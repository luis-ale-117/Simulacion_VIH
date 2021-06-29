
package simulavih;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ToolsPanel extends JPanel{
    
    public int celTotales;
    public JLabel generacion,infectadas,infecA,infecB,muertas,sanas, porcentajeSanas;
    
    ToolsPanel(int dimensionSimul, int dimenTool){
        super();
        this.setBounds(dimensionSimul,0, dimenTool, dimensionSimul);
        this.setBackground(Color.gray);
        this.setLayout(null);
        initComponents();
        
        celTotales = dimensionSimul*dimensionSimul;
    }
    
    private void initComponents(){
        generacion = new JLabel("Generacion: ");
        generacion.setBounds(5, 0, 120, 30);
        this.add(generacion);
        
        infectadas = new JLabel("Infectadas: ");
        infectadas.setBounds(5, 31, 120, 30);
        this.add(infectadas);
        
        infecA = new JLabel("Tipo A: ");
        infecA.setBounds(5, 62, 120, 30);
        this.add(infecA);
        
        infecB = new JLabel("Tipo B: ");
        infecB.setBounds(5, 93, 120, 30);
        this.add(infecB);
        
        muertas = new JLabel("Muertas: ");
        muertas.setBounds(5, 124, 120, 30);
        this.add(muertas);
        
        sanas = new JLabel("Sanas: ");
        sanas.setBounds(5, 155, 120, 30);
        this.add(sanas);
        
        porcentajeSanas = new JLabel("%Sanas: ");
        porcentajeSanas.setBounds(5, 186, 120, 30);
        this.add(porcentajeSanas);
    }
    
    public void actualizaDatos(int gen,int cantInfectTotales, int tipA,int tipB,int muert, int sana){
        generacion.setText("Generacion: "+gen);
        infectadas.setText("Infectadas:"+cantInfectTotales);
        infecA.setText("Tipo A: "+tipA);
        infecB.setText("Tipo B: "+tipB);
        muertas.setText("Muertas: "+muert);
        sanas.setText("Sanas: "+sana);
        porcentajeSanas.setText("%Sanas: "+(sana*100.0/celTotales));
    }
}
