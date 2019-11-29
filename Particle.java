import java.util.ArrayList;

public class Particle {
	
	int name;
	double [] velocity;
	double [] position;
	Particle pbest;
	ArrayList<Particle> informants;
	
	
	public Particle(int name, int dimensions) {
		this.name = name;
		velocity = new double [dimensions];
		position = new double [dimensions];
		pbest = null;
	}
	
	public Particle bestInformant() {
		Particle bestinformant = null;
		for(Particle p: informants) {
			if(bestinformant == null || p.calculateFitness() > bestinformant.calculateFitness()) {
				bestinformant = p;
			}
		}
		
		if(bestinformant == null) {
			return this;
		}
		return bestinformant;
	}
	
	public double calculateFitness() {
		double sum = 0;
		for(int i = 0; i < this.position.length; i++) {
			sum += Math.pow(this.position[i], 2);
		}
		return sum + 1;
	}
	
	public void move() {
		for(int i = 0; i < this.position.length; i++) {
			this.position[i] += this.velocity[i];
		}
	}
	
	public String toString() {
		String s = "Particle " + name +": Velocity = ";
		for(int i = 0; i < this.velocity.length; i++) {
			s += " " + velocity[i];
		}
		String p = " Position = ";
		for(int i = 0; i < this.position.length; i++) {
			p += " " + position[i];
		}
		return s + p;
	}
	
	public String printpbest() {
		return "Personal Best: " + this.pbest.toString();
	}
	
	
}
