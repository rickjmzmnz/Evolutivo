import gaframework.*;
import org.moeaframework.problem.tsplib.TSPInstance;
import org.moeaframework.problem.tsplib.DistanceTable;
import org.moeaframework.problem.tsplib.Tour;
import java.util.HashMap;

public class Funcion implements  FitnessFunction<Integer>{

	private Population<String,Integer> poblacion;
	private TSPInstance datos;
	private HashMap<Phenotype<Integer>,Double> distancia;
	double maximo;
	double minimo;
	
	public void setPoblacion(Population<String,Integer> poblacion){
		this.poblacion = poblacion;
	}
	
	public Funcion(TSPInstance datos){
		this.datos = datos;
	}
	
	public void establecer(){
		minimo = Double.MAX_VALUE;
		maximo = 0;
		int n = poblacion.size();
		distancia = new HashMap<>();
		for(int i = 0; i < n; i++){
			Phenotype<Integer> p = poblacion.getIndividual(i).getPhenotype();
			Tour t = Tour.createTour(toArray(p));
			double d = t.distance(datos);
			distancia.put(p,d);
			if(maximo < d)
				maximo = d;
			if(d < minimo)
				minimo = d; 
		}
	}
	
	private int[] toArray(Phenotype<Integer> p){
		int[] a = new int[p.size()];
		for(int  i = 0; i < p.size(); i++){
			a[i] = p.getAllele(i);
		}
		return a;
	}
	
	
	public double evaluate(Phenotype<Integer> p){
//		if(poblacion == null)
//			return 0;
//		return maximo + minimo - distancia.get(p);
		return 100000 - Tour.createTour(toArray(p)).distance(datos);
	}
	

}
