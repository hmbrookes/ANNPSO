import java.util.ArrayList;

public class Layer {

	ArrayList<Neuron> neurons;
	
	public Layer() {
		neurons = new ArrayList<Neuron>();
	}
	
	public Layer(int neu) {
		neurons = new ArrayList<Neuron>();
		for(int i = 0; i < neu; i++) {
			Neuron n = new Neuron();
			neurons.add(n);
		}
	}
	
	public Layer(int [] lneurons) {
		neurons = new ArrayList<Neuron>();
		for(int n: lneurons) {
			Neuron ne = new Neuron(n);
			neurons.add(ne);
		}
	}

	public String toString() {
		return neurons.toString();
	}
}
