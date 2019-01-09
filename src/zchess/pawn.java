/*
 * This is an open source project, feel free to use it by any mean
 *  for inquiries, please email me at s-mmegahed@zewailcity.edu.eg

 */
package zchess;

/**
 *
 * @author Mohamed Megahed <s-mmegahed@zewailcity.edu.eg>
 */
public class pawn {
 public static String possiblePawn_White(long WP,long BP,long EP) {
        String possiblemoves="";
        //x1,y1,x2,y2
        long PAWN_MOVES=(WP>>7)&MovesControl.Can_Capture&MovesControl.occupied&~MovesControl.RANK_8&~MovesControl.FILE_A;//capture right
        long temp=PAWN_MOVES&~(PAWN_MOVES-1);
        while (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index/8+1)+(index%8-1)+(index/8)+(index%8);
            PAWN_MOVES&=~temp;
            temp=PAWN_MOVES&~(PAWN_MOVES-1);
        }
        PAWN_MOVES=(WP>>9)&MovesControl.Can_Capture&MovesControl.occupied&~MovesControl.RANK_8&~MovesControl.FILE_H;//capture left
        temp=PAWN_MOVES&~(PAWN_MOVES-1);
        while (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index/8+1)+(index%8+1)+(index/8)+(index%8);
            PAWN_MOVES&=~temp;
            temp=PAWN_MOVES&~(PAWN_MOVES-1);
        }
        PAWN_MOVES=(WP>>8)&MovesControl.EMPTY&~MovesControl.RANK_8;//move 1 forward
        temp=PAWN_MOVES&~(PAWN_MOVES-1);
        while (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index/8+1)+(index%8)+(index/8)+(index%8);
            PAWN_MOVES&=~temp;
            temp=PAWN_MOVES&~(PAWN_MOVES-1);
        }
        PAWN_MOVES=(WP>>16)&MovesControl.EMPTY&(MovesControl.EMPTY>>8)&MovesControl.RANK_4;//move 2 forward
        temp=PAWN_MOVES&~(PAWN_MOVES-1);
        while (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index/8+2)+(index%8)+(index/8)+(index%8);
            PAWN_MOVES&=~temp;
            temp=PAWN_MOVES&~(PAWN_MOVES-1);
        }
        //y1,y2,Promotion Type,"P"
        PAWN_MOVES=(WP>>7)&MovesControl.Can_Capture&MovesControl.occupied&MovesControl.RANK_8&~MovesControl.FILE_A;//pawn promotion by capture right
        temp=PAWN_MOVES&~(PAWN_MOVES-1);
        while (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index%8-1)+(index%8)+"QP"+(index%8-1)+(index%8)+"RP"+(index%8-1)+(index%8)+"BP"+(index%8-1)+(index%8)+"NP";
            PAWN_MOVES&=~temp;
            temp=PAWN_MOVES&~(PAWN_MOVES-1);
        }
        PAWN_MOVES=(WP>>9)&MovesControl.Can_Capture&MovesControl.occupied&MovesControl.RANK_8&~MovesControl.FILE_H;//pawn promotion by capture left
        temp=PAWN_MOVES&~(PAWN_MOVES-1);
        while (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index%8+1)+(index%8)+"QP"+(index%8+1)+(index%8)+"RP"+(index%8+1)+(index%8)+"BP"+(index%8+1)+(index%8)+"NP";
            PAWN_MOVES&=~temp;
            temp=PAWN_MOVES&~(PAWN_MOVES-1);
        }
        PAWN_MOVES=(WP>>8)&MovesControl.EMPTY&MovesControl.RANK_8;//pawn promotion by move 1 forward
        temp=PAWN_MOVES&~(PAWN_MOVES-1);
        while (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index%8)+(index%8)+"QP"+(index%8)+(index%8)+"RP"+(index%8)+(index%8)+"BP"+(index%8)+(index%8)+"NP";
            PAWN_MOVES&=~temp;
            temp=PAWN_MOVES&~(PAWN_MOVES-1);
        }
        //y1,y2,"WE"
        //en passant right
        temp = (WP << 1)&BP&MovesControl.RANK_5&~MovesControl.FILE_A&EP;//shows piece to remove, not the destination
        if (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index%8-1)+(index%8)+"WE";
        }
        //en passant left
        temp = (WP >> 1)&BP&MovesControl.RANK_5&~MovesControl.FILE_H&EP;//shows piece to remove, not the destination
        if (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index%8+1)+(index%8)+"WE";
        }
       return possiblemoves;
    }
 public static String possiblePawn_Black(long BP,long WP,long EP) {
        String possiblemoves="";
        //x1,y1,x2,y2
        long PAWN_MOVES=(BP<<7)&MovesControl.Can_Capture&MovesControl.occupied&~MovesControl.RANK_1&~MovesControl.FILE_H;//capture right
        long temp=PAWN_MOVES&~(PAWN_MOVES-1);
        while (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index/8-1)+(index%8+1)+(index/8)+(index%8);
            PAWN_MOVES&=~temp;
            temp=PAWN_MOVES&~(PAWN_MOVES-1);
        }
        PAWN_MOVES=(BP<<9)&MovesControl.Can_Capture&MovesControl.occupied&~MovesControl.RANK_1&~MovesControl.FILE_A;//capture left
        temp=PAWN_MOVES&~(PAWN_MOVES-1);
        while (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index/8-1)+(index%8-1)+(index/8)+(index%8);
            PAWN_MOVES&=~temp;
            temp=PAWN_MOVES&~(PAWN_MOVES-1);
        }
        PAWN_MOVES=(BP<<8)&MovesControl.EMPTY&~MovesControl.RANK_1;//move 1 forward
        temp=PAWN_MOVES&~(PAWN_MOVES-1);
        while (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index/8-1)+(index%8)+(index/8)+(index%8);
            PAWN_MOVES&=~temp;
            temp=PAWN_MOVES&~(PAWN_MOVES-1);
        }
        PAWN_MOVES=(BP<<16)&MovesControl.EMPTY&(MovesControl.EMPTY<<8)&MovesControl.RANK_5;//move 2 forward
        temp=PAWN_MOVES&~(PAWN_MOVES-1);
        while (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index/8-2)+(index%8)+(index/8)+(index%8);
            PAWN_MOVES&=~temp;
            temp=PAWN_MOVES&~(PAWN_MOVES-1);
        }
        //y1,y2,Promotion Type,"P"
        PAWN_MOVES=(BP<<7)&MovesControl.Can_Capture&MovesControl.occupied&MovesControl.RANK_1&~MovesControl.FILE_H;//pawn promotion by capture right
        temp=PAWN_MOVES&~(PAWN_MOVES-1);
        while (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index%8+1)+(index%8)+"qP"+(index%8+1)+(index%8)+"rP"+(index%8+1)+(index%8)+"bP"+(index%8+1)+(index%8)+"nP";
            PAWN_MOVES&=~temp;
            temp=PAWN_MOVES&~(PAWN_MOVES-1);
        }
        PAWN_MOVES=(BP<<9)&MovesControl.Can_Capture&MovesControl.occupied&MovesControl.RANK_1&~MovesControl.FILE_A;//pawn promotion by capture left
        temp=PAWN_MOVES&~(PAWN_MOVES-1);
        while (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index%8-1)+(index%8)+"qP"+(index%8-1)+(index%8)+"rP"+(index%8-1)+(index%8)+"bP"+(index%8-1)+(index%8)+"nP";
            PAWN_MOVES&=~temp;
            temp=PAWN_MOVES&~(PAWN_MOVES-1);
        }
        PAWN_MOVES=(BP<<8)&MovesControl.EMPTY&MovesControl.RANK_1;//pawn promotion by move 1 forward
        temp=PAWN_MOVES&~(PAWN_MOVES-1);
        while (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index%8)+(index%8)+"qP"+(index%8)+(index%8)+"rP"+(index%8)+(index%8)+"bP"+(index%8)+(index%8)+"nP";
            PAWN_MOVES&=~temp;
            temp=PAWN_MOVES&~(PAWN_MOVES-1);
        }
        //y1,y2,"BE"
        //en passant right
        temp = (BP >> 1)&WP&MovesControl.RANK_4&~MovesControl.FILE_H&EP;//shows piece to remove, not the destination
        if (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index%8+1)+(index%8)+"BE";
        }
        //en passant left
        temp = (BP << 1)&WP&MovesControl.RANK_4&~MovesControl.FILE_A&EP;//shows piece to remove, not the destination
        if (temp != 0)
        {
            int index=Long.numberOfTrailingZeros(temp);
            possiblemoves+=""+(index%8-1)+(index%8)+"BE";
        }
       return possiblemoves;
    }
    
    
}
