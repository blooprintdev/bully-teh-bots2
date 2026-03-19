import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Mage extends Player {

    private boolean isAttacking = false;
    private int attackTimer = 0;
    private double baseAngle = 0;
    private int mouseX = 960;
    private int mouseY = 540;

    public Mage(int x, int y) {
        super(x, y, 180, 45, new Staff());
        speed = 5;   // FIX: int statt double
    }

    public void setMousePosition(int x, int y) {
        this.mouseX = x;
        this.mouseY = y;
    }

    @Override
    public Projectile mouseAttack(int mx, int my, ArrayList<Enemy> enemies) {
        if (isAttacking) return null;
        isAttacking = true;
        attackTimer = 10;
        return new Projectile(getX() + 10, getY() + 10, mx, my, getDamage() + 12); // FIX: ohne Color
    }

    @Override
    public void update() {
        super.update();
        if (!isAttacking) {
            double dx = mouseX - getX();
            double dy = mouseY - getY();
            baseAngle = Math.toDegrees(Math.atan2(dy, dx));
        }
        if (isAttacking) attackTimer--;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int px = getX();
        int py = getY();

        g2.setColor(new Color(80, 40, 140));
        g2.fillRect(px - 12, py - 12, 24, 24);

        double angle = baseAngle;
        AffineTransform old = g2.getTransform();
        g2.rotate(Math.toRadians(angle), px + 10, py + 8);

        g2.setColor(new Color(50, 20, 100));
        g2.fillRect(px + 18, py + 5, 10, 6);
        g2.setColor(new Color(120, 200, 255));
        g2.fillRect(px + 26, py + 3, 42, 8);
        g2.setColor(new Color(200, 255, 255));
        g2.fillOval(px + 62, py + 1, 14, 14);

        g2.setTransform(old);
    }
}