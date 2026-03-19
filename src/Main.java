import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Bully the Bots - Wave Survival");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 👉 ECHTES FULLSCREEN (kein Rahmen, ganzer Monitor)
        window.setUndecorated(true);           // kein Fensterrahmen
        window.setExtendedState(JFrame.MAXIMIZED_BOTH); // voller Bildschirm
        
        MenuPanel menu = new MenuPanel(window);
        window.add(menu);
        window.setVisible(true);
    }
}