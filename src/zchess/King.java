/*
 * This is an open source project, feel free to use it by any mean
 *  for inquiries, please email me at s-mmegahed@zewailcity.edu.eg

 */
package zchess;

/**
 *
 * @author Mohamed Megahed <s-mmegahed@zewailcity.edu.eg>
 */
public class King {
    
        public static String possible_King(long OCCUPIED,long K) {
        String list="";
        long possibility;
        int iLocation=Long.numberOfTrailingZeros(K);
        if (iLocation>9)
        {
            possibility=MovesControl.KING_SPAN<<(iLocation-9);
        }
        else {
            possibility=MovesControl.KING_SPAN>>(9-iLocation);
        }
        if (iLocation%8<4)
        {
            possibility &=~MovesControl.FILE_GH&MovesControl.Can_Capture;
        }
        else {
            possibility &=~MovesControl.FILE_AB&MovesControl.Can_Capture;
        }
        long j=possibility&~(possibility-1);
        while (j != 0)
        {
            int index=Long.numberOfTrailingZeros(j);
            list+=""+(iLocation/8)+(iLocation%8)+(index/8)+(index%8);
            possibility&=~j;
            j=possibility&~(possibility-1);
        }
        return list;
    }
    public static String possibleCastle_White(long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK,boolean CWK,boolean CWQ) {
        String list="";
        long UNSAFE=MovesControl.unsafeForWhite(WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK);
        if ((UNSAFE&WK)==0) {
            if (CWK&&(((1L<<63)&WR)!=0))
            {
                if (((MovesControl.occupied|UNSAFE)&((1L<<61)|(1L<<62)))==0) {
                    list+="7476";
                }
            }
            if (CWQ&&(((1L<<56)&WR)!=0))
            {
                if (((MovesControl.occupied|(UNSAFE&~(1L<<57)))&((1L<<57)|(1L<<58)|(1L<<59)))==0) {
                    list+="7472";
                }
            }
        }
        return list;
    }
    public static String possibleCastle_Black(long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK,boolean CBK,boolean CBQ) {
        String list="";
        long UNSAFE=MovesControl.unsafeForBlack(WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK);
        if ((UNSAFE&BK)==0) {
            if (CBK&&(((1L<<7)&BR)!=0))
            {
                if (((MovesControl.occupied|UNSAFE)&((1L<<5)|(1L<<6)))==0) {
                    list+="0406";
                }
            }
            if (CBQ&&(((1L<<0)&BR)!=0))
            {
                if (((MovesControl.occupied|(UNSAFE&~(1L<<1)))&((1L<<1)|(1L<<2)|(1L<<3)))==0) {
                    list+="0402";
                }
            }
        }
        return list;
    }
    
}
