package model.functions;

import model.FunctionType;

public interface Function {

    double resolve(double x);

    Function derive();

    String toString();

    FunctionType getType();

    boolean isNegative();

    boolean isPositive();

    boolean isConstant();

    double getCoefficient();

    Function getFunctionWithoutCoefficient();
}
