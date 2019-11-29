
public class Connection {

	Neuron from;
	Neuron to;
	double weight;
	
	public Connection(Neuron from, Neuron to) {
		weight = 0.0;
		this.from = from;
		this.to = to;
	}
	
	public Connection(double value, Neuron from, Neuron to) {
		weight = value;
		this.from = from;
		this.to = to;
	}
	
	public String toString() {
		return from.toString() + " -> " + to.toString() + " = " + weight;
	}
}
