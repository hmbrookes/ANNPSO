import java.util.ArrayList;

public class Neuron {
	
	double value;
	ArrayList<Connection> connections;
	
	public Neuron() {
		value = 0;
		connections = new ArrayList<Connection>();
	}
	
	public Neuron(int val) {
		value = val;
		connections = new ArrayList<Connection>();
	}
	
	public int inputSum() {
		int sum = 0;
		for(Connection c: connections) {
			System.out.println(c.from.value);
			sum += c.from.value * c.weight;
		}
		return sum;
	}
	
	public void activate(String function) {
		this.value = (1/( 1 + Math.pow(Math.E,(-1*this.inputSum()))));
	}
	
	public String toString() {
		return "" + value;
	}
}
