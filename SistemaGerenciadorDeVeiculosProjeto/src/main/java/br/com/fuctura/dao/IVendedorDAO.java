package br.com.fuctura.dao;

import java.sql.Connection;

import br.com.fuctura.entidade.Vendedor;

public interface IVendedorDAO {

	public void cadastrarVendedor(Connection conexao, Vendedor vendedor);
	
	public Vendedor consultarVendedorNome (Connection conexao, String nome);

	void excluirVendedor(Connection conexao, String nome);

	void editarVendedor(Connection conexao, Vendedor vendedor, String nome);
	
}