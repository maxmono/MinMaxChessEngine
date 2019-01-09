/*
 * This is an open source project, feel free to use it by any mean
 *  for inquiries, please email me at s-mmegahed@zewailcity.edu.eg
 */
package zchess;

/**
 *
 * @author Mohamed Megahed
 * @version 1
 * @email s-mmegahed@zewailcity.edu.eg
 */
public class ZChess {


    public static void main(String[] args) {
    Board B1 = new Board(); // creating new board with start position
  //  MovesControl.drawBitboard(B1.WP);
B1.FEN_Notation("rnbqkb1r/ppp2ppp/4pn2/8/2PPpP2/8/PP4PP/RNBQKBNR w KQkq - 0 5 "); // test board
String x = MovesControl.possibleMoves_White(B1); // all possible white moves
System.out.println(x); 
System.out.println(x.length()/4);  // number of possible white moves
String y = MovesControl.possibleMoves_Black(B1); // all possible black moves
System.out.println(y);
System.out.println(y.length()/4);

 Board.drawBoard(B1); // drawing the test board for testing purposes
    }
    
}

/**
 * Sources:
 * https://chessprogramming.wikispaces.com/Hyperbola+Quintessence
 * http://www.top-5000.nl/authors/rebel/chess840.htm
 *jonathan from Logic Crazy Chess channel : https://www.youtube.com/channel/UCmMjMHTeUEBJJZhxix-N-yg
 * http://www.frayn.net/beowulf/theory.html
 * http://tckerrigan.com/Chess/TSCP/
 * 
 */