/*
 * This is an open source project, feel free to use it by any mean
 *  for inquiries, please email me at s-mmegahed@zewailcity.edu.eg

 */
package zchess;

/**
 *
 * @author Mohamed Megahed <s-mmegahed@zewailcity.edu.eg>
 */
public class bishop {
    
     public static String possibleBishop(long WB){
        String moves="";
        long i = WB & ~(WB-1);
        long temp;
        while(i != 0){
            int iLocation=Long.numberOfTrailingZeros(i);
            temp = MovesControl.Diagonal_Moves(iLocation) & MovesControl.Can_Capture;
                     //  MovesControl.drawBitboard(temp);

            long j=temp & ~(temp-1);
            while (j != 0)
            {
                int index = Long.numberOfTrailingZeros(j);
                moves += "" + (iLocation/8) + (iLocation%8) + (index/8) + (index%8);
                temp &= ~j;
                j = temp & ~(temp-1);
            }
            WB &= ~i;
            i = WB & ~(WB-1);
        }
        
       //int x = moves.length()/4;
        return moves;
    }
    
    
    
    
}
