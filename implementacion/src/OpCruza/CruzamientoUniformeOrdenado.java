import gaframework.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Implementacion de la operacion Cruzamiento de un punto
 */
public class CruzamientoUniformeOrdenado<G> implements CrossoverOp<G>{

    private double probCruza;
    Random random;

    /**
     * Se construye un objeto para poder realizar el operador de cruzamiento
     * @param probCruza - La probabilidad de que suceda el cruzamiento
     */
    public CruzamientoUniformeOrdenado(double probCruza, Random random){
		this.probCruza = probCruza;
		this.random = random;
    }

    /**
     * Método para obtener la probabilidad de cruzamiento
     * @return probCruza - La probabilidad de cruzamiento
     */
    public double getProbCruza(){
	return this.probCruza;
    }
    
    /**
     * Método para hacer cruzamiento uniforme ordenado
     * Creamos dos nuevos genotipos vacíos y una mascara con l bits, l es la longitud de los padres
     * Se van llenando los dos nuevos de acuerdo a una variable que itera sobre los padres
     * Esto nos asegura que al hacer los cruzamientos, no agregamos genes repetidos
     * @param lista - La lista de genotipos de la poblacion actual
     * @return lista - La lista con los dos nuevos genotipos que generamos
     */
    @Override
    public List<Genotype<G>> crossover(List<Genotype<G>> lista){
		List<Genotype<G>> nueva = new LinkedList<>();
		List<G> listaGenes;
		double probCruza = random.nextDouble();
		double probabilidad = this.getProbCruza();
		if(!lista.isEmpty() && lista.size() > 1){
			Genotype<G> gen1 = lista.get(0);
			Genotype<G> gen2 = lista.get(1);
			if(probabilidad >= probCruza){		
				int tam = gen1.size();
				Genotype<G> h = new Genotype<>(tam);
				Genotype<G> f = new Genotype<>(tam);
				Genotype<G> h2 = new Genotype<>(tam);
				Genotype<G> f2 = new Genotype<>(tam);
				Genotype<Integer> mascara = new Genotype<>(tam);
				h = generaCruze(gen1,gen2,h,f,mascara);
				h2 = generaCruze(gen2,gen1,h2,f2,mascara);
				nueva.add(h);
				nueva.add(h2);
			}else{
				nueva.add(gen1);
				nueva.add(gen2);
			}
		}
		return lista;
    }

    /*
     * Genera el cruzamiento dado dos padres, los dos genotipos vacíos y la mascara
     */
    private Genotype<G> generaCruze(Genotype<G> gen1,Genotype<G> gen2,Genotype<G> h,Genotype<G> f,Genotype<Integer> mascara){
		List<G> listaGenes;
		int tam = gen1.size();
		for(int i=0;i<tam;i++){
			int bin = random.nextInt(2);
			mascara.setGene(i,bin);
		}
		int faltan = 0;
		for(int i=0;i<tam;i++){
			int genMas = mascara.getGene(i);
				if(genMas == 1){
				G g = gen1.getGene(i);
				h.setGene(i,g);
				}else{
				G g = gen1.getGene(i);
				f.setGene(faltan,g);
				faltan++;
				}
		}
		listaGenes = ordenaGenes(f,gen2);
		f = permutaGenes(listaGenes,f);
		int k = 0;
		for(int i=0;i<tam;i++){
			G g = h.getGene(i);
			if(g == null){
			G gf = f.getGene(k);
			h.setGene(i,gf);
			k++;
			}
		}
		return h;
    }

    /*
     * Permuta los genes del genotipo dado el orden de la lista
     */
    private Genotype<G> permutaGenes(List<G> lista,Genotype<G> gen){
		for(int i=0;i<lista.size();i++){
			G g = lista.get(i);
			gen.setGene(i,g);
		}
		return gen;
    }

    /*
     * Ordena los genes del primero genotipo dado el orden del segundo genotipo 
     */
    private List<G> ordenaGenes(Genotype<G> gen1,Genotype<G> gen2){
		List<G> lista = new ArrayList<G>();
		List<G> listGen1 = obtenGenes(gen1);
		List<G> listGen2 = obtenGenes(gen2);
		int tam = gen1.size();
		int n = 0;
		int k = 0;
		G g1;
		G g2;
		for(int i=0;i<tam;i++){
			g1 = gen1.getGene(i);
			if(g1 != null){
				for(int j=0;j<tam;j++){
					g2 = gen2.getGene(j);
					if(g1.equals(g2)){
						if(k >= n){
							if(lista.size() == 0){
								lista.add(0,g1);
							} else {
								lista.add(lista.size(),g1);
							}
						}else{
							lista.add(0,g1);
						}
					} else {
						k++;
					}
					
				}
				n = k;
				k = 0;
			}
		}
		return lista;
    }

    /*
     * Obtiene los genes de un genotipo y los guarda en una lista
     */
    private List<G> obtenGenes(Genotype<G> gen){
		List<G> lista = new ArrayList<G>();
		for(int i=0;i<gen.size();i++){
			G g = gen.getGene(i);
			lista.add(g);
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
		Random r = new Random();
		CruzamientoUniformeOrdenado<Integer> cp = new CruzamientoUniformeOrdenado<>(0.9,r);
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
