package model.functions;

import model.FunctionType;
import model.functions.basics.Product;

public class Sin implements Function {

    private Function internal;
    private double coefficient;
    private final FunctionType functionType = FunctionType.SIN;

    public Sin(Function internal, double coefficient) {
        this.internal = internal;
        this.coefficient = coefficient;
    }

    public Sin(Function internal) {
        this.internal = internal;
        this.coefficient = 1;
    }

    @Override
    public Function derive() {
        if (isConstant()) {
            return new Constant(0);
        } else if (internal.isConstant()) {
            return new Cos(internal, coefficient * internal.getCoefficient());
        } else {
            Product p = new Product();
            p.addFunction(new Cos(internal));
            p.addFunction(internal.derive());
            return p;
        }
    }

    @Override
    public double resolve(double x) {
        return coefficient * Math.sin(internal.resolve(x));
    }

    @Override
    public String toString() {
        if (coefficient == 1) {
            return "Sin(" + internal.toString() + ")";
        } else if (coefficient == -1) {
            return "- Sin(" + internal.toString() + ")";
        } else if (coefficient == 0) {
            return "0";
        } else {
            return coefficient + " Sin(" + internal.toString() + ")";
        }
    }

    @Override
    public FunctionType getType() {
        return functionType;
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
        return internal.isConstant();
    }

    @Override
    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public Function getFunctionWithoutCoefficient() {
        return new Sin(internal);
    }
}
