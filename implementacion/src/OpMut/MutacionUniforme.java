import gaframework.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * Implementación de la operación mutación uniforme
 */
public class MutacionUniforme<G> implements MutationOp<G>{

    private double probMut;

    /**
     * Se construye un objeto para poder realizar el operador de mutación
     * @param probMut - La probabilidad de que suceda la mutación
     */
    public MutacionUniforme(double probMut){
	this.probMut = probMut;
    }

    /**
     * Método para obtener la probabilidad de mutación
     * @return probMut - La probabilidad de mutación
     */
    public double getProbMut(){
	return this.probMut;
    }

    /**
     * Método para guardar en una lista los genes de un genotipo
     * @param g - El genotipo del cual se obtienen los genes
     * @return lista - La lista con los genes del genotipo
     */
    public List<G> obtenGenes(Genotype<G> g){
	List<G> lista = new ArrayList<G>();
	for(int i=0;i<g.size();i++){
	    G gen = g.getGene(i);
	    lista.add(gen);
	}
	return lista;
    }

    /**
     * Método para eliminar un gen en una lista de genes
     * @param lista - Una lista de genes
     * @param gen - El gen a eliminar de la lista de genes
     */
    public void eliminaGen(List<G> lista,G gen){
	G genLista;
	for(int i=0;i<lista.size();i++){
	    genLista = lista.get(i);
	    if(genLista.equals(gen)){
		lista.remove(i);
		break;
	    }
	}
    }

    /**
     * Método para hacer mutación uniforme
     * Por cada gen del genotipo se calcula su probabilidad de mutación
     * Si muta, entonces se elige un gen al azar de los disponibles en la lista de genes
     * Si no, se coloca el gen actual, si ya no se encuentra disponible ese gen entonces se elige uno al azar de la lista de genes
     * @param g - El genotipo a mutar
     * @return nuevo -  El genotipo mutado
     */
    @Override
    public Genotype<G> mutate(Genotype<G> g){
	List<G> lista = obtenGenes(g);
	Genotype<G> nuevo = new Genotype<G>(g.size());
	Random r = new Random();
	G gen;
	double probabilidad = this.getProbMut();
	double probCruza;
	for(int i=0;i<g.size();i++){
	    probCruza = Math.random();
	    if(probabilidad > probCruza){
		int tam = lista.size();
		if(tam == 1){
		    gen = lista.get(0);
		    nuevo.setGene(i,gen);
		} else {
		    int gr = r.nextInt(tam - 1) + 1;
		    gen = lista.get(gr);
		    nuevo.setGene(i,gen);
		    eliminaGen(lista,gen);
		}
	    }else{
		gen = g.getGene(i);
		if(lista.contains(gen)){
		    nuevo.setGene(i,gen);
		    eliminaGen(lista,gen);
		} else {
		    int tam = lista.size();
		    if(tam == 1){
			gen = lista.get(0);
			nuevo.setGene(i,gen);
		    } else {
			int gr = r.nextInt(tam - 1) + 1;
			gen = lista.get(gr);
			nuevo.setGene(i,gen);
			eliminaGen(lista,gen);
		    }
		}
	    }
	}
	return nuevo;
    }

    /**
     * Método main para probar la mutación uniforme
     * Se crea un genotipo de enteros del 1 al 6
     * Y se muta ese genotipo
     * Al final se muestra el genotipo original y el mutado
     */
    public static void main(String[] args){
	MutacionUniforme<Integer> mu = new MutacionUniforme<>(0.5);
	Genotype<Integer> g = new Genotype<Integer>(6);
	Genotype<Integer> nuevo = new Genotype<Integer>(6);
	int n = 1;
	for(int i=0;i<g.size();i++){
	    g.setGene(i,n);
	    n++;
	}
	System.out.println("Genotipo original = " + g.toString());
	nuevo = mu.mutate(g);
	System.out.println("Genotipo mutado = " + nuevo.toString());
    }
    
}
