package model.functions.basics;

import model.FunctionType;
import model.functions.Function;

/**
 * Created by IntelliJ IDEA.
 * User: jivimberg
 * Date: May 26, 2010
 * Time: 5:11:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Polynomial implements Function {

    private int degree;
    private double[] coefficients;
    private final FunctionType functionType = FunctionType.POLYNOMIAL;

    public Polynomial(int degree, double[] coeff) {
        this.coefficients = coeff;
        this.degree = degree;
    }

    public Function derive() {
        double[] newCoeff = new double[coefficients.length - 1];
        int exponent = degree;
        for (int i = 0; i < newCoeff.length; i++) {
            newCoeff[i] = coefficients[i] * exponent;
            exponent--;
        }
        return new Polynomial(degree - 1, newCoeff);
    }

    public double resolve(double x) {
        int exponent = 0;
        double result = 0;
        for (double coeff : coefficients) {
            result += coeff * Math.pow(x, exponent);
            exponent++;
            if (exponent - 1 == degree) {
                break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        int exponent = 0;
        for (double coeff : coefficients) {
            if (exponent == 0) {
                if (coeff < 0) {
                    result.concat("-" + coeff);
                } else {
                    result.concat("" + coeff);
                }
            }
            if (exponent == 1) {
                if (coeff < 0) {
                    result.concat("-" + coeff + "x");
                } else {
                    result.concat(coeff + "x");
                }
            }
            if (coeff < 0) {
                result.concat("-" + coeff + "x^" + exponent);
            } else {
                result.concat(coeff + "x^" + exponent);
            }
            exponent++;
        }
        return result;
    }

    @Override
    public FunctionType getType() {
        return functionType;
    }

    @Override
    public boolean isNegative() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isPositive() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isConstant() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double getCoefficient() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Function getFunctionWithoutCoefficient() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public static void main(String[] args) {
        Polynomial p = new Polynomial(2, new double[]{2, 3, -1});
        System.out.println(p.toString());
    }
}
