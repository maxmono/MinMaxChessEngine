/*
 * This is an open source project, feel free to use it by any mean
 *  for inquiries, please email me at s-mmegahed@zewailcity.edu.eg

 */
package zchess;

/**
 *
 * @author Mohamed Megahed <s-mmegahed@zewailcity.edu.eg>
 */
public class knight {
    
    public static String possible_Knight(long N) {
        String moves="";
        long i=N&~(N-1);
        long temp;
        while(i != 0)
        {
            int iLocation=Long.numberOfTrailingZeros(i);
            if (iLocation>18)
            {
                temp=MovesControl.KNIGHT_SPAN<<(iLocation-18);
            }
            else {
                temp=MovesControl.KNIGHT_SPAN>>(18-iLocation);
            }
            if (iLocation%8<4)
            {
                temp &=~MovesControl.FILE_GH&MovesControl.Can_Capture;
            }
            else {
                temp &=~MovesControl.FILE_AB&MovesControl.Can_Capture;
            }
            long j=temp&~(temp-1);
            while (j != 0)
            {
                int index=Long.numberOfTrailingZeros(j);
                moves+=""+(iLocation/8)+(iLocation%8)+(index/8)+(index%8);
                temp&=~j;
                j=temp&~(temp-1);
            }
            N&=~i;
            i=N&~(N-1);
        }
        return moves;
    }
    
}
