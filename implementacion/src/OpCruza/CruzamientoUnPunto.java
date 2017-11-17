
import gaframework.*;
import java.util.*;

/**
 * Implementacion de la operacion Cruzamiento de un punto
 */
public class CruzamientoUnPunto<G> implements CrossoverOp<G>{

    private double probCruza;

    /**
     * Se construye un objeto para poder realizar el operador de cruzamiento
     * @param probCruza - La probabilidad de que suceda el cruzamiento
     */
    public CruzamientoUnPunto(double probCruza){
	this.probCruza = probCruza;
    }

    /**
     * Método para obtener la probabilidad de cruzamiento
     * @return probCruza - La probabilidad de cruzamiento
     */
    public double getProbCruza(){
	return this.probCruza;
    }
    
    /**
     * Método para hacer cruzamiento de un punto
     * Seleccionamos un punto al azar de dos genotipos
     * Combinamos los dos genotipos para generar dos nuevos
     * @param lista - La lista de genotipos de la poblacion actual
     * @return lista - La lista con los dos nuevos genotipos que generamos
     */
    @Override
    public List<Genotype<G>> crossover(List<Genotype<G>> lista){
	Random random = new Random();
	double probCruza = Math.random();
	double probabilidad = this.getProbCruza();
        if(!lista.isEmpty() && lista.size() > 1){
	    if(probabilidad > probCruza){
		Genotype<G> gen1 = lista.get(0);
		Genotype<G> gen2 = lista.get(1);
		int tam = gen1.size();
		int punto = random.nextInt(tam - 1) + 1;
		System.out.println("El punto de corte está en la posición: " + punto);
		Genotype<G> cop1 = new Genotype<>(tam);
		Genotype<G> cop2 = new Genotype<>(tam);
		for(int i=0;i < punto;i++){
		    G g1 = gen1.getGene(i);
		    cop1.setGene(i,g1);
		    G g2 = gen2.getGene(i);
		    cop2.setGene(i,g2);
		}
		for(int j=punto; j < tam;j++){
		    G g3 = gen2.getGene(j);
		    cop1.setGene(j,g3);
		    G g4 = gen1.getGene(j);
		    cop2.setGene(j,g4);
		}
		lista.add(cop1);
		lista.add(cop2);
	    }
	}
	return lista;
    }

    /**
     * Método main para probar el método de cruzamiento de un punto
     * Se crean dos genotipos
     * Y al final se muestra como se cruzan estos dos genotipos 
     * Y se ven los nuevos individuos
     */
    public static void main(String[] args){
	Random r1 = new Random();
	Random r2 = new Random();
	CruzamientoUnPunto<Integer> cp = new CruzamientoUnPunto<>(0.9);
	List<Genotype<Integer>> lista = new ArrayList<Genotype<Integer>>();
	Genotype<Integer> g1 = new Genotype<>(10);
	Genotype<Integer> g2 = new Genotype<>(10);
	int tam = g1.size();
	for(int i=0; i < tam;i++){
		int llena1 = r1.nextInt(2);
		g1.setGene(i,llena1);
	}
	for(int j=0; j < tam;j++){
		int llena2 = r2.nextInt(2);
		g2.setGene(j,llena2);
	}
	lista.add(g1);
	lista.add(g2);
	System.out.println("Población actual");
	for(Genotype<Integer> g:lista){
	    System.out.println(g);
	}
	cp.crossover(lista);
	System.out.println("Población nueva");
	for(Genotype<Integer> g:lista){
	    System.out.println(g);
	}
    }
}
