package function;

public class Cos implements Function {

	private Function internal;
	
	public Cos(Function internal) {
		this.internal = internal;
	}
	
	public Function derive() {
		return new Sin(internal.derive());
	}

	public double resolve(double x) {
		return Math.cos(internal.resolve(x));
	}
	
	public String toString() {
		return "Cos(" + internal.toString() + ")";
	}
}
