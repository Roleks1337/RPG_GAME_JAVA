package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;

    public boolean upPressed = false;
    public boolean downPressed = false;
    public boolean leftPressed = false;
    public boolean rightPressed = false;
    public boolean enterPressed = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int pressedKeyCode = e.getKeyCode();
        //PLAY STATE
        if(gp.gameState == gp.playState) {
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
            if(pressedKeyCode == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }
            if(pressedKeyCode == KeyEvent.VK_E) {
                enterPressed = true;
            }
        }
        //PAUSE STATE
        else if(gp.gameState == gp.pauseState) {
            if(pressedKeyCode == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }
        //DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState) {
            if(pressedKeyCode == KeyEvent.VK_E) {
                gp.gameState = gp.playState;
            }
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
        if(pressedKeyCode == KeyEvent.VK_E) {
            enterPressed = false;
        }
    }
}
