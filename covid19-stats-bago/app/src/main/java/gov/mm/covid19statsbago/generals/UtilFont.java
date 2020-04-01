package gov.mm.covid19statsbago.generals;

public class UtilFont {

    public static String convertUniNumber(String word) {
        return word.toLowerCase()
                .replace('1', '၁')
                .replace('2', '၂')
                .replace('3', '၃')
                .replace('4', '၄')
                .replace('5', '၅')
                .replace('6', '၆')
                .replace('7', '၇')
                .replace('8', '၈')
                .replace('9', '၉')
                .replace('0', 'ဝ');
    }

    public static String convertEngNumber(String word) {
        return word.toLowerCase()
                .replace( '၁','1')
                .replace('၂','2')
                .replace('၃','3')
                .replace('၄','4')
                .replace( '၅','5')
                .replace( '၆','6')
                .replace('၇','7')
                .replace('၈','8')
                .replace( '၉','9')
                .replace( 'ဝ','0');
    }

}
