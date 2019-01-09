package zchess;

import java.util.*;

public class Board {
    
    long WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK,EP=0L; // AB   A = Color B = piece WP = White pawns
    boolean CWK=true,CWQ=true,CBK=true,CBQ=true,WhiteToMove=true; // castling
public Board(){
    // start position board
       WP=71776119061217280L;
       WN=4755801206503243776L;
       WB=2594073385365405696L;
       WR=-9151314442816847872L;
       WQ=576460752303423488L;
       WK=1152921504606846976L;
       BP=65280L;
       BN=66L;
       BB=36L;
       BR=129L;
       BQ=8L;
       BK=16L; 
                drawBoard(this);

    
}
 
    
     public static void drawBoard(Board board) {
        String chessBoard[][]=new String[8][8];
        for (int i=0;i<64;i++) {
            chessBoard[i/8][i%8]=" ";
        }
        for (int i=0;i<64;i++) {
            if (((board.WP>>i)&1)==1) {chessBoard[i/8][i%8]="P";}
            if (((board.WN>>i)&1)==1) {chessBoard[i/8][i%8]="N";}
            if (((board.WB>>i)&1)==1) {chessBoard[i/8][i%8]="B";}
            if (((board.WR>>i)&1)==1) {chessBoard[i/8][i%8]="R";}
            if (((board.WQ>>i)&1)==1) {chessBoard[i/8][i%8]="Q";}
            if (((board.WK>>i)&1)==1) {chessBoard[i/8][i%8]="K";}
            if (((board.BP>>i)&1)==1) {chessBoard[i/8][i%8]="p";}
            if (((board.BN>>i)&1)==1) {chessBoard[i/8][i%8]="n";}
            if (((board.BB>>i)&1)==1) {chessBoard[i/8][i%8]="b";}
            if (((board.BR>>i)&1)==1) {chessBoard[i/8][i%8]="r";}
            if (((board.BQ>>i)&1)==1) {chessBoard[i/8][i%8]="q";}
            if (((board.BK>>i)&1)==1) {chessBoard[i/8][i%8]="k";}
        }
        for (int i=0;i<8;i++) {
            System.out.println(Arrays.toString(chessBoard[i]));
        }
        
  
     
    
}

     public void FEN_Notation(String FEN){  // FEN Notation used in inputting chess settings for testing
         
         this.WP=0; this.WN=0; this.WB=0;
        this.WR=0; this.WQ=0; this.WK=0;
        this.BP=0; this.BN=0; this.BB=0;
        this.BR=0; this.BQ=0; this.BK=0;
        this.CWK=false; this.CWQ=false;
        this.CBK=false; this.CBQ=false;
	int charIndex = 0;
	int boardIndex = 0;
	while (FEN.charAt(charIndex) != ' ')
	{
		switch (FEN.charAt(charIndex++))
		{
		case 'P': this.WP |= (1L << boardIndex++);
			break;
		case 'p': this.BP |= (1L << boardIndex++);
			break;
		case 'N': this.WN |= (1L << boardIndex++);
			break;
		case 'n': this.BN |= (1L << boardIndex++);
			break;
		case 'B': this.WB |= (1L << boardIndex++);
			break;
		case 'b': this.BB |= (1L << boardIndex++);
			break;
		case 'R': this.WR |= (1L << boardIndex++);
			break;
		case 'r': this.BR |= (1L << boardIndex++);
			break;
		case 'Q': this.WQ |= (1L << boardIndex++);
			break;
		case 'q': this.BQ |= (1L << boardIndex++);
			break;
		case 'K': this.WK |= (1L << boardIndex++);
			break;
		case 'k': this.BK |= (1L << boardIndex++);
			break;
		case '/':
			break;
		case '1': boardIndex++;
			break;
		case '2': boardIndex += 2;
			break;
		case '3': boardIndex += 3;
			break;
		case '4': boardIndex += 4;
			break;
		case '5': boardIndex += 5;
			break;
		case '6': boardIndex += 6;
			break;
		case '7': boardIndex += 7;
			break;
		case '8': boardIndex += 8;
			break;
		default:
			break;
		}
	}
	this.WhiteToMove = (FEN.charAt(++charIndex) == 'w');
	charIndex += 2;
	while (FEN.charAt(charIndex) != ' ')
	{
		switch (FEN.charAt(charIndex++))
		{
		case '-':
			break;
		case 'K': this.CWK = true;
			break;
		case 'Q': this.CWQ = true;
			break;
		case 'k': this.CBK = true;
			break;
		case 'q': this.CBQ = true;
			break;
		default:
			break;
		}
	}
	if (FEN.charAt(++charIndex) != '-')
	{
		this.EP = MovesControl.FileMasks[FEN.charAt(charIndex++) - 'a'];
	}
         
         
     }
     

}