package model.functions;

import model.FunctionType;

public interface MyFunction {

    double resolve(double x);

    MyFunction derive();

    FunctionType getType();

    boolean isConstant();

    double getCoefficient();

    MyFunction getFunctionWithoutCoefficient();
}
