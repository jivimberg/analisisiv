package model.functions;

import model.FunctionType;

public class Constant implements MyFunction {

    private Double x;
    private final FunctionType functionType = FunctionType.CONSTANT;

    public Constant(double x) {
        this.x = x;
    }

    @Override
    public MyFunction derive() {
        return new Constant(0);
    }

    @Override
    public double resolve(double x) {
        return this.x;
    }

    @Override
    public String toString() {
        if (x - x.intValue() == 0) {
            return "" + x.intValue();
        } else {
            return "" + x;
        }
    }

    @Override
    public FunctionType getType() {
        return functionType;
    }

    @Override
    public boolean isConstant() {
        return true;
    }

    @Override
    public double getCoefficient() {
        return 1;
    }

    @Override
    public MyFunction getFunctionWithoutCoefficient() {
        return new Constant(x);
    }

    public double getNumber() {
        return x;
    }
}
