package com.irs.register.register.shared.dto;


public interface CommonDTO {
	
	//CommonDTO, que nada mais é do que uma interface de marcação para os DTOs da aplicação, nele podemos ver que usamos o Builder que a TaxPayer fornece, passando os dados que iremos receber no POST da API.
	
	String getType();

}
