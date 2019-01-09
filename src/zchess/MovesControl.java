/*
 * This is an open source project, feel free to use it by any mean
 *  for inquiries, please email me at s-mmegahed@zewailcity.edu.eg

 */
package zchess;

import java.util.Arrays;

/**
 *
 * @author Mohamed Megahed <s-mmegahed@zewailcity.edu.eg>
 */



public class MovesControl {
    // bitboards to use in generating moves
    public static long 
            FILE_A=72340172838076673L,
            FILE_H=-9187201950435737472L,
            FILE_AB=217020518514230019L,
            FILE_GH=-4557430888798830400L,
            RANK_1=-72057594037927936L,
            RANK_4=1095216660480L,
            RANK_5=4278190080L,
            RANK_8=255L,
            CENTRE=103481868288L,
            EXTENDED_CENTRE=66229406269440L,
            KING_SIDE=-1085102592571150096L,
            QUEEN_SIDE=1085102592571150095L,
            KING_B7=460039L,
            KNIGHT_C6=43234889994L,
            Can_Capture,
            MY_PIECES,
            OTHER_PIECES,
            EMPTY;
        static long KING_SPAN=460039L;
    static long KNIGHT_SPAN=43234889994L;
   public static long RankMasks[] = {
        255L, 65280L, 16711680L, 4278190080L, 1095216660480L, 280375465082880L, 71776119061217280L, 0xFF00000000000000L
    };
   public static long FileMasks[] = {  72340172838076673L, 144680345676153346L, 289360691352306692L, 578721382704613384L,
        1157442765409226768L, 2314885530818453536L, 4629771061636907072L, 0x8080808080808080L
   };
   public static long DiagonalMasks[] = {
	0x1L, 0x102L, 0x10204L, 0x1020408L, 0x102040810L, 0x10204081020L, 0x1020408102040L,
	0x102040810204080L, 0x204081020408000L, 0x408102040800000L, 0x810204080000000L,
	0x1020408000000000L, 0x2040800000000000L, 0x4080000000000000L, 0x8000000000000000L
    };
   public static long AntiDiagonalMasks[] = {
	128L, 32832L, 8405024L, 2151686160L, 550831656968L, 141012904183812L, 36099303471055874L,
	0x8040201008040201L, 4620710844295151872L, 2310355422147575808L, 1155177711073755136L,
	577588855528488960L, 288794425616760832L, 144396663052566528L, 72057594037927936L
   };
   static long occupied;
   
  public static String possibleMoves_White(Board b) {
        Can_Capture=~(b.WP|b.WN|b.WB|b.WR|b.WQ|b.WK|b.BK);//added BK to avoid illegal capture
        MY_PIECES=b.WP|b.WN|b.WB|b.WR|b.WQ;//omitted WK to avoid illegal capture
        occupied=b.WP|b.WN|b.WB|b.WR|b.WQ|b.WK|b.BP|b.BN|b.BB|b.BR|b.BQ|b.BK;
        EMPTY=~occupied;
        String possiblemoves=pawn.possiblePawn_White(b.WP,b.BP,b.EP)+
                knight.possible_Knight(b.WN)+
                bishop.possibleBishop(b.WB)+
                rook.possibleRook(b.WR)+
                queen.possibleQueen(b.WQ)+
                King.possible_King(occupied,b.WK)+
                King.possibleCastle_White(b.WP,b.WN,b.WB,b.WR,b.WQ,b.WK,b.BP,b.BN,b.BB,b.BR,b.BQ,b.BK,b.CWK,b.CWQ);
        return possiblemoves;
    }
  public static String possibleMoves_Black(Board b) {
        Can_Capture=~(b.BP|b.BN|b.BB|b.BR|b.BQ|b.BK|b.WK);//added WK to avoid illegal capture
        MY_PIECES=b.BP|b.BN|b.BB|b.BR|b.BQ;//omitted BK to avoid illegal capture
        occupied=b.WP|b.WN|b.WB|b.WR|b.WQ|b.WK|b.BP|b.BN|b.BB|b.BR|b.BQ|b.BK;
        EMPTY=~occupied;
        String possiblemoves=pawn.possiblePawn_Black(b.BP,b.WP,b.EP)+
                knight.possible_Knight(b.BN)+
                bishop.possibleBishop(b.BB)+
                rook.possibleRook(b.BR)+
                queen.possibleQueen(b.BQ)+
                King.possible_King(occupied,b.BK)+
                King.possibleCastle_Black(b.WP,b.WN,b.WB,b.WR,b.WQ,b.WK,b.BP,b.BN,b.BB,b.BR,b.BQ,b.BK,b.CBK,b.CBQ);
        return possiblemoves;
    }
   
   
   
   
   static long Horizontal_Vertical_Moves(int x){ // generating all horizontal and vertical moves for a given chess piece
        long Location=1L<<x;
        long horizontalmoves = (occupied - 2 * Location) ^ Long.reverse(Long.reverse(occupied) - 2 * Long.reverse(Location));
        
        long verticalmoves = ((occupied & FileMasks[x % 8]) - (2 * Location)) ^ Long.reverse(Long.reverse(occupied&FileMasks[x % 8]) - (2 * Long.reverse(Location)));
        
        long hvmoves = (horizontalmoves&RankMasks[x / 8]) | (verticalmoves&FileMasks[x % 8]);
        return hvmoves;
    }
    static long Diagonal_Moves(int x){ // generating all diagonal moves for a given chess piece
        long Location=1L<<x;
        long diagonalmoves = ((occupied & DiagonalMasks[(x / 8) + (x % 8)]) - (2 * Location)) ^ Long.reverse(Long.reverse(occupied & DiagonalMasks[(x / 8) + (x % 8)]) - (2 * Long.reverse(Location)));
        
        long antimoves = ((occupied & AntiDiagonalMasks[(x / 8) + 7 - (x % 8)]) - (2 * Location)) ^ Long.reverse(Long.reverse(occupied & AntiDiagonalMasks[(x / 8) + 7 - (x % 8)]) - (2 * Long.reverse(Location)));
        long damoves = (diagonalmoves&DiagonalMasks[(x / 8) + (x % 8)]) | (antimoves & AntiDiagonalMasks[(x / 8) + 7 - (x % 8)]);
       // drawBitboard(damoves);
        return damoves;
    }
   
