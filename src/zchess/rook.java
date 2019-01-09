/*
 * This is an open source project, feel free to use it by any mean
 *  for inquiries, please email me at s-mmegahed@zewailcity.edu.eg

 */
package zchess;

/**
 *
 * @author Mohamed Megahed <s-mmegahed@zewailcity.edu.eg>
 */
public class rook {
    
        public static String possibleRook(long WR){
         
           String moves = "";
           long i = WR & ~(WR-1);
           long temp;
           while(i != 0){
            int iLocation=Long.numberOfTrailingZeros(i);
            temp = MovesControl.Horizontal_Vertical_Moves(iLocation) & MovesControl.Can_Capture;
            long j=temp & ~(temp-1);
            while (j != 0)
            {
                int index = Long.numberOfTrailingZeros(j);
                moves += "" + (iLocation/8) + (iLocation%8) + (index/8) + (index%8);
                temp &= ~j;
                j = temp & ~(temp-1);
            }
            WR &= ~i;
            i = WR & ~(WR-1);
        }
        return moves;
    } 
            
            
}
 
    
