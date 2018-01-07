package be.qbmt.dv.devstore;

/**
 * Created by dv on 29/12/2017.
 */

public class Util {

    public static String priceConvert(int priceInt) {
        double i = (double)priceInt/100.0;
        return String.format("%.2f", i) + " €";
    }
}
