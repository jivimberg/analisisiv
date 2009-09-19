package functions.basics;

import java.util.ArrayList;
import java.util.List;

import functions.Constant;
import functions.Function;

public class Sum implements Function {

	private List<Function> functions;

	public Sum(List<Function> functions) {
		this.functions = functions;
	}

	public Sum() {
		functions = new ArrayList<Function>();
	}

	public void addFunction(Function f) {
		functions.add(f);
	}

	@Override
	public Function derive() {
		List<Function> derived = new ArrayList<Function>();
		for (Function f : functions) {
			derived.add(f.derive());
		}
		return new Sum(derived);
	}

	@Override
	public double resolve(double x) {
		double result = 0;
		for (Function f : functions) {
			result += f.resolve(x);
		}
		return result;
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < functions.size(); i++) {
			if (!functions.get(i).toString().equals("0.0")) {
				if (i != 0) {
					result += " + ";
				}
				result += functions.get(i).toString();
			}
		}
		return result;
	}
}
