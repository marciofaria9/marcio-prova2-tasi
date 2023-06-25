package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDTO {
	
	private Integer code;
	private String name;
	private float price;
	private String provider;
	private String lastBuy;
	private boolean active;

}
