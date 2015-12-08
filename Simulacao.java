package supermercado;

/*
 * Classe com a logica da simulacao passo-a-passo
 */
public class Simulacao {
	//@ public invariant duracao == 200;
	//@ public invariant probabilidadeChegada == 0.1;
	
	private /*@ spec_public @*/ static final int duracao = 200;
	private /*@ spec_public @*/ static final double probabilidadeChegada = 0.1;
	private /*@ spec_public @*/ QueueTAD<Cliente> fila;
	private /*@ spec_public @*/ Caixa caixa;
	private /*@ spec_public @*/ GeradorClientes geradorClientes;
	private /*@ spec_public @*/ Acumulador statTemposEsperaFila;
	private /*@ spec_public @*/ Acumulador statComprimentosFila;
	private /*@ spec_public @*/ boolean trace; // valor indica se a simulacao ira imprimir
							// passo-a-passo os resultados

	/*@
	  @ requires (t == true) || (t == false);
	  @
	  @ assignable fila, caixa, geradorClientes, statTemposEsperaFila, statComprimentosFila, trace;
	  @
	  @ ensures fila == new QueueLinked<Cliente>() && caixa == new Caixa() && 
	  			geradorClientes == new GeradorClientes(probabilidadeChegada) &&
	  			statTemposEsperaFila == new Acumulador() &&
	  			statComprimentosFila == new Acumulador() &&
	  			trace == t;
	  @
	  @*/
	public Simulacao(boolean t) {
		fila = new QueueLinked<Cliente>();
		caixa = new Caixa();
		geradorClientes = new GeradorClientes(probabilidadeChegada);
		statTemposEsperaFila = new Acumulador();
		statComprimentosFila = new Acumulador();
		trace = t;
	}

	public void simular() {
		// realizar a simulacao por um certo numero de passos de duracao
		for (int tempo = 0; tempo < duracao; tempo++) {
			verificaClienteChegou(tempo);
			verificaCaixaVazio(tempo);
			statComprimentosFila.adicionar(fila.size());
		}
	}

	private void verificaCaixaVazio(int tempo) {
		// TODO Auto-generated method stub
		// verificar se o caixa esta vazio
		if (caixa.estaVazio()) {
			// se o caixa esta vazio, atender o primeiro cliente da fila se
			// ele existir
			if (!fila.isEmpty()) {
				// tirar o cliente do inicio da fila e atender no caixa
				caixa.atenderNovoCliente(fila.remove());
				statTemposEsperaFila.adicionar(tempo - caixa.getClienteAtual().getInstanteChegada());
				if (trace)
					System.out.println(tempo + ": cliente " + caixa.getClienteAtual().getNumero() + " chega ao caixa.");
			}
		} else {
			// se o caixa ja esta ocupado, diminuir de um em um o tempo de
			// atendimento ate chegar a zero
			if (caixa.getClienteAtual().getTempoAtendimento() == 0) {
				if (trace)
					System.out.println(tempo + ": cliente " + caixa.getClienteAtual().getNumero() + " deixa o caixa.");
				caixa.dispensarClienteAtual();
			} else {
				caixa.getClienteAtual().decrementarTempoAtendimento();
			}
		}
	}

	private void verificaClienteChegou(int tempo) {
		// TODO Auto-generated method stub
		// verificar se um cliente chegou
		if (geradorClientes.gerar()) {
			// se cliente chegou, criar um cliente e inserir na fila do
			// caixa
			Cliente c = new Cliente(geradorClientes.getQuantidadeGerada(), tempo);
			fila.add(c);
			if (trace)
				System.out.println(tempo + ": cliente " + c.getNumero() + " (" + c.getTempoAtendimento()
						+ " min) entra na fila - " + fila.size() + " pessoa(s)");
		}
	}

	/*@
	  @ assignable fila, caixa, geradorClientes, statTemposEsperaFila, statComprimentosFila;
	  @
	  @ ensures fila == new QueueLinked<Cliente>() && 
	  			caixa == new Caixa() && 
	  			geradorClientes == new GeradorClientes(probabilidadeChegada) &&
	  			statTemposEsperaFila == new Acumulador() &&
	  			statComprimentosFila == new Acumulador();
	  @
	  @*/
	public void limpar() {
		fila = new QueueLinked<Cliente>();
		caixa = new Caixa();
		geradorClientes = new GeradorClientes(probabilidadeChegada);
		statTemposEsperaFila = new Acumulador();
		statComprimentosFila = new Acumulador();
	}

	
	public /*@ pure @*/ void imprimirResultados() {
		System.out.println();
		System.out.println("Resultados da Simulacao");
		System.out.println("Duracao:" + duracao);
		System.out.println("Probabilidade de chegada de clientes:" + probabilidadeChegada);
		System.out.println("Tempo de atendimento minimo:" + Cliente.tempoMinAtendimento);
		System.out.println("Tempo de atendimento maximo:" + Cliente.tempoMaxAtendimento);
		System.out.println("Cliente atendidos:" + caixa.getNumeroAtendidos());
		System.out.println("Clientes ainda na fila:" + fila.size());
		System.out.println("Cliente ainda no caixa:" + caixa.getClienteAtual());
		System.out.println("Total de clientes gerados:" + geradorClientes.getQuantidadeGerada());
		System.out.println("Tempo medio de espera:" + statTemposEsperaFila.getMedia());
		System.out.println("Comprimento medio da fila:" + statComprimentosFila.getMedia());
	}
}
