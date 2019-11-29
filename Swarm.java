import java.util.ArrayList;
import java.util.Random;

public class Swarm {
	
	ArrayList<Particle> swarm;
	int dimensions;
	static int ITERATIONS = 50;
	Particle gbest;
	
	//proportion of velocity to be retained
	double A = 0.5;
	
	//proportion of personal best to be retained
	double B = 0.9;
	
	//proportion of informants best to be retained
	double C = 0.8;
	
	//proportion of global best to be retained
	double D = 0.9;
	
	//particle jump size
	double jump = 2;
	
	public Swarm(int swarmsize, int dimensions) {
		swarm = new ArrayList<Particle>();
		this.dimensions = dimensions;
		for(int i = 0; i < swarmsize; i++) {
			swarm.add(new Particle(i, this.dimensions));	
		}
		gbest = null;
	}
	
	public void randomInitParticles() {
		//for each particle
		for(Particle p: this.swarm) {
			
			//initialise random velocity
			for(int i = 0; i < p.velocity.length; i++) {
				p.velocity[i] = Math.random() * (1 - -1) + -1;
			}
			
			//initialise random position
			for(int i = 0; i < p.position.length; i++) {
				p.position[i] = Math.random() * (1 - -1) + -1;
			}
			
			p.pbest = p;
		}
	}
	
	public void setgbest() {
		for(Particle p: this.swarm) {
			double fitness = p.calculateFitness();
			if(gbest == null || fitness > gbest.calculateFitness()) {
				gbest = p;
			}
		}
	}
	
	public void setpbest() {
		for(Particle p: this.swarm) {
			double fitness = p.calculateFitness();
			if(p.pbest == null  || fitness > p.pbest.calculateFitness()) {
				p.pbest = p;
			}
		}
	}
	
	public void setInformants() {
		for(Particle p: this.swarm) {
			ArrayList<Particle> informants = new ArrayList<Particle>();
			Random rand = new Random();
			for(int i = 0; i < rand.nextInt(this.swarm.size()); i++) {
				for(int j = 0; j < rand.nextInt(this.swarm.size()); j++) {
					informants.add(this.swarm.get(j));
				}
			}
			p.informants = informants;
		}
	}
	
	public void moveParticles() {
		
		
		for(Particle p: this.swarm) {
			Particle x1 = p.pbest;
			Particle x2 = p.bestInformant();
			Particle x3 = this.gbest;
			double [] newvelocity = new double[dimensions];
			
			Random rand = new Random();
			double b = 0 + (B - 0) * rand.nextDouble();
			double c = 0 + (C - 0) * rand.nextDouble();
			double d = 0 + (D - 0) * rand.nextDouble();
			
			//for each dimension i
			for(int i = 0; i < newvelocity.length; i++) {
				newvelocity[i] = A*p.velocity[i] + b*(x1.position[i] - p.position[i]) + c*(x2.position[i] - p.position[i]) + d*(x3.position[i] - p.position[i]);
			}
			
			p.velocity = newvelocity;
			p.move();
		}
	}
	
	public static void main(String[] args) {
		Swarm s = new Swarm(4,2);
		s.randomInitParticles();
		s.setInformants();
		for(Particle p: s.swarm) {
			System.out.println(p.toString());
			System.out.println(p.printpbest());
			System.out.println("----------------------------------");
		}
		
		//for the number of iterations
		for(int i = 0; i < ITERATIONS; i++) {
			//for each particle in the swarm
			s.setgbest();
			s.setpbest();
			
			s.moveParticles();
			
			System.out.println("----------------Iteration " + i + "------------------");
			for(Particle p: s.swarm) {
				System.out.println(p.toString());
				System.out.println(p.printpbest());
				System.out.println("----------------------------------");
			}
		}
		
		System.out.println("==================================");
		System.out.println(s.gbest);
		
		
	}

}
