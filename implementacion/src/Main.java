import org.moeaframework.problem.tsplib.TSPInstance;
import org.moeaframework.problem.tsplib.DistanceTable;
import org.moeaframework.problem.tsplib.Tour;
import gaframework.*;
import java.io.File;
import java.io.IOException;

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
    }

}
