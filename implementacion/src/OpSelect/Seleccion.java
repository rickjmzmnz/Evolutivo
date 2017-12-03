import gaframework.*;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;

/**
 * Implementacion de la operacion Seleccion Uniforme o Rueda de Ruleta
 */
public class Seleccion<G,P> implements SelectionOp<G,P>{

    private double totalSeleccion;
    private Random r;

    /**
     * Se construye un objeto para poder realizar el operador de cruzamiento
     * @param probCruza - La probabilidad de que suceda el cruzamiento
     */
    public Seleccion(double totalSeleccion, int semilla){
    	this.totalSeleccion = totalSeleccion;
    	this.r = new Random(semilla);
    }

    public List<Individual<G,P>> select(Population<G,P> p){
    	double[] ruleta = new double[p.size()];
    	double sumaFitnes = 0.0;
    	for(int i = 0; i<p.size();i++){
    		ruleta[i] = p.getIndividual(i).getFitness();
    		sumaFitnes += ruleta[i]; 
    	}
    	ruleta[0] = ruleta[0]/sumaFitnes;
    	for(int i = 1; i<p.size();i++){
    		ruleta[i] = ruleta[i-1] + (ruleta[i]/sumaFitnes);
    	}
    	LinkedList<Individual<G,P>> l = new LinkedList<>();
    	for(int i = 0; i<totalSeleccion; i++){
    		int j = 0;
    		double v = r.nextDouble();
    		while(v >= ruleta[j]) j++;
    		l.add(p.getIndividual(j));
    	}
    	return l;
    }
    
}
