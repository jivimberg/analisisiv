package function;

public class Sum implements Function{

	private Function first;
	private Function second;
	
	public Sum(Function first, Function second) {
		this.first = first;
		this.second = second;
	}
	
	@Override
	public Function derive() {
		return new Sum(first.derive(), second.derive());
	}

	@Override
	public double resolve(double x) {
		return first.resolve(x) + second.resolve(x);
	}
	
	public String toString() { 
		return first.toString() + " + " + second.toString();
	}
}
