import java.util.ArrayList;
import java.util.Random;

public class Network {
	
	Layer inputlayer;
	ArrayList<Layer> hiddenlayers;
	Layer outputlayer;
	
	//constructor for specified input
	public Network(int numlay, int [] data, int numhid, int numout) {
		inputlayer = new Layer(data);
		hiddenlayers = new ArrayList<Layer>();
		for(int i = 0; i < numlay; i++) {
			Layer l = new Layer(numhid);
			hiddenlayers.add(l);
		}
		outputlayer = new Layer(numout);
	}
	
	
	public String toString() {
		return inputlayer.toString() + " " + hiddenlayers.toString() + " " + outputlayer.toString();
	}
	
	public void generateConnections() {
		Random rand = new Random();
		if(!this.hiddenlayers.isEmpty()) {
			for(int i = 0; i < hiddenlayers.size(); i++) {
				if(i == 0) {
					for(Neuron n: hiddenlayers.get(0).neurons) {
						for(Neuron ineu: inputlayer.neurons) {
							n.connections.add(new Connection(rand.nextDouble(), ineu,n));
						}
					}
				}else {
					for(Neuron n: hiddenlayers.get(i).neurons) {
						for(Neuron ineu: hiddenlayers.get(i-1).neurons) {
							n.connections.add(new Connection(rand.nextDouble(), ineu,n));
						}
					}
				}
			}
			
			for(Neuron n: outputlayer.neurons) {
				for(Neuron ineu: hiddenlayers.get(hiddenlayers.size()-1).neurons) {
					n.connections.add(new Connection(rand.nextDouble(), ineu,n));
				}
			}
		}else {
			for(Neuron n: outputlayer.neurons) {
				for(Neuron ineu: inputlayer.neurons) {
					n.connections.add(new Connection(rand.nextDouble(), ineu,n));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int [] data = {1,2,3};
		Network net = new Network(0, data , 5, 5);
		net.generateConnections();
		for(Neuron n: net.outputlayer.neurons) {
			System.out.println(n.connections);
		}
		for(Neuron n: net.outputlayer.neurons) {
			n.activate("sigmoid");
		}
		System.out.println(net);
	}

}
