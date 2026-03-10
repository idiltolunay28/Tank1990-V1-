package Tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.imageio.ImageIO;

abstract public class Bullets {
    protected static final int width = 900;
    protected static final int height = 600;

    protected int x, y;
    protected int speed;
    protected boolean isAlive;
    protected String direction;
    protected int power;
    protected boolean isBoosted;
    protected final int size = 14;

    protected BufferedImage bulletImage;

    Bullets(int x, int y, int speed, int power, String direction) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        isAlive = true;
        this.direction = direction;
        this.power = power;
        this.isBoosted = false;

        // try {
        // bulletImage =
        // ImageIO.read(getClass().getResourceAsStream("/Resources/Bullet_Alt.png"));
        // } catch (Exception e) {
        // e.printStackTrace();
        // System.out.println("Bullet null");
        // }

    }

    public void move(List<Components> map) {
        int newX = x;
        int newY = y;
        switch (direction) {
            case "up":
                newY = y + (-1 * speed);
                if (!checkCollision(map, newX, newY)) {
                    x = newX;
                    y = newY;
                }
                break;
            case "down":
                newY = y + (1 * speed);
                if (!checkCollision(map, newX, newY)) {
                    x = newX;
                    y = newY;
                }
                break;
            case "left":
                newX = x + (-1 * speed);
                if (!checkCollision(map, newX, newY)) {
                    x = newX;
                    y = newY;
                }
                break;
            case "right":
                newX = x + (1 * speed);
                if (!checkCollision(map, newX, newY)) {
                    x = newX;
                    y = newY;
                }
                break;

        }

    }

    public boolean checkCollision(List<Components> map, int Xcord, int Ycord) {
        int tileX = Xcord / 64;
        int tileY = Ycord / 64;

        if (tileX < 0 || tileX >= 832 || tileY < 0 || tileY >= 832) {
            return true;
        }

        for (Components comp : map) {
            if (comp instanceof UnWalkable) {
                if (comp.getX() == tileX && comp.getY() == tileY) {
                    return true;
                }
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(x, y, 8, 8);
    }

    public void setImage(String newdirection) {
        switch (newdirection) {
            case "up":
                try {
                    bulletImage = ImageIO.read(getClass().getResourceAsStream("/Resources/Bullet_Ust.png"));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Bullet Ust null");
                }
                break;
            case "down":
                try {
                    bulletImage = ImageIO.read(getClass().getResourceAsStream("/Resources/Bullet_Alt.png"));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Bullet Alt null");
                }
                break;
            case "left":
                try {
                    bulletImage = ImageIO.read(getClass().getResourceAsStream("/Resources/Bullet_Sol.png"));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Bullet Sol null");
                }
                break;
            case "right":
                try {
                    bulletImage = ImageIO.read(getClass().getResourceAsStream("/Resources/Bullet_Sag.png"));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Bullet Sag null");
                }
                break;
        }
    }

    public int getSize() {
        return size;
    }

    public int getWidth() {
        return this.bulletImage.getWidth() * 4;
    }

    public int getHeight() {
        return this.bulletImage.getHeight() * 4;
    }

    public boolean getAlive() {
        return this.isAlive;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPower() {
        return this.power;
    }

}

class PlayerBullet extends Bullets {
    protected BufferedImage bulletImage;

    PlayerBullet(int x, int y, int speed, int power, String direction) {
        super(x, y, speed, power, direction);
    }

}

class TankBullet extends Bullets {
    protected BufferedImage bulletImage;

    TankBullet(int x, int y, int speed, int power, String direction) {
        super(x, y, speed, power, direction);

    }

}
