package br.edu.univas.tasi.prova.dto;

import br.edu.univas.tasi.prova.entities.MaterialEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialDTO {
	private Integer code;
	private String name;
	private float price;
	private String provider;
	private String lastBuy;
	private boolean active;
	
	public MaterialDTO(MaterialEntity material) {
		
		this.code = material.getCode();
		this.name = material.getName();
		this.price = material.getPrice();
		this.provider = material.getProvider();
		this.lastBuy = material.getLastBuy();
		this.active = material.isActive();
		
	}

}
