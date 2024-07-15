package br.com.fuctura.entidade;

import java.util.Date;
import lombok.Data;

@Data
public class Veiculo {

	private int codigo;
	private String placa;
	private String modelo;
	private Date ano;
	private double valor;
	private int tipoDigitado;
	Tipo tipo;
}