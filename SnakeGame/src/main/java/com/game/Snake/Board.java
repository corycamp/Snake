package com.game.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements Runnable {
    private static final int SIZE = 30;
    private static final int GIANT = 50;
    private int speed = 5;
    private int score;
    private int x,y;
    private String direction;
    private Boolean running;
    private Boolean paused;
    private Apple apple;
    private SnakeBody snake;

    public Board(){
        this.setFocusable(true);
        this.setSize(500,500);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        setupKeyBindings();
        setupGame();
    }

    private void setupGame(){
        this.x = 50;
        this.y = 50;
        this.score = 0;
        this.apple = new Apple(SIZE);
        this.snake = new SnakeBody();
        this.running = true;
        this.paused = false;
    }

    private void printGame(Graphics g){
        g.setColor(Color.WHITE);
        g.drawString("Score: " + this.score, 520, 10);
        this.snake.drawSnake(g);
        g.setColor(Color.RED);
        g.fillOval(this.apple.getPosX(), this.apple.getPosY(), this.apple.getSize(),this.apple.getSize());
    }

    private void printPauseMenu(Graphics g){
        g.setFont(Font.getFont(Font.SANS_SERIF));
        g.setColor(Color.WHITE);
        g.drawString("PAUSED", 250, 250);
        g.drawString("Press ESC to continue", 250, 300);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!this.paused){
            printGame(g);
        }else {
            printPauseMenu(g);
        }
    }

    public void handleAppleCollision(){
        int x = this.apple.getPosX();
        int y = this.apple.getPosY();
        int currentSize = this.apple.getSize();
        if
        (
            ((x > this.snake.getPosX() && (x < this.snake.getPosX() + SIZE)) || (x + currentSize > this.snake.getPosX() && (x + currentSize < this.snake.getPosX() + SIZE))) &&
            ((y > this.snake.getPosY() && (y < this.snake.getPosY() + SIZE)) || (y + currentSize > this.snake.getPosY() && (y + currentSize < this.snake.getPosY() + SIZE)))
        ){
            this.score += 1;
            this.apple = new Apple(this.score % 5 == 0 ? GIANT : SIZE);
            this.snake.eat(this.direction);
        }
    }

    @Override
    public void run() {
        while(running) {
            repaint();
            if (!paused) {
                if (this.direction != null) {
                    this.snake.moveSnakeHead(this.direction);
                }

                if (this.snake.getPosX() > this.getWidth() - SIZE || this.snake.getPosX() < 0 || this.snake.getPosY() < 0 || this.snake.getPosY() > this.getHeight() - (SIZE + 5)) {
                    this.running = false;
                }
                handleAppleCollision();
                try {
                    Thread.sleep(17);
                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }
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

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "pause");
        getActionMap().put("pause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!paused){
                    paused = true;
                }else{
                    paused = false;
                }
            }
        });

    }

}
