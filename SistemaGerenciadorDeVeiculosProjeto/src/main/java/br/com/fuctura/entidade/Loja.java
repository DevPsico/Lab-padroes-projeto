package br.com.fuctura.entidade;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class Loja {

	private int codigo;
	private String nomeLoja;
	private int veiculoDigitado;
	Veiculo veiculo;
	Endereco endereco;

	// Método para exibir informações da loja
	@Override
	public String toString() {
		return "Loja{" + "codigo=" + codigo + ", nomeLoja='" + nomeLoja + '\'' + ", veiculoDigitado=" + veiculoDigitado
				+ ", CEP=" + endereco.getCep() + " " + endereco.getNumero() + "\n " + endereco.getLogradouro() + " "
				+ endereco.getComplemento() + " " + endereco.getBairro() + " " + endereco.getLocalidade() + " "
				+ endereco.getUf() + '}';
	}

	// Esse METODO esta completo na CLASSE ENDEREÇO ESTUDEM ELE
	public void consultarEnderecoPorCep(String cep) throws IOException, InterruptedException, URISyntaxException {
		Endereco endereco = new Endereco();
		endereco.consultarEnderecoPorCep(cep);
		this.endereco = endereco;
	}

	public void definirDadosDoEndereco(String numeroLoja) {
		if (endereco != null) {

			// this.setCodigo(Integer.parseInt(endereco.getCep().replace("-", "")));
			// this.setNomeLoja(endereco.getLogradouro());
			this.endereco.setCep(endereco.getCep());
			this.endereco.setLogradouro(endereco.getLogradouro());
			this.endereco.setComplemento(endereco.getComplemento());
			this.endereco.setBairro(endereco.getBairro());
			this.endereco.setLocalidade(endereco.getLocalidade());
			this.endereco.setUf(endereco.getUf());
			this.endereco.setNumero(numeroLoja);
			// this.endereco.setIbge(endereco.getIbge());
			// this.endereco.setGia(endereco.getGia());
			// this.endereco.setDdd(endereco.getDdd());
			// this.endereco.setSiafi(endereco.getSiafi());
			// Preencha outros campos da loja conforme necessário
		}
	}
}