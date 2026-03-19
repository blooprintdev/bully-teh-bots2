import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Warrior extends Player {

    private boolean isAttacking = false;
    private int attackTimer = 0;
    private double baseAngle = 0;
    private double swingOffset = 0;
    private int mouseX = 960;
    private int mouseY = 540;

    public Warrior(int x, int y) {
        super(x, y, 280, 28, new Sword());
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
        attackTimer = 18;
        swingOffset = -58;

        double px = getX() + 12;
        double py = getY() + 8;
        double swordAngle = baseAngle + swingOffset;

        for (Enemy e : enemies) {
            double dx = e.getX() - px;
            double dy = e.getY() - py;
            double dist = Math.hypot(dx, dy);
            if (dist < 92) {
                double enemyAngle = Math.toDegrees(Math.atan2(dy, dx));
                double angleDiff = Math.abs(enemyAngle - swordAngle);
                if (angleDiff > 180) angleDiff = 360 - angleDiff;
                if (angleDiff < 35) {
                    e.takeDamage(getDamage() + 22);
                }
            }
        }
        return null;
    }

    @Override
    public void update() {
        super.update();
        if (!isAttacking) {
            double dx = mouseX - getX();
            double dy = mouseY - getY();
            baseAngle = Math.toDegrees(Math.atan2(dy, dx));
        }
        if (isAttacking) {
            attackTimer--;
            swingOffset += 7.2;
            if (attackTimer <= 0) {
                isAttacking = false;
                swingOffset = 0;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int px = getX();
        int py = getY();

        g2.setColor(new Color(170, 25, 25));
        g2.fillRect(px - 14, py - 14, 28, 28);
        g2.setColor(Color.BLACK);
        g2.drawRect(px - 14, py - 14, 28, 28);

        double finalAngle = baseAngle + swingOffset;
        AffineTransform old = g2.getTransform();
        g2.rotate(Math.toRadians(finalAngle), px + 10, py + 8);

        g2.setColor(new Color(45, 30, 20));
        g2.fillRect(px + 14, py + 4, 14, 7);     // Griff
        g2.setColor(new Color(90, 90, 95));
        g2.fillRect(px + 23, py - 6, 6, 19);     // Parier
        g2.setColor(new Color(225, 230, 240));
        g2.fillRect(px + 28, py + 2, 58, 4);     // lange scharfe Klinge

        g2.setTransform(old);
    }
}