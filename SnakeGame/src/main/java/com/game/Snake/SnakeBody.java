package com.game.Snake;

import java.awt.*;

public class SnakeBody {
    private static final int SIZE = 30;
    private final BodyPart head;

    public SnakeBody(){
        this.head = new BodyPart(50,50);
    }

    public int getPosX(){
        return this.head.getPosX();
    }

    public int getPosY(){
        return this.head.getPosY();
    }

    public void drawSnake(Graphics g){
        g.fillRect(this.head.getPosX(),this.head.getPosY(),SIZE,SIZE);
        BodyPart currentPart = this.head;
        while (currentPart.getNextBodyPart() != null){
            currentPart = currentPart.getNextBodyPart();
            g.fillRect(currentPart.getPosX(),currentPart.getPosY(),SIZE,SIZE);
        }
    }

    public BodyPart getLastBodyPart(){
        BodyPart currentPart = this.head;
        while (currentPart.getNextBodyPart()!=null){
            currentPart = currentPart.getNextBodyPart();
        }
        return currentPart;
    }


    public void eat(String direction){
        BodyPart tail = getLastBodyPart();
        BodyPart newTail = switch (direction) {
            case "right" -> new BodyPart(tail.getPosX() - SIZE, tail.getPosY());
            case "left" -> new BodyPart(tail.getPosX() + SIZE, tail.getPosY());
            case "up" -> new BodyPart(tail.getPosX(), tail.getPosY() + SIZE);
            case "down" -> new BodyPart(tail.getPosX(), tail.getPosY() - SIZE);
            default -> null;
        };
        tail.setNextBodyPart(newTail);
    }

    private void moveBody(BodyPart currentPart, int x, int y){
        if(currentPart.getNextBodyPart() != null){
            int currentX = currentPart.getNextBodyPart().getPosX();
            int currentY = currentPart.getNextBodyPart().getPosY();
            currentPart.getNextBodyPart().setPosX(x);
            currentPart.getNextBodyPart().setPosY(y);
            moveBody(currentPart.getNextBodyPart(),currentX, currentY);
        }
    }

    public void moveSnakeHead(String direction){
        int currentX = this.head.getPosX();
        int currentY = this.head.getPosY();
        switch (direction) {
            case "right":
                this.head.setPosX(this.head.getPosX() + 5);
                break;
            case "left":
                this.head.setPosX(this.head.getPosX() - 5);
                break;
            case "up":
                this.head.setPosY(this.head.getPosY() - 5);
                break;
            case "down":
                this.head.setPosY(this.head.getPosY() + 5);
                break;
            default:
                System.out.println("No direction");
                break;
        }
        this.moveBody(this.head, currentX,currentY);
    }
}
