package br.com.fuctura.entidade;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Endereco {

	private String cep;
	private String numero;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade; // cidade
	private String uf; // estado

	// Método para consultar e configurar o endereço a partir do CEP
	public void consultarEnderecoPorCep(String cep) throws IOException, InterruptedException, URISyntaxException {
		HttpClient client = HttpClient.newHttpClient();
		// a API q vamos acessar
		String url = "https://viacep.com.br/ws/" + cep + "/json/";
		// criando requisição com a URL
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		// Recebendo a respoda API
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		if (response.statusCode() == 200) {
			ObjectMapper mapper = new ObjectMapper();
			Endereco endereco = mapper.readValue(response.body(), Endereco.class);
			this.definirDadosDoEndereco(endereco);
		} else {
			throw new IOException("Erro ao consultar o serviço de CEP: " + response.statusCode());
		}
	}

	public void definirDadosDoEndereco(Endereco endereco) {
		if (endereco != null) {

			// this.setCodigo(Integer.parseInt(endereco.getCep().replace("-", "")));
			// this.setNomeLoja(endereco.getLogradouro());
			this.cep = endereco.cep;
			this.logradouro = endereco.logradouro;
			this.complemento = endereco.complemento;
			this.bairro = endereco.bairro;
			this.localidade = endereco.localidade;
			this.uf = endereco.uf;
			// this.endereco.setIbge(endereco.getIbge());
			// this.endereco.setGia(endereco.getGia());
			// this.endereco.setDdd(endereco.getDdd());
			// this.endereco.setSiafi(endereco.getSiafi());
			// Preencha outros campos da loja conforme necessário
		}
	}

}