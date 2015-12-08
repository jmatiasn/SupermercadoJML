package supermercado;

/*
 * Classe utilitaria que realiza calculos de media aritmetica
 */


public class Acumulador {
	private  /*@ spec_public @*/ double valor;
	private  /*@ spec_public @*/ int contador;
	
	/*@ 
    @   assignable valor; 
    @   assignable contador; 
    @   ensures valor == 0 && contador == 0; 	  
   	@*/
	public Acumulador() {
		valor = 0;
		contador = 0;
	}
	/*@ 
	  @ assignable \nothing;
	  @ ensures \result == valor;
	  @*/
	public /*@ pure @*/ double getValor() {
		return (valor);
	}
	/*@ 
	  @ assignable \nothing;
	  @ ensures \result == contador;
 	@*/
	public /*@ pure @*/ int getContagem() {
		return contador;
	}

	/*@ 
	  @ assignable valor,contador;
	  @ ensures contador == \old (contador +1) && valor == \old (valor + n);
	@*/
	public void adicionar(double n) {
		valor = valor + n;
		contador++;
	}
	
	/*@ 
	  @ assignable valor,contador;
	  @ ensures contador == \old (contador +1) && valor == \old (valor + n);
	@*/
	public void adicionar(int n) {
		valor = valor + n;
		contador++;
	}

	/*@ 
	  @ requires contador != 0;
	  @ assignable valor,contador;
	  @ ensures \result == valor / contador ;
	  @ also
	  @ requires contador == 0 ;
	  @ assignable \nothing;
	  @ ensures \result == 0;
	@*/
	public double getMedia() {
		if (contador != 0)
			return valor / contador;
		else
			return 0;
	}
}