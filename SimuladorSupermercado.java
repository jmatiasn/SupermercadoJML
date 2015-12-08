package supermercado;

/*
 * Programa principal da simulacao
 */

public class SimuladorSupermercado {
	private static /*@ spec_public @*/Simulacao sim ;
	
	/*@ assignable sim;
	  @ ensures sim == new Simulacao(true); 
	@*/
	public static void main(String[] args) {
		sim = new Simulacao(true);
		sim.simular();
		sim.imprimirResultados();
	}
}
