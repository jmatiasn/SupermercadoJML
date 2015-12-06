package supermercado;

import java.util.Random;

public class Cliente {
	private /*@ spec_public nullable @*/ int numero; // numero do cliente
	private /*@ spec_public nullable @*/ int instanteChegada;
	private /*@ spec_public nullable @*/ int tempoAtendimento; // quantidade
																	// de tempo
																	// que resta
																	// para o
	// cliente no caixa
	private /*@ spec_public non_null @*/ static final Random gerador = new Random();
	public /*@ nullable @*/ static final int tempoMinAtendimento = 5;
	public /*@ nullable @*/ static final int tempoMaxAtendimento = 10;

	/*@ requires n >= 0 && c >=0;
	  @
	  @ assignable numero, instanteChegada, tempoAtendimento;
	  @
	  @ ensures numero == n && instanteChegada == c && tempoAtendimento >= 0;
	  @
	  @*/
	public Cliente(int n, int c) {
		numero = n;
		instanteChegada = c;
		tempoAtendimento = gerador.nextInt(tempoMaxAtendimento - tempoMinAtendimento + 1) + tempoMinAtendimento; // gera
																													// //
																													// 20
	}

	/*@
	  @ 
	  @ assignable \nothing;
	  @ 
	  @ ensures \result == numero;
	  @
	  @*/
	public /*@ pure @*/ int getNumero() {
		return numero;
	}

	/*@
	  @ 
	  @ assignable \nothing;
	  @ 
	  @ ensures \result == instanteChegada;
	  @*/
	public /*@ pure @*/ int getInstanteChegada() {
		return instanteChegada;
	}

	/*@
	  @ 
	  @ assignable tempoAtendimento;
	  @ 
	  @ ensures tempoAtendimento == \old (tempoAtendimento - 1);
	  @
	  @*/
	public void decrementarTempoAtendimento() {
		tempoAtendimento--;
	}

	/*@
	  @ 
	  @ assignable \nothing;
	  @ 
	  @ ensures \result == tempoAtendimento;
	  @
	  @*/
	public /*@ pure @*/ int getTempoAtendimento() {
		return tempoAtendimento;
	}
}
