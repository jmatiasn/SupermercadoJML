package supermercado;

public class Caixa {
	//@ public invariant numeroAtendidos >= 0;
	
	private /*@ spec_public nullable @*/ Cliente clienteAtual; // cliente sendo atendido no caixa
	private /*@ spec_public @*/ int numeroAtendidos;
	
	//@ public initially clienteAtual == null;
	//@ public initially numeroAtendidos == 0;
	
	/*@ 
    @   assignable clienteAtual, numeroAtendidos; 
    @	ensures clienteAtual == null && numeroAtendidos == 0; 	  
   	@*/
	public Caixa() {
		clienteAtual = null;
		numeroAtendidos = 0;
	}

	/*@ 
    @   assignable clienteAtual; 
    @   ensures clienteAtual == c;  	  
   	@*/
	public void atenderNovoCliente(Cliente c) {
		clienteAtual = c;
	}
	
	/*@ 
	  @ assignable numeroAtendidos;
	  @ assignable clienteAtual;
	  @ ensures clienteAtual == null && numeroAtendidos == \old (numeroAtendidos  +1);
	  @ ensures \result == \old (clienteAtual);
 	@*/
	public Cliente dispensarClienteAtual() {
		Cliente c = clienteAtual;
		clienteAtual = null;
		numeroAtendidos++;
		return c;
	}

	/*@ 
	  @ assignable \nothing;
	  @ ensures \result == (clienteAtual == null);
	@*/
	public /*@ pure @*/ boolean estaVazio() {
		return (clienteAtual == null);
	}
	
	/*@ 
	  @ requires clienteAtual != null;
	  @ assignable \nothing;
	  @ ensures \result == clienteAtual ;
	@*/
	public /*@ pure @*/ Cliente getClienteAtual() { 
		Cliente retorno = new Cliente(0, 0);
		if(clienteAtual != null)
			retorno = clienteAtual;
		return retorno;
	}
	
	/*@ 
	  @ assignable \nothing;
	  @ ensures \result == numeroAtendidos;
 	@*/
	public /*@ pure @*/ int getNumeroAtendidos() {
		return numeroAtendidos;
	}
}
