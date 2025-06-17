package am.aua.chess.ui;

import javax.swing.*;
import java.awt.*;

public class BoardSquare extends JButton {
    public final boolean isLight;
    private int x;
    private int y;
    public static final Color LIGHT_COLOR = Color.WHITE;
    public static final Color DARK_COLOR = Color.GREEN;

    public static final int BOARD_SQUARE_WIDTH = 80;

    public static final int BOARD_SQUARE_HEIGHT = 80;

    public BoardSquare(boolean isLight, int x, int y){
        this.isLight = isLight;
        if(isLight){
            this.setBackground(LIGHT_COLOR);
        }
        else{
            this.setBackground(DARK_COLOR);
        }

        this.x = x;
        this.y = y;
        this.setSize(BOARD_SQUARE_WIDTH, BOARD_SQUARE_HEIGHT);
    }

    public int[] getCoordinates(){
        int[] coordinates = {this.x, this.y};
        return coordinates;
    }

    public void setPiece(String piece){
        switch(piece){
            case "R":
            case "S":
                this.setIcon(new ImageIcon(".\\gfx\\RookW.png"));
                break;
            case "r":
            case "s":
                this.setIcon(new ImageIcon(".\\gfx\\RookB.png"));
                break;
            case "N":
                this.setIcon(new ImageIcon(".\\gfx\\KnightW.png"));
                break;
            case "n":
                this.setIcon(new ImageIcon(".\\gfx\\KnightB.png"));
                break;
            case "B":
                this.setIcon(new ImageIcon(".\\gfx\\BishopW.png"));
                break;
            case "b":
                this.setIcon(new ImageIcon(".\\gfx\\BishopB.png"));
                break;
            case "K":
            case "L":
                this.setIcon(new ImageIcon(".\\gfx\\KingW.png"));
                break;
            case "k":
            case "l":
                this.setIcon(new ImageIcon(".\\gfx\\KingB.png"));
                break;
            case "Q":
                this.setIcon(new ImageIcon(".\\gfx\\QueenW.png"));
                break;
            case "q":
                this.setIcon(new ImageIcon(".\\gfx\\QueenB.png"));
                break;
            case "P":
                this.setIcon(new ImageIcon(".\\gfx\\PawnW.png"));
                break;
            case "p":
                this.setIcon(new ImageIcon(".\\gfx\\PawnB.png"));
                break;
        }
    }
    public void setPiece(){
        this.setIcon(null);
    }
    
    public void setHighlight(boolean isHighlighted){
        if(isHighlighted){
            this.setBackground(Color.RED);
        }
        else{
            if(this.isLight){
                this.setBackground(LIGHT_COLOR);
            }
            else{
                this.setBackground(DARK_COLOR);
            }
        }
    }
}
