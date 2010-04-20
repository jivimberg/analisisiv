package functions;

import functions.basics.Product;

public class Cos implements Function {

	private Function internal;
	private Constant coefficient;

	public Cos(Function internal, double coefficient) {
		this.internal = internal;
		this.coefficient = new Constant(coefficient);
	}

	public Cos(Function internal) {
		this.internal = internal;
		this.coefficient = new Constant(1);
	}
	
	@Override
	public Function derive() {
		if (isConstant()) {
			return new Constant(0);
		} else if (internal.isConstant()) {
			return new Sin(internal, -coefficient.getNumber() * internal.getCoefficient());
		} else {
			Product p = new Product();
			p.addFunction(new Sin(internal, -1));
			p.addFunction(internal.derive());
			return p;
		}
	}

	@Override
	public double resolve(double x) {
		return coefficient.getNumber() * Math.cos(internal.resolve(x));
	}

	@Override
	public String toString() {
		if (coefficient.getNumber() == 1) {
			return "Cos(" + internal.toString() + ")";
		} else if (coefficient.getNumber() == -1) {
			return "- Cos(" + internal.toString() + ")";
		} else if (coefficient.getNumber() == 0) {
			return "0";
		} else {
			return coefficient + " Cos(" + internal.toString() + ")";
		}
	}

	@Override
	public String getType() {
		return "Cos";
	}

	@Override
	public boolean isNegative() {
		return coefficient.getNumber() < 0;
	}

	@Override
	public boolean isPositive() {
		return coefficient.getNumber() > 0;
	}

	@Override
	public boolean isConstant() {
		return internal.isConstant();
	}

	@Override
	public double getCoefficient() {
		return coefficient.getNumber();
	}
	
	@Override
	public Function getFunctionWithoutCoefficient() {
		return new Cos(internal);
	}	
}
