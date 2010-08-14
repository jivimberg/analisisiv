package model.functions.basics;

import model.FunctionType;
import model.functions.MyFunction;

import java.util.ArrayList;
import java.util.List;

public class Product implements MyFunction {

    private List<MyFunction> functions;
    private double coefficient;
    private final FunctionType functionType = FunctionType.PRODUCT;

    public Product(List<MyFunction> functions, double coefficient) {
        this.coefficient = coefficient;
        functions = new ArrayList<MyFunction>();
        for (MyFunction f : functions) {
            addFunction(f);
        }
    }

    public Product(List<MyFunction> functions) {
        coefficient = 1;
        functions = new ArrayList<MyFunction>();
        for (MyFunction f : functions) {
            addFunction(f);
        }
    }

    public Product() {
        coefficient = 1;
        functions = new ArrayList<MyFunction>();
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public void addFunction(MyFunction f) {
        if (f.isConstant()) {
            setCoefficient(coefficient * f.resolve(1));
        } else {
            setCoefficient(coefficient * f.getCoefficient());
            functions.add(f.getFunctionWithoutCoefficient());
        }
    }

    @Override
    public MyFunction derive() {
        return null;
    }

    @Override
    public double resolve(double x) {
        double result = 1;
        for (MyFunction f : functions) {
            result *= f.resolve(x);
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "(c)" + coefficient;
        for (int i = 0; i < functions.size(); i++) {
            MyFunction f = functions.get(i);
            result += " * " + f.toString();
        }
        return result;
    }

    @Override
    public FunctionType getType() {
        return functionType;
    }

    @Override
    public boolean isConstant() {
        for (MyFunction f : functions) {
            if (!f.isConstant()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public double getCoefficient() {
        return 1;
    }

    @Override
    public MyFunction getFunctionWithoutCoefficient() {
        return new Product(functions);
    }
}
