package functions;

import functions.basics.Product;

public class Sin implements Function {

	private Function internal;

	public Sin(Function internal) {
		this.internal = internal;
	}

	//ESTA MAL!!!!!!!!!!! NO USAR INSTANCEOF!!!!
	public Function derive() {
		if(internal instanceof Variable || internal instanceof Constant) {
			return new Cos(internal);
		}
		Product p = new Product();
		p.addFunction(new Cos(internal));
		p.addFunction(internal.derive());
		return p;
	}

	public double resolve(double x) {
		return Math.sin(internal.resolve(x));
	}

	public String toString() {
		return "Sin(" + internal.toString() + ")";
	}

}
