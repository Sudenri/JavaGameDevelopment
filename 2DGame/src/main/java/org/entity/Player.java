package org.entity;

import org.infra.GamePanel;
import org.infra.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    BigDecimal cosineModifier = BigDecimal.valueOf(speed*Math.cos(45));
    BigDecimal sineModifier = BigDecimal.valueOf(speed*Math.sin(45));

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        getPlayerImage();
        direction = "down";
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

        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed || keyHandler.upRightPressed || keyHandler.downRightPressed || keyHandler.upLeftPressed || keyHandler.downLeftPressed) {
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNumber == 1){
                    spriteNumber = 2;
                }
                else if(spriteNumber == 2){
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
        if ((keyHandler.rightPressed && keyHandler.upPressed) || keyHandler.upRightPressed){
            direction = "up";
            x += speed/2;
            y -= speed/2;
        }
        if ((keyHandler.rightPressed && keyHandler.downPressed) || keyHandler.downRightPressed){
            direction = "down";
            x += speed/2;
            y += speed/2;
        }
        if ((keyHandler.leftPressed && keyHandler.upPressed) || keyHandler.upLeftPressed){
            direction = "up";
            x -= speed/2;
            y -= speed/2;
        }
        if ((keyHandler.leftPressed && keyHandler.downPressed) || keyHandler.downLeftPressed){
            direction = "down";
            x -= speed/2;
            y += speed/2;
        }
        else if (keyHandler.upPressed) {
            direction = "up";
            y -= speed;
        } else if (keyHandler.downPressed) {
            direction = "down";
            y += speed;
        } else if (keyHandler.leftPressed) {
            direction = "left";
            x -= speed;
        } else if (keyHandler.rightPressed) {
            direction = "right";
            x += speed;
        }



    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNumber == 1) {
                    image = up1;
                }
                else if (spriteNumber == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNumber == 1) {
                    image = down1;
                }
                else if (spriteNumber == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNumber == 1) {
                    image = left1;
                }
                else if (spriteNumber == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNumber == 1) {
                    image = right1;
                }
                else if (spriteNumber == 2) {
                    image = right2;
                }
            }
        }
        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }


}
