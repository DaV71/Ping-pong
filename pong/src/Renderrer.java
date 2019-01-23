import javax.swing.*;
import java.awt.*;

public class Renderrer extends JPanel{
    public static final long serialVersionUID=1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Pong.pong.repaint(g);
    }
}
