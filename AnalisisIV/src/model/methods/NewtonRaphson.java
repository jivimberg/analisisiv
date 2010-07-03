package model.methods;

import model.exceptions.RootNotFoundException;
import model.functions.Function;

/**
 * Created by IntelliJ IDEA.
 * User: jivimberg
 * Date: May 25, 2010
 * Time: 4:03:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewtonRaphson {
    /**
     * Newton-Raphson method finds a root by sucesive derivations.
     * @author Juan Ignacio Vimberg
     */

    /**
     * This method find the root of the function by Newton-Raphson
     *
     * @param p0            starting point. This point should be as close as posible to the root.
     * @param error         expected error
     * @param maxIterations max itereations allowed
     */
    public double findRoot(Function function, double p0, double error, int maxIterations)
            throws RootNotFoundException {
        int iterations = 0;
        double p = p0;
        while (iterations < maxIterations) {
            //Xn+1 = Xn - f(x) / f'(x)
            double root = p - function.resolve(p) / function.derive().resolve(p);
            if (Math.abs(root - p) < error) {
                return root;
            }
            p = root;
            iterations++;
        }
        throw new RootNotFoundException();
    }

    public static void main(String[] args) {
//		try {
//			System.out.println(new NewtonRaphson().findRoot(new Polynomial(2, new double[] { -4, 0, 1 }), 3, 0.25, 100));
//		} catch (RootNotFoundException e) {
//			e.printStackTrace();
//		}
	}    
}
