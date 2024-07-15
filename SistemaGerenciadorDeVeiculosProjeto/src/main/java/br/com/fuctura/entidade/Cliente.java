package br.com.fuctura.entidade;

import java.io.IOException;
import java.net.URISyntaxException;

import lombok.Data;

@Data
public class Cliente {

	private int codigo;
	private String nome;
	private String cpf;
	private String celular;

	Endereco endereco;

	// Esse METODO esta completo na CLASSE ENDEREÇO ESTUDEM ELE
	public void consultarEnderecoPorCep(String cep) throws IOException, InterruptedException, URISyntaxException {
		Endereco endereco = new Endereco();
		endereco.consultarEnderecoPorCep(cep);
		this.endereco = endereco;
	}

	public void definirDadosDoEndereco(String numeroendereco) {
		if (endereco != null) {

			// this.setCodigo(Integer.parseInt(endereco.getCep().replace("-", "")));
			// this.setNomeLoja(endereco.getLogradouro());
			this.endereco.setCep(endereco.getCep());
			this.endereco.setNumero(numeroendereco);
			this.endereco.setLogradouro(endereco.getLogradouro());
			this.endereco.setComplemento(endereco.getComplemento());
			this.endereco.setBairro(endereco.getBairro());
			this.endereco.setLocalidade(endereco.getLocalidade());
			this.endereco.setUf(endereco.getUf());
			// this.endereco.setIbge(endereco.getIbge());
			// this.endereco.setGia(endereco.getGia());
			// this.endereco.setDdd(endereco.getDdd());
			// this.endereco.setSiafi(endereco.getSiafi());
			// Preencha outros campos da loja conforme necessário
		}
	}

}