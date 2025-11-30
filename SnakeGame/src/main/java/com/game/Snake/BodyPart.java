package com.game.Snake;

public class BodyPart {
    private int posX;
    private int posY;
    private BodyPart nextBodyPart;

    public BodyPart(int x, int y){
        this.posX = x;
        this.posY = y;
        this.nextBodyPart = null;
    }

    public void setNextBodyPart(BodyPart part){
        this.nextBodyPart = part;
    }

    public BodyPart getNextBodyPart(){
        return this.nextBodyPart;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }


}
