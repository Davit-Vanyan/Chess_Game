package am.aua.chess.ui;

import javax.swing.*;

import am.aua.chess.core.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessUI extends JFrame {
    private Chess game;
    private BoardSquare[][] board;
    private Position origin;
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 1000;
    public static final int CHESS_BOARD_WIDTH = 640;
    public static final int CHESS_BOARD_HEIGHT = 640;

    private Position[] highlightedPositions = null;



    public ChessUI() throws IllegalArrangementException {
        this.game = new Chess();
        board = new BoardSquare[Chess.BOARD_RANKS][Chess.BOARD_FILES];

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle("CS120: Chess");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(Chess.BOARD_RANKS, Chess.BOARD_FILES));
        panel.setSize(CHESS_BOARD_WIDTH, CHESS_BOARD_HEIGHT);
        JPanel chessPanel = new JPanel();
        panel.setPreferredSize(new Dimension(CHESS_BOARD_WIDTH, CHESS_BOARD_HEIGHT));


        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = new BoardSquare((i+j)%2==0 ? true : false, i, j);
                this.board[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boardClicked(((BoardSquare)e.getSource()).getCoordinates());

                    }
                });
                panel.add(board[i][j]);
            }
        }
        updatePieces();



        chessPanel.add(panel);
        chessPanel.setBounds(180, 10, CHESS_BOARD_WIDTH, CHESS_BOARD_HEIGHT);

        this.add(chessPanel, BorderLayout.CENTER);


        this.setVisible(true);

    }



    private void boardClicked(int[] coordinates){

        if(this.origin==null){
            if(this.game.getPieceAt(Position.generateFromRankAndFile(coordinates[0], coordinates[1]))==null){
                return;
            }
            else if(game.getTurn()==this.game.getPieceAt(Position.generateFromRankAndFile(coordinates[0], coordinates[1])).getPieceColor()) {
                this.origin = Position.generateFromRankAndFile(coordinates[0], coordinates[1]);
                this.highlightedPositions = this.game.getPieceAt(origin).allDestinations(this.game, this.origin);
                        for (int k = 0; k < this.highlightedPositions.length; k++) {
                            if (Position.generateFromRankAndFile(highlightedPositions[k].getRank(), highlightedPositions[k].getFile()).equals(this.highlightedPositions[k])) {
                                this.board[this.highlightedPositions[k].getRank()][this.highlightedPositions[k].getFile()].setHighlight(true);
                            }
                        }
            }
            else{
                return;
            }

        }
        else{
            if(game.getTurn()==this.game.getPieceAt(this.origin).getPieceColor()) {
                Position destination = Position.generateFromRankAndFile(coordinates[0], coordinates[1]);
                if (this.game.performMove(new Move(this.origin, destination))) {
                    updatePieces();
                    removeHighlights();
                    this.highlightedPositions = null;
                    this.origin = null;
                }
                else {
                    removeHighlights();
                    this.highlightedPositions = null;
                    this.origin = null;
                }
            }
        }
    }

    public void removeHighlights(){
        for (int i = 0; i < this.highlightedPositions.length; i++) {
            Position current = this.highlightedPositions[i];
            this.board[current.getRank()][current.getFile()].setHighlight(false);
        }
    }

    public void updatePieces(){

        Piece[][] chessBoard = this.game.getBoard();

        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                if(chessBoard[i][j]!=null) {
                    this.board[i][j].setPiece(chessBoard[i][j].toString());
                }
                else{
                    this.board[i][j].setPiece();
                }
            }
        }

    }
}
