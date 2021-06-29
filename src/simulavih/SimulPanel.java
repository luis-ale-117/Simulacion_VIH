
package simulavih;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SimulPanel extends JPanel{
    
    SimulPanel(int dimension){
        super();
        this.setBounds(0,0, dimension, dimension);
    }
    public void actualizaVistaSisInmune(BufferedImage buffImg){
        Graphics g = this.getGraphics();
        g.drawImage(buffImg, 0, 0, this);
    }
}
