package model.methods;

/**
 * Created by IntelliJ IDEA.
 * User: jivimberg
 * Date: Jul 21, 2010
 * Time: 9:44:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class Function {

    public static double resolve(double x) {
        return x - Math.pow(2, x);
    }

    public static void main(String[] args) {
        Function f = new Function();
    }

}
