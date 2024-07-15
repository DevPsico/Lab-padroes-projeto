package br.com.fuctura.entidade;

import lombok.Data;

@Data
public class Venda {
	
	private int codigo;
	private double valorTotal;
	private String nomeVendedor;
	private String cpfCliente;
	private String nomeLoja;
	private String placaVeiculo;
	
	Loja loja;
	Cliente cliente;
	Vendedor vendedor;
	
}