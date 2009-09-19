package functions;

import functions.basics.Product;

public class Cos implements Function {

	private Function internal;
	
	public Cos(Function internal) {
		this.internal = internal;
	}
	
	
	//ESTA MAL!!!!!!!!!!! NO USAR INSTANCEOF!!!!
	public Function derive() {
		if(internal instanceof Variable || internal instanceof Constant) {
			return new Sin(internal);
		}
		Product p = new Product();
		p.addFunction(new Sin(internal));
		p.addFunction(internal.derive());
		return p;
	}

	public double resolve(double x) {
		return Math.cos(internal.resolve(x));
	}
	
	public String toString() {
		return "Cos(" + internal.toString() + ")";
	}
}
