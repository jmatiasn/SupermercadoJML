package supermercado;

import java.util.Random;

/*
 * Esta classe indica se um cliente sera gerado de acordo com a probabilidade indicada no construtor
 */
public class GeradorClientes {
	private /*@ spec_public @*/double probabilidade;
	private /*@ spec_public @*/ int quantidadeGerada;
	private /*@ spec_public @*/ static final Random gerador = new Random(); // gerador de numeros
														// aleatorios de Java
	/*@ 
    @   assignable probabilidade; 
    @   assignable quantidadeGerada;  	
   	@ 	ensures probabilidade == p && quantidadeGerada == 0;   
   	@*/
	public GeradorClientes(double p) {
		probabilidade = p;
		quantidadeGerada = 0;
	}
	
	/* @ 
	   @ requires gerador.nextDouble() < probabilidade;
       @ assignable quantidadeGerada; 
       @ ensures quantidadeGerada == \old (quantidadeGerada +1); 	
   	   @ ensures \result == true;
	   @ 
	   @ also
	   @
	   @ requires gerador.nextDouble() >= probabilidade;
       @ assignable \nothing;  	
   	   @ ensures \result == false;
	   @ */
	public boolean gerar() {
		boolean gerado = false;
		if (gerador.nextDouble() < probabilidade) {
			quantidadeGerada++;
			gerado = true;
		}
		return gerado;
	}
	
	/*@	
	  @	assignable \nothing;
	  @	ensures \result == quantidadeGerada ;
 	@*/
	public /*@ pure @*/ int getQuantidadeGerada() {
		return quantidadeGerada;
	}
}
