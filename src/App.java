import javax.swing.*;
import java.awt.*;
public class App {
    public static void main(String[] args) throws Exception {
        JFrame f=new JFrame();
        Gameplay gp= new Gameplay();
        f.add(gp);
        f.setBounds(10,10,700,700);
        f.setTitle("Brick Breaker Game");
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
