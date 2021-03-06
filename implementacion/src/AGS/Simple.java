
import gaframework.*;
import java.util.*;

public class Simple<G,P> implements GeneticAlgorithm<G,P> {

    private Breeder<G,P> breeder;
    private CrossoverOp<G> crossoverOp;
    private MutationOp<G> mutationOp;
    private SelectionOp<G,P> selectionOp;
    private TerminationCondition<G,P> termination;
    private FitnessFunction<P> fun;
    private final int popSize;
    private double max;
    private double min;

    public Simple(Codification<G,P> cod,
		  Corrector<G> cor,
		  CrossoverOp<G> cro,
		  MutationOp<G> muo,
		  SelectionOp<G,P> seo,
		  FitnessFunction<P> fun,
		  TerminationCondition<G,P> ter,
		  int popSize,
		  double max,
		  double min){
		this.breeder = new Breeder<>(cod, cor, fun);
		this.fun = fun;
		this.crossoverOp = cro;
		this.mutationOp = muo;
		this.selectionOp = seo;
		this.termination = ter;
		this.popSize = popSize;
		this.max = max;
		this.min = min;
    }

    public Population<G,P> iteration(Population<G,P> current) {
		Population<G,P> out = new Population<>(current.getGeneration() + 1);
		out.addIndividual(current.getWorstIndividual());
		//elitismo
		out.addIndividual(current.getBestIndividual());
		while (out.size() < current.size()) {
			// Seleccion
			List<Individual<G,P>> selectionList = selectionOp.select(current);
			// Cruza
			List<Genotype<G>> genotypeList = new LinkedList<>();
			for (Individual<G,P> s:selectionList)
			genotypeList.add(s.getGenotype());
			List<Genotype<G>> crossedList = crossoverOp.crossover(genotypeList);
			// Mutacion
			List<Genotype<G>> mutatedList = new LinkedList<>();
			for (Genotype<G> c:crossedList)
			mutatedList.add(mutationOp.mutate(c));
			// Nuevos individuos
			for (Genotype<G> m:mutatedList)
			out.addIndividual(breeder.newIndividual(m));
		}
		return out;	    
    }
    
    public void run(){
		Population<G,P> p = breeder.newRandomPopulation(popSize);
		while(!termination.conditionReached(p)){
			p = iteration(p);
			double d = (max - p.getBestIndividual().getFitness());
			System.out.println(p.getGeneration()+" "+(((d-min)/min)*100));
		}
    }

}
