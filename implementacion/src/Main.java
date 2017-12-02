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
		TSPInstance problem = new TSPInstance(new File("data/tsp/ulysses16.tsp"));
		System.out.println(problem.getName());
		System.out.println(problem.getDimension());
		problem.addTour(new File("data/tsp/ulysses16.opt.tour"));
		for(Tour tour: problem.getTours()){
			System.out.println(tour.distance(problem));
		}
		Codificacion cd = new Codificacion(16);
		CruzamientoUniformeOrdenado<String> cp = new CruzamientoUniformeOrdenado<>(0.9);
		MutacionUniforme<String> mu = new MutacionUniforme<>(0.5);
		Seleccion<String,Integer> sel = new Seleccion<>(8,100);
		Funcion f = new Funcion(problem);
		Terminacion<String,Integer> t = new Terminacion<>(100);
		int popSize = 1000;
		Simple<String,Integer> ags = new Simple<String,Integer>(cd,null,cp,mu,sel,f,t,popSize);
		ags.run();
	}
}
