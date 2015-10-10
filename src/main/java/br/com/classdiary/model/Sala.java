package br.com.classdiary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sala")
public class Sala {

	@Id	
	@GenericGenerator(name="salaid" , strategy="increment")
	@GeneratedValue(generator="salaid") 
	private Long id;
	
	@Column(length = 100)
	@NotNull
	private String nome;
		
	@Column(length = 100)
	@NotNull
	private String latitude;
	
	@Column(length = 100)
	@NotNull
	private String longitude;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
}
