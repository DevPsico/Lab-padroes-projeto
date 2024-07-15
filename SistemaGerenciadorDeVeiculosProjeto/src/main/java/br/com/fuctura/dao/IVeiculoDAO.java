package br.com.fuctura.dao;

import java.sql.Connection;

import br.com.fuctura.entidade.Veiculo;

public interface IVeiculoDAO {
	
	public void adicionarVeiculo (Connection conn, Veiculo veiculo);
	
	public boolean tipoExiste(Connection conexao, int codigoTipo);
	
	public Veiculo consultarVeiculoPorPlaca(Connection conexao, String placa);

	void excluiVeiculo(Connection conexao, String placa);

	void editarVeiculo(Connection conexao, Veiculo veiculo, String placa);

}
