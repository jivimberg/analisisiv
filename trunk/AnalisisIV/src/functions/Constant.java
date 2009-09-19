package functions;

public class Constant implements Function {

	private double x;

	public Constant(double x) {
		this.x = x;
	}

	@Override
	public Function derive() {
		return new Constant(0);
	}

	@Override
	public double resolve(double x) {
		return this.x;
	}

	public String toString() {
		return "" + x;
	}

}
