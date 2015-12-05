package supermercado;

public class Caixa {
	private /*@ spec_public nullable @*/ Cliente clienteAtual; // cliente sendo atendido no caixa
	private int numeroAtendidos;

	public Caixa() {
		clienteAtual = null;
		numeroAtendidos = 0;
	}

	public void atenderNovoCliente(Cliente c) {
		clienteAtual = c;
	}

	public Cliente dispensarClienteAtual() {
		Cliente c = clienteAtual;
		clienteAtual = null;
		numeroAtendidos++;
		return c;
	}

	public boolean estaVazio() {
		return (clienteAtual == null);
	}

	public Cliente getClienteAtual() {
		Cliente retorno = new Cliente(0, 0);
		if(clienteAtual != null)
			retorno = clienteAtual;
		return retorno;
	}

	public int getNumeroAtendidos() {
		return numeroAtendidos;
	}
}
