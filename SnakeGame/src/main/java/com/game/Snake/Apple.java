package com.game.Snake;

public class Apple {
    private int size;
    private int posX;
    private int posY;

    public Apple(int size){
        this.size = size;
        this.posX = (int) (Math.random() * 500);
        this.posY = (int) (Math.random() * 500);
    }

    public int getPosX(){
        return this.posX;
    }

    public int getPosY(){
        return this.posY;
    }

    public int getSize(){
        return this.size;
    }
}
