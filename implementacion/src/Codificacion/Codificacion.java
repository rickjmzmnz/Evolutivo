import gaframework.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase para poder codificar fenotipos y decodificar genotipos
 */
public class Codificacion implements Codification<String,Integer>{

    private int tam;
    private Random random;

    /**
     * Se construye un objeto con el tamaño del nuevo genotipo o fenotipo
     * @param tam - el tamaño del nuevo genotipo a decodificar o fenotipo a codificar
     */
    public Codificacion(int tam, Random random){
		this.tam = tam;
		this.random = random; 
    }

    /**
     * Método para codificar un fenotipo
     * Por cada ciudad que se representa por un entero se codifica con su representación en binario
     * @param phenotype - el fenotipo a codificar
     * @return gen - el genotipo que representa el fenotipo codificado
     */
    @Override
    public Genotype<String> encode(Phenotype<Integer> phenotype){
	Genotype<String> gen = new Genotype<String>(this.tam);
	for(int i=0;i<this.tam;i++){
	    int n = phenotype.getAllele(i);
	    String s = Integer.toBinaryString(n);
	    gen.setGene(i,s);
	}
	return gen;
    }

    /**
     * Método para decodificar un genotipo
     * Cada ciudad representada en binario lo decodificamos a su representación en entero
     * @param genotype - el genotipo a decodificar
     * @return phen - el fenotipo que representa la decodificación del genotipo
     */
    @Override
    public Phenotype<Integer> decode(Genotype<String> genotype){
		Phenotype<Integer> phen = new Phenotype<Integer>(this.tam);
		for(int i=0;i<this.tam;i++){
			String s = genotype.getGene(i);
			int n = Integer.parseInt(s,2);
			phen.setAllele(i,n);
		}
		return phen;
    }

    /**
     * Método que genera un genotipo con base en genes aleatorios
     * Se toman los genes a partir de un intervalo de 1 al tamaño del objeto más 1
     * Los genes son enteros en su representación en binario
     * @return gen - el genotipo generado a partir de genes aleatorios
     */
    @Override
    public Genotype<String> newRandomGenotype(){
		List<Integer> lista = new ArrayList<Integer>();
		Genotype<String> gen = new Genotype<String>(this.tam);
		int g;
		String s;
		for(int i=1;i<this.tam+1;i++){
			lista.add(i);
		}
		for(int i=0;i<this.tam;i++){
			int tamList = lista.size();
			if(tamList == 1){
			g = lista.get(0);
			s = Integer.toBinaryString(g);
			gen.setGene(i,s);
			}else{
			int gr = random.nextInt(tamList);
			g = lista.get(gr);
			s = Integer.toBinaryString(g);
			gen.setGene(i,s);
			lista.remove(new Integer(g));
			}
		}
		return gen;
    }

    /**
     * Se crea un objeto codificación de tamaño 10
     * Se crea un genotipo aleatorio
     * Se decodifica el genotipo
     * Se codifica el fenotipo generado anteriormente 
     */
    public static void main(String[] args){
		Random r = new Random();
		Codificacion c = new Codificacion(10,r);
		Genotype<String> gen = c.newRandomGenotype();
		System.out.println("Genotipo generado aleatoriamente = " + gen.toString());
		Phenotype<Integer> phen = c.decode(gen);
		System.out.println("El genotipo se decodificó a = " + phen.toString());
		gen = c.encode(phen);
		System.out.println("El fenotipo se codificó a = " + gen.toString());
    }
    
}
