package org.estudos.br;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ConsultaIBGEMockTest {
    @Mock
    private HttpURLConnection connectionMock;

    // JSON de resposta simulada
    private static final String JSON_RESPONSE_ESTADO = "{\"id\":35,\"sigla\":\"SP\",\"nome\":\"São Paulo\",\"regiao\":{\"id\":3,\"sigla\":\"SE\",\"nome\":\"Sudeste\"}}";    // Método executado antes de cada teste
    private static final String JSON_RESPONSE_DISTRITO = "[{\"id\":160030312,\"nome\":\"Fazendinha\",\"municipio\":{\"id\":1600303,\"nome\":\"Macapá\",\"microrregiao\":{\"id\":16003,\"nome\":\"Macapá\",\"mesorregiao\":{\"id\":1602,\"nome\":\"Sul do Amapá\",\"UF\":{\"id\":16,\"sigla\":\"AP\",\"nome\":\"Amapá\",\"regiao\":{\"id\":1,\"sigla\":\"N\",\"nome\":\"Norte\"}}}},\"regiao-imediata\":{\"id\":160001,\"nome\":\"Macapá\",\"regiao-intermediaria\":{\"id\":1601,\"nome\":\"Macapá\",\"UF\":{\"id\":16,\"sigla\":\"AP\",\"nome\":\"Amapá\",\"regiao\":{\"id\":1,\"sigla\":\"N\",\"nome\":\"Norte\"}}}}}}]";    // Método executado antes de cada teste
    private static final String JSON_RESPONSE_REGIAO = "[{\"id\":31,\"sigla\":\"MG\",\"nome\":\"Minas Gerais\",\"regiao\":{\"id\":3,\"sigla\":\"SE\",\"nome\":\"Sudeste\"}},{\"id\":32,\"sigla\":\"ES\",\"nome\":\"Espírito Santo\",\"regiao\":{\"id\":3,\"sigla\":\"SE\",\"nome\":\"Sudeste\"}},{\"id\":33,\"sigla\":\"RJ\",\"nome\":\"Rio de Janeiro\",\"regiao\":{\"id\":3,\"sigla\":\"SE\",\"nome\":\"Sudeste\"}},{\"id\":35,\"sigla\":\"SP\",\"nome\":\"São Paulo\",\"regiao\":{\"id\":3,\"sigla\":\"SE\",\"nome\":\"Sudeste\"}}]";    // Método executado antes de cada teste

    @BeforeEach
    public void setup() throws IOException {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);

        // Configura o comportamento do mock
        InputStream inputStream = new ByteArrayInputStream(JSON_RESPONSE_DISTRITO.getBytes());
        when(connectionMock.getInputStream()).thenReturn(inputStream);
    }

    @Test
    @DisplayName("Consulta de Estado utilizando o Mock")
    public void testConsultarEstadoComMock() throws IOException {
        // Sigla do estado a ser consultado
        String estadoUf = "SP";

        // Act (Execução do método a ser testado)
        String response = ConsultaIBGE.consultarEstado(estadoUf);

        // Verificamos se o JSON retornado é o mesmo que o JSON de resposta simulada
        assertEquals(JSON_RESPONSE_ESTADO, response, "O JSON retornado não corresponde ao esperado.");
    }

    @Test
    @DisplayName("Consulta de Distrito utilizando o Mock")
    public void testConsultarDistritoComMock() throws IOException {
        // Sigla do estado a ser consultado
        int id = 160030312;

        // Act (Execução do método a ser testado)
        String response = ConsultaIBGE.consultarDistrito(id);

        // Verificamos se o JSON retornado é o mesmo que o JSON de resposta simulada
        assertEquals(JSON_RESPONSE_DISTRITO, response, "O JSON retornado não corresponde ao esperado.");
    }

    @Test
    @DisplayName("Consulta de Região utilizando o Mock")
    public void testConsultarRegiaoComMock() throws IOException {
        // Sigla do estado a ser consultado
        int id = 31;

        // Act (Execução do método a ser testado)
        String response = ConsultaIBGE.consultarRegiao(id);

        // Verificamos se o JSON retornado é o mesmo que o JSON de resposta simulada
        assertEquals(JSON_RESPONSE_REGIAO, response, "O JSON retornado não corresponde ao esperado.");
    }
}
