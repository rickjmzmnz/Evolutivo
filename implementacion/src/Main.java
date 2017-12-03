import org.moeaframework.problem.tsplib.TSPInstance;
import org.moeaframework.problem.tsplib.DistanceTable;
import org.moeaframework.problem.tsplib.Tour;
import gaframework.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * Ejemplo de como leer los archivos tsp
 */
public class Main{

    public static void main(String[] args) throws IOException{
    	String instancia = "ulysses16";
    	double tasaCruzamiento = 0.1;
    	double tasaMutacion = 0.1;
    	int semilla = 100;
    	int totalSeleccion = 2;
    	int maxIteracion = 1000;
    	int popSize = 200;
		TSPInstance problem = new TSPInstance(new File(String.format("data/tsp/%s.tsp",instancia)));
		System.out.println(problem.getName());
		System.out.println(problem.getDimension());
		double max = 100000;
		double min = 0;
		try{
			problem.addTour(new File(String.format("data/tsp/%s.opt.tour",instancia)));
			for(Tour tour: problem.getTours()){
				System.out.println(tour);
				min = tour.distance(problem);
				System.out.println(min);
			}
		}catch(Exception e){
		}
		Random random = new Random(semilla);
		Codificacion cd = new Codificacion(problem.getDimension(),random);
		CruzamientoUniformeOrdenado<String> cp = new CruzamientoUniformeOrdenado<>(tasaCruzamiento,random);
		MutacionUniforme<String> mu = new MutacionUniforme<>(tasaMutacion,random);
		Seleccion<String,Integer> sel = new Seleccion<>(totalSeleccion,random);
		Funcion f = new Funcion(problem);
		Terminacion<String,Integer> t = new Terminacion<>(maxIteracion);
		Simple<String,Integer> ags = new Simple<String,Integer>(cd,null,cp,mu,sel,f,t,popSize,max,min);
		ags.run();
	}
}
