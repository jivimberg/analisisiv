package model.functions.basics;

import model.FunctionType;
import model.functions.MyFunction;

import java.util.ArrayList;
import java.util.List;

public class Sum implements MyFunction {

    private List<MyFunction> functions;
    private final FunctionType functionType = FunctionType.SUM;

    public Sum(List<MyFunction> functions) {
        this.functions = functions;
    }

    public Sum(MyFunction[] functions) {
        List<MyFunction> functionList = new ArrayList<MyFunction>();
        for (MyFunction function : functions) {
            functionList.add(function);
        }
        this.functions = functionList;
    }

    public Sum() {
        functions = new ArrayList<MyFunction>();
    }

    public void addFunction(MyFunction f) {
        functions.add(f);
    }

    @Override
    public MyFunction derive() {
        List<MyFunction> derived = new ArrayList<MyFunction>();
        for (MyFunction f : functions) {
            if (!f.isConstant()) {
                derived.add(f.derive());
            }
        }
        return new Sum(derived);
    }

    @Override
    public double resolve(double x) {
        double result = 0;
        for (MyFunction f : functions) {
            result += f.resolve(x);
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
        return new Sum(functions);
    }
}
