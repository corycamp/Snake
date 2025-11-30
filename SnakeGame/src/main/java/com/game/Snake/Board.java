package com.game.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements Runnable {

    private int x,y;
    private String direction;
    private Boolean running;

    public Board(){
        this.setFocusable(true);
        this.setSize(500,500);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.x = 50;
        this.y = 50;
        setupKeyBindings();
        this.running = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(this.x,this.y,50,50);

    }

    @Override
    public void run() {
        while(running){
            repaint();
            if (this.direction != null) {
                switch (this.direction){
                    case "right":
                        this.x += 5;
                        break;
                    case "left":
                        this.x -= 5;
                        break;
                    case "up":
                        this.y -= 5;
                        break;
                    case "down":
                        this.y += 5;
                        break;
                    default:
                        System.out.println("No direction");
                        break;
                }
            }

            if(this.x > this.getWidth() - 50 || this.x < 0 || this.y < 0 || this.y > this.getHeight() - 55){
                this.running = false;
            }
            try{
                Thread.sleep(16);
            }catch(Exception e){
                System.out.println("Error " + e.getMessage());
            }
        }
    }

    private void setupKeyBindings() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "moveUp");
        getActionMap().put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                direction = "up";
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "moveDown");
        getActionMap().put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                direction = "down";
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "moveLeft");
        getActionMap().put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               direction = "left";
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "moveRight");
        getActionMap().put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                direction="right";
            }
        });
    }

}
