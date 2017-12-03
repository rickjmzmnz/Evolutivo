import gaframework.*;
import org.moeaframework.problem.tsplib.TSPInstance;
import org.moeaframework.problem.tsplib.DistanceTable;
import org.moeaframework.problem.tsplib.Tour;
import java.util.HashMap;

public class Funcion implements  FitnessFunction<Integer>{

	private TSPInstance datos;
	
	public Funcion(TSPInstance datos){
		this.datos = datos;
	}
	
	private int[] toArray(Phenotype<Integer> p){
		int[] a = new int[p.size()];
		for(int  i = 0; i < p.size(); i++){
			a[i] = p.getAllele(i);
		}
		return a;
	}	
	
	public double evaluate(Phenotype<Integer> p){
		return 100000 - Tour.createTour(toArray(p)).distance(datos);
	}
	

}
