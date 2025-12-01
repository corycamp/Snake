package com.game.Snake;


import javax.swing.*;

public class Main {

    public static void main(String[] args){
        JFrame frame = new JFrame("Snake");
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CloseFrame close = frame::dispose;
        Board board = new Board(close);
        board.requestFocusInWindow();
        frame.add(board);

        Thread game = new Thread(board);
        game.start();
    }
}
