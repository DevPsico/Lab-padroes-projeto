package br.com.fuctura.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.fuctura.entidade.Venda;

public interface IVendaDAO {
	
	public void adicionarVenda(Connection conexao, Venda venda);
	
	public int lojaExiste(Connection conexao, String nomeLoja);
	
	public int vendedorExiste(Connection conexao, String nomeVendedor);
	
	public int clienteExiste(Connection conexao, String cpf);
	
	public double getValorVeiculo(Connection conexao, String placaVeiculo) throws SQLException;

	void excluirVenda(Connection conexao, int codigoVenda);

}
