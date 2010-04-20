package functions.basics;

import java.util.ArrayList;
import java.util.List;

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
			if (!f.isConstant()) {
				derived.add(f.derive());
			}
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

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < functions.size(); i++) {
			Function f = functions.get(i);

			if (!result.equals("") && f.isPositive()) {
				result += " + ";
			} else if (!result.equals("") && f.isNegative()) {
				result += " ";
			}

			if (f.toString().equals("0")) {
			} else {
				result += f.toString();
			}
		}
		return result;
	}

	@Override
	public String getType() {
		return "Sum";
	}

	@Override
	public boolean isNegative() {
		return false;
	}

	@Override
	public boolean isPositive() {
		return false;
	}
	
	@Override
	public boolean isConstant() {
		for(Function f: functions) {
			if(!f.isConstant()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public double getCoefficient() {
		return 1;
	}
	
	@Override
	public Function getFunctionWithoutCoefficient() {
		return new Sum(functions);
	}	
}
