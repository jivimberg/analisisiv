package model.functions;

public class Variable implements Function {

    private double coefficient;

    public Variable() {
        this.coefficient = 1;
    }

    public Variable(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public Function derive() {
        return new Constant(coefficient);
    }

    @Override
    public double resolve(double x) {
        return coefficient * x;
    }

    @Override
    public String toString() {
        if (coefficient == 1) {
            return "x";
        } else if (coefficient == -1) {
            return "- x";
        } else if (coefficient == 0) {
            return "0";
        } else {
            return coefficient + " x";
        }
    }

    @Override
    public String getType() {
        return "Variable";
    }

    @Override
    public boolean isNegative() {
        return coefficient < 0;
    }

    @Override
    public boolean isPositive() {
        return coefficient > 0;
    }

    @Override
    public boolean isConstant() {
        return false;
    }

    @Override
    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public Function getFunctionWithoutCoefficient() {
        return new Variable();
    }
}
