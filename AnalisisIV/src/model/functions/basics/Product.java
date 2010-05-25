package model.functions.basics;

import model.functions.Function;

import java.util.ArrayList;
import java.util.List;

public class Product implements Function {

    private List<Function> functions;
    private double coefficient;

    public Product(List<Function> functions, double coefficient) {
        this.coefficient = coefficient;
        functions = new ArrayList<Function>();
        for (Function f : functions) {
            addFunction(f);
        }
    }

    public Product(List<Function> functions) {
        coefficient = 1;
        functions = new ArrayList<Function>();
        for (Function f : functions) {
            addFunction(f);
        }
    }

    public Product() {
        coefficient = 1;
        functions = new ArrayList<Function>();
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public void addFunction(Function f) {
        if (f.isConstant()) {
            setCoefficient(coefficient * f.resolve(1));
        } else {
            setCoefficient(coefficient * f.getCoefficient());
            functions.add(f.getFunctionWithoutCoefficient());
        }
    }

    @Override
    public Function derive() {
        return null;
    }

    @Override
    public double resolve(double x) {
        double result = 1;
        for (Function f : functions) {
            result *= f.resolve(x);
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "(c)" + coefficient;
        for (int i = 0; i < functions.size(); i++) {
            Function f = functions.get(i);
            result += " * " + f.toString();
        }
        return result;
    }

    @Override
    public String getType() {
        return "Product";
    }

    @Override
    public boolean isNegative() {
        return false;
    }

    @Override
    public boolean isPositive() {
        return false;
    }

    @Override
    public boolean isConstant() {
        for (Function f : functions) {
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
    public Function getFunctionWithoutCoefficient() {
        return new Product(functions);
    }
}
