package game.infra;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, upRightPressed, downRightPressed, upLeftPressed, downLeftPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_I -> upLeftPressed = true;
            case KeyEvent.VK_K -> downRightPressed = true;
            case KeyEvent.VK_L -> upRightPressed = true;
            case KeyEvent.VK_J -> downLeftPressed = true;
            case KeyEvent.VK_W, KeyEvent.VK_UP -> upPressed = true;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPressed = true;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPressed = true;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_I -> upLeftPressed = false;
            case KeyEvent.VK_K -> downRightPressed = false;
            case KeyEvent.VK_L -> upRightPressed = false;
            case KeyEvent.VK_J -> downLeftPressed = false;
            case KeyEvent.VK_W, KeyEvent.VK_UP -> upPressed = false;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPressed = false;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPressed = false;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPressed = false;
        }

    }
}
