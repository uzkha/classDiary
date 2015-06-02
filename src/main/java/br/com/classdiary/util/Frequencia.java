package br.com.classdiary.util;

public enum Frequencia {
	
	Falta("F"),
	Justificado("J"),
	Presente("P");

	private final String frequencia;
	
	private Frequencia(String frequencia){
		this.frequencia = frequencia;
	}
	
	public String getFrequencia(){
		return this.frequencia;
	}
	
}
