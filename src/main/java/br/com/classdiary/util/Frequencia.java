package br.com.classdiary.util;

public enum Frequencia {
	
	Falta("falta"),
	Justificativa("justificativa"),
	Presenca("presenca");
	
	private final String frequencia;
	
	private Frequencia(String frequencia){
		this.frequencia = frequencia;
	}
	
	private String getFrequencia(){
		return this.frequencia;
	}
	
}
