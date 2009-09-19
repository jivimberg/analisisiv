package functions.basics;

import java.util.ArrayList;
import java.util.List;

import functions.Constant;
import functions.Function;

public class Product implements Function {

	private List<Function> functions;

	public Product(List<Function> functions) {
		this.functions = functions;
	}

	public Product() {
		functions = new ArrayList<Function>();
	}

	public void addFunction(Function f) {
		functions.add(f);
	}

	@Override
	public Function derive() {
		return null;
	}

	@Override
	public double resolve(double x) {
		double result = 1;
		for (Function f : functions) {
			result *= f.resolve(x);
		}
		return result;
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < functions.size(); i++) {
			if (!functions.get(i).toString().equals("1.0")) {
				if (i != 0) {
					result += " * ";
				}
				result += functions.get(i).toString();
			}
		}
		return result;
	}

}
