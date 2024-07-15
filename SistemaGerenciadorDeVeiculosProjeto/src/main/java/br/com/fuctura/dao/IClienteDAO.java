package br.com.fuctura.dao;

import java.sql.Connection;

import br.com.fuctura.entidade.Cliente;

public interface IClienteDAO {

	public void cadastrarCliete(Connection conexao, Cliente cliente);

	public Cliente localizarVendedorCPF(Connection conexao, String cpf);

	void excluirCliente(Connection conexao, String cpf);

	void atualizaCliente(Connection conexao, Cliente cliente, String cpf);

}
