import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Sniper extends Player {

    private boolean isAttacking = false;
    private int attackTimer = 0;
    private double baseAngle = 0;
    private int mouseX = 960;
    private int mouseY = 540;

    public Sniper(int x, int y) {
        super(x, y, 140, 55, new Staff());
        speed = 4;
    }

    public void setMousePosition(int x, int y) {
        this.mouseX = x;
        this.mouseY = y;
    }

    @Override
    public Projectile mouseAttack(int mx, int my, ArrayList<Enemy> enemies) {
        if (isAttacking) return null;
        isAttacking = true;
        attackTimer = 25;
        return new Projectile(getX() + 10, getY() + 10, mx, my, getDamage() + 35); // FIX: ohne Color
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

        g2.setColor(new Color(50, 50, 50));
        g2.fillRect(px - 13, py - 13, 26, 26);

        double angle = baseAngle;
        AffineTransform old = g2.getTransform();
        g2.rotate(Math.toRadians(angle), px + 10, py + 8);

        g2.setColor(new Color(40, 40, 40));
        g2.fillRect(px + 15, py + 5, 18, 6);
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(px + 30, py + 4, 65, 7);
        g2.setColor(Color.RED);
        g2.fillRect(px + 85, py + 2, 8, 11);

        g2.setTransform(old);
    }
}