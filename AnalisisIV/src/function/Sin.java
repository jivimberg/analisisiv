package function;

public class Sin implements Function {

	private Function internal;

	public Sin(Function internal) {
		this.internal = internal;
	}

	public Function derive() {
		return new Cos(internal.derive());
	}

	public double resolve(double x) {
		return Math.sin(internal.resolve(x));
	}

	public String toString() {
		return "Sin(" + internal.toString() + ")";
	}

}