    public static void drawBitboard(long bitBoard) { // for testing purposes
        String chessBoard[][]=new String[8][8];
        for (int i=0;i<64;i++) {
            chessBoard[i/8][i%8]="";
        }
        for (int i=0;i<64;i++) {
            if (((bitBoard>>>i)&1)==1) {chessBoard[i/8][i%8]="T";}
            if ("".equals(chessBoard[i/8][i%8])) {chessBoard[i/8][i%8]=" ";}
        }
        for (int i=0;i<8;i++) {
            System.out.println(Arrays.toString(chessBoard[i]));
        }
    }
    
    // generating squares attacked by white
    public static long unsafeForBlack(long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK) {
        long unsafe;
        occupied=WP|WN|WB|WR|WQ|WK|BP|BN|BB|BR|BQ|BK;
        //pawn
        unsafe=((WP>>>7)&~FILE_A);//pawn capture right
        unsafe|=((WP>>>9)&~FILE_H);//pawn capture left
        long possibility;
        //knight
        long i=WN&~(WN-1);
        while(i != 0)
        {
            int iLocation=Long.numberOfTrailingZeros(i);
            if (iLocation>18)
            {
                possibility=KNIGHT_SPAN<<(iLocation-18);
            }
            else {
                possibility=KNIGHT_SPAN>>(18-iLocation);
            }
            if (iLocation%8<4)
            {
                possibility &=~FILE_GH;
            }
            else {
                possibility &=~FILE_AB;
            }
            unsafe |= possibility;
            WN&=~i;
            i=WN&~(WN-1);
        }
        //bishop/queen
        long QB=WQ|WB;
        i=QB&~(QB-1);
        while(i != 0)
        {
            int iLocation=Long.numberOfTrailingZeros(i);
            possibility=Diagonal_Moves(iLocation);
            unsafe |= possibility;
            QB&=~i;
            i=QB&~(QB-1);
        }
        //rook/queen
        long QR=WQ|WR;
        i=QR&~(QR-1);
        while(i != 0)
        {
            int iLocation=Long.numberOfTrailingZeros(i);
            possibility=Horizontal_Vertical_Moves(iLocation);
            unsafe |= possibility;
            QR&=~i;
            i=QR&~(QR-1);
        }
        //king
        int iLocation=Long.numberOfTrailingZeros(WK);
        if (iLocation>9)
        {
            possibility=KING_SPAN<<(iLocation-9);
        }
        else {
            possibility=KING_SPAN>>(9-iLocation);
        }
        if (iLocation%8<4)
        {
            possibility &=~FILE_GH;
        }
        else {
            possibility &=~FILE_AB;
        }
        unsafe |= possibility;
        return unsafe;
    }
   // generating squares attacked by black
    public static long unsafeForWhite(long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK) {
        long unsafe;
        occupied=WP|WN|WB|WR|WQ|WK|BP|BN|BB|BR|BQ|BK;
        //pawn
        unsafe=((BP<<7)&~FILE_H);//pawn capture right
        unsafe|=((BP<<9)&~FILE_A);//pawn capture left
        long possibility;
        //knight
        long i=BN&~(BN-1);
        while(i != 0)
        {
            int iLocation=Long.numberOfTrailingZeros(i);
            if (iLocation>18)
            {
                possibility=KNIGHT_SPAN<<(iLocation-18);
            }
            else {
                possibility=KNIGHT_SPAN>>(18-iLocation);
            }
            if (iLocation%8<4)
            {
                possibility &=~FILE_GH;
            }
            else {
                possibility &=~FILE_AB;
            }
            unsafe |= possibility;
            BN&=~i;
            i=BN&~(BN-1);
        }
        //bishop/queen
        long QB=BQ|BB;
        i=QB&~(QB-1);
        while(i != 0)
        {
            int iLocation=Long.numberOfTrailingZeros(i);
            possibility=Diagonal_Moves(iLocation);
            unsafe |= possibility;
            QB&=~i;
            i=QB&~(QB-1);
        }
        //rook/queen
        long QR=BQ|BR;
        i=QR&~(QR-1);
        while(i != 0)
        {
            int iLocation=Long.numberOfTrailingZeros(i);
            possibility=Horizontal_Vertical_Moves(iLocation);
            unsafe |= possibility;
            QR&=~i;
            i=QR&~(QR-1);
        }
        //king
        int iLocation=Long.numberOfTrailingZeros(BK);
        if (iLocation>9)
        {
            possibility=KING_SPAN<<(iLocation-9);
        }
        else {
            possibility=KING_SPAN>>(9-iLocation);
        }
        if (iLocation%8<4)
        {
            possibility &=~FILE_GH;
        }
        else {
            possibility &=~FILE_AB;
        }
        unsafe |= possibility;
        return unsafe;
    }
    
}
