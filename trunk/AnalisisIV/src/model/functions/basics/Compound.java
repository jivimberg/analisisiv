package model.functions.basics;

import model.FunctionType;
import model.functions.MyFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jivimberg
 * Date: Jul 7, 2010
 * Time: 4:57:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Compound implements MyFunction {
    MyFunction f;
    MyFunction g;

    private final FunctionType functionType = FunctionType.SUM;

    public Compound(MyFunction f, MyFunction g) {
        this.f = f;
        this.g = g;
    }

    public MyFunction derivative() {
        List<MyFunction> functionList = new ArrayList<MyFunction>();
        functionList.add(new Compound(f.derive(), g));
        functionList.add(g.derive());
        return new Product(functionList);
    }

    public double resolve(double d) {
        return f.resolve(g.resolve(d));
    }

    public MyFunction derive() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public FunctionType getType() {
        return functionType;
    }

    public boolean isConstant() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public double getCoefficient() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public MyFunction getFunctionWithoutCoefficient() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
