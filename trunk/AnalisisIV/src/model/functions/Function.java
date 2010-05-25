package model.functions;

public interface Function {

    double resolve(double x);

    Function derive();

    String toString();

    String getType();

    boolean isNegative();

    boolean isPositive();

    boolean isConstant();

    double getCoefficient();

    Function getFunctionWithoutCoefficient();
}
