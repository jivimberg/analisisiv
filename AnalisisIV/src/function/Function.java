package function;

public interface Function {

	double resolve(double x);

	Function derive();
	
	String toString();
}
