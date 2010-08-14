package model.methods;

import model.exceptions.RootNotFoundException;

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
     * Este método encuentra la raíz de la función por medio de Newton Raphson
     *
     * @param function      the  función numérica
     * @param p0            punto de inicio. Debe ser lo más cercano posible a la raíz
     * @param error         margen de error tolerado
     * @param maxIterations número máximo de iteraciones permitidas
     */
    public double findRoot(Function function, Function derivedF, double p0, double error, int maxIterations)
            throws RootNotFoundException {
        int iterations = 0;
        double p = p0;
        while (iterations < maxIterations) {
            //Xn+1 = Xn - f(x) / f'(x)
            double root = p - function.resolve(p) / derivedF.resolve(p);
            if (Math.abs(root - p) < error) {
                return root;
            }
            p = root;
            iterations++;
        }
        throw new RootNotFoundException();
    }

    public static void main(String[] args) {
        try {
            Function f = new Function();
            e

            System.out.println(new NewtonRaphson().findRoot(f, f, 1.5, 0.01, 1000));
        } catch (RootNotFoundException e) {
            e.printStackTrace();
        }
    }
}
