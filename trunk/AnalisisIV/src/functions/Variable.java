package functions;

public class Variable implements Function {

	@Override
	public Function derive() {
		return new Constant(1);
	}

	@Override
	public double resolve(double x) {
		return x;
	}
	
	public String toString() {
		return "x";
	}

}
