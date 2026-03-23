package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed = false;
    public boolean downPressed = false;
    public boolean leftPressed = false;
    public boolean rightPressed = false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int pressedKeyCode = e.getKeyCode();

        if(pressedKeyCode == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(pressedKeyCode == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(pressedKeyCode == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(pressedKeyCode == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int pressedKeyCode = e.getKeyCode();

        if(pressedKeyCode == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(pressedKeyCode == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(pressedKeyCode == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(pressedKeyCode == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
