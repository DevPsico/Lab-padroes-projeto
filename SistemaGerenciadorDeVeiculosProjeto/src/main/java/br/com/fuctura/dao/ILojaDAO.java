package br.com.fuctura.dao;

import java.sql.Connection;
import java.util.List;
import br.com.fuctura.entidade.Loja;
import br.com.fuctura.entidade.Veiculo;

public interface ILojaDAO {

	void cadastrarLoja(Connection conn, Loja loja);

	public boolean veiculoExiste(Connection conn, int codigoVeiculo);

	public List<Loja> listarLojas(Connection conexao);

	void excluiLoja(Connection conexao, String nomeLoja);

	void alterarLoja(Connection conn, Loja loja, String nomeLoja);

}