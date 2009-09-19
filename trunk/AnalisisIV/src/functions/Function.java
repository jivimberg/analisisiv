package functions;

public interface Function {

	double resolve(double x);

	Function derive();
	
	String toString();
}
