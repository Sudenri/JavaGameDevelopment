package game.entity;

import game.infra.GamePanel;
import game.infra.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;

public class Player extends Entity {

    private final String DIRECTION_RIGHT = "right";
    private final String DIRECTION_LEFT = "left";
    private final String DIRECTION_UP = "up";
    private final String DIRECTION_DOWN = "down";
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle(8, 16, 28, 28);

        setDefaultValues();
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 4;
        getPlayerImage();
        direction = DIRECTION_DOWN;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                direction = DIRECTION_UP;
            } else if (keyHandler.downPressed) {
                direction = DIRECTION_DOWN;
            } else if (keyHandler.leftPressed) {
                direction = DIRECTION_LEFT;
            } else if (keyHandler.rightPressed) {
                direction = DIRECTION_RIGHT;
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn) {
                switch (direction) {
                    case DIRECTION_UP -> worldY -= speed;
                    case DIRECTION_DOWN -> worldY += speed;
                    case DIRECTION_LEFT -> worldX -= speed;
                    case DIRECTION_RIGHT -> worldX += speed;
                }
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                switch (spriteNumber) {
                    case 1 -> spriteNumber = 2;
                    case 2 -> spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case DIRECTION_UP -> {
                switch (spriteNumber){
                    case 1 -> image = up1;
                    case 2 -> image = up2;
                }
            }
            case DIRECTION_DOWN -> {
                switch (spriteNumber){
                    case 1 -> image = down1;
                    case 2 -> image = down2;
                }
            }
            case DIRECTION_LEFT -> {
                switch (spriteNumber){
                    case 1 -> image = left1;
                    case 2 -> image = left2;
                }
            }
            case DIRECTION_RIGHT -> {
                switch (spriteNumber){
                    case 1 -> image = right1;
                    case 2 -> image = right2;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }


}
