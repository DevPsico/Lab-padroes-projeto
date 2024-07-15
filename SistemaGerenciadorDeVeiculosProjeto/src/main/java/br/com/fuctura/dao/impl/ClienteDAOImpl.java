package br.com.fuctura.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fuctura.dao.IClienteDAO;
import br.com.fuctura.entidade.Cliente;
import br.com.fuctura.entidade.Endereco;

public class ClienteDAOImpl implements IClienteDAO {

	@Override
	public void cadastrarCliete(Connection conexao, Cliente cliente) {
		// TODO Auto-generated method stub

		try {

			String comandoSql = "insert into cliente (nome, cpf, celular, enderecocep, endereconumero, enderecologradouro, "
					+ "enderecocomplemento, enderecobairro, enderecocidade, enderecouf) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pstm = conexao.prepareStatement(comandoSql);

			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getCpf());
			pstm.setString(3, cliente.getCelular());
			pstm.setString(4, cliente.getEndereco().getCep());
			pstm.setString(5, cliente.getEndereco().getNumero());
			pstm.setString(6, cliente.getEndereco().getLogradouro());
			pstm.setString(7, cliente.getEndereco().getComplemento());
			pstm.setString(8, cliente.getEndereco().getBairro());
			pstm.setString(9, cliente.getEndereco().getLocalidade());
			pstm.setString(10, cliente.getEndereco().getUf());

			pstm.execute();

			pstm.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public Cliente localizarVendedorCPF(Connection conexao, String cpf) {
		// TODO Auto-generated method stub

		String comandoSQL = "select * from cliente where cpf = ?";
		Cliente cliente = null;

		try {

			PreparedStatement pstm = conexao.prepareStatement(comandoSQL);

			pstm.setString(1, cpf);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				cliente = new Cliente();

				cliente.setCodigo(rs.getInt("codigo"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setCelular(rs.getString("celular"));

				Endereco endereco = new Endereco();

				endereco.setCep(rs.getString("enderecocep"));
				endereco.setNumero(rs.getString("endereconumero"));
				endereco.setLogradouro(rs.getString("enderecologradouro"));
				endereco.setComplemento(rs.getString("enderecocomplemento"));
				endereco.setBairro(rs.getString("enderecobairro"));
				endereco.setLocalidade(rs.getString("enderecocidade"));
				endereco.setUf(rs.getString("enderecouf"));

				cliente.setEndereco(endereco);

			}
			rs.close();
			pstm.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cliente;
	}

	@Override
	public void excluirCliente(Connection conexao, String cpf) {
		try {
			String comandoSQL = "DELETE FROM cliente WHERE cpf = ?";

			PreparedStatement pstm = conexao.prepareStatement(comandoSQL);

			pstm.setString(1, cpf);

			int linhasAfetadas = pstm.executeUpdate();

			if (linhasAfetadas > 0) {
				System.out.println("Cliente excluído com sucesso!");
			} else {
				System.out.println("Nenhum cliente encontrado com o ID informado.");
			}

		} catch (SQLException e) {
			System.err.println("Erro ao excluir cliente: " + e.getMessage());
		}
	}

	@Override
	public void atualizaCliente(Connection conexao, Cliente cliente, String cpf) {
		try {
			String comandoSQL = "UPDATE cliente SET nome = ?, cpf = ?, celular = ?, "
					+ "enderecocep = ?, endereconumero = ?, enderecologradouro = ?, "
					+ "enderecocomplemento = ?, enderecobairro = ?, "
					+ "enderecocidade = ?, enderecouf = ? WHERE cpf = ?";

			PreparedStatement pstm = conexao.prepareStatement(comandoSQL);

			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getCpf());
			pstm.setString(3, cliente.getCelular());
			pstm.setString(4, cliente.getEndereco().getCep());
			pstm.setString(5, cliente.getEndereco().getNumero());
			pstm.setString(6, cliente.getEndereco().getLogradouro());
			pstm.setString(7, cliente.getEndereco().getComplemento());
			pstm.setString(8, cliente.getEndereco().getBairro());
			pstm.setString(9, cliente.getEndereco().getLocalidade());
			pstm.setString(10, cliente.getEndereco().getUf());
			pstm.setString(11, cpf);

			int linhasAfetadas = pstm.executeUpdate();

			if (linhasAfetadas > 0) {
				System.out.println("Cliente atualizado com sucesso!");
			} else {
				System.out.println("Nenhum cliente encontrado com o ID informado.");
			}

		} catch (SQLException e) {
			System.err.println("Erro ao atualizar cliente: " + e.getMessage());
		}
	}
}