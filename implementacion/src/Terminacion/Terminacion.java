import gaframework.*;

public class Terminacion<G,P> implements TerminationCondition<G,P>{

    private int term;

    /**
     * Se construye un objeto con el numero de terminación deseada
     * @param term - El numero de terminación
     */
    public Terminacion(int term){
		this.term = term;
    }

    /**
     * Método para obtener la terminación
     * @return term - La terminación obtenida
     */
    public int getTerm(){
		return this.term;
    }

    /**
     * Método para obtener la condición de paro del algoritmo genético
     * @param p - La población del algoritmo
     * @return - True si se alcanza la condición de termino, False si no
     */
    @Override
    public boolean conditionReached(Population<G,P> p){
		return p.getGeneration() >= this.getTerm();
    }

}
