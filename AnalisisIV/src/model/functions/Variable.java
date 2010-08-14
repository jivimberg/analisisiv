package model.functions;

import model.FunctionType;

public class Variable implements MyFunction {

    private double coefficient;
    private final FunctionType functionType = FunctionType.VARIABLE;

    public Variable() {
        this.coefficient = 1;
    }

    public Variable(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public MyFunction derive() {
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
    public FunctionType getType() {
        return functionType;
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
    public MyFunction getFunctionWithoutCoefficient() {
        return new Variable();
    }
}
