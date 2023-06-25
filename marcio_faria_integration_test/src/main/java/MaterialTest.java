import static org.junit.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import DTO.MaterialDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MaterialTest {
	private ObjectMapper mapper = new ObjectMapper();
	private final String materialURL = "http://localhost:8080/materials";

	@Test
	public void testCreateMaterial() { // Teste criando com sucesso
		Response resp = createMaterial();
		resp.then().assertThat().statusCode(HttpStatus.SC_CREATED);
	}

	@Test
	public void testCreateMateiralExisting() { // Teste criando material que já existe
		
		createMaterial();
		Response resp = createMaterial();
		resp.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
	}

	private Response createMaterial() {
		MaterialDTO material = new MaterialDTO(1, "Teste", 2f, "Teste", "01-01-2000", true);
		Response resp = RestAssured.given().body(material).contentType(ContentType.JSON).post(materialURL);
		return resp;
	}

	@Test
	public void testCreatematerialInvalid() { //Teste Criando com Dado inválido (sem provider)
		Response resp = createMaterialWithoutProvider();
		resp.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
	}
	
	private Response createMaterialWithoutProvider() {
		MaterialDTO material = new MaterialDTO(1, "Teste", 2f, null, "01-01-2000", true);
		Response resp = RestAssured.given().body(material).contentType(ContentType.JSON).post(materialURL);
		return resp;
	}
	
	@Test
	public void testGetMaterialById() {  // Teste get by id com sucesso
		Integer materialId = 1;
		createMaterial();
		Response resp = RestAssured.get(materialURL + materialId);
		resp.then().assertThat().statusCode(HttpStatus.SC_OK);
	}
	
	public void testGetMaterialByIdInexisting () { // Teste get by id Inexistente
		Integer inexistingMaterialId = 2;
		createMaterial();
		Response resp = RestAssured.get(materialURL + inexistingMaterialId); 
		resp.then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
		
	}
	
	@Test
	public void testActive() {  // Teste do active com sucesso
		Integer code = 1;
		createMaterial();
		Response action = RestAssured.put(materialURL + "active/" + code);
		action.then().assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
		assertEquals(HttpStatus.SC_NO_CONTENT, action.getStatusCode());

		Response resp = RestAssured.get(materialURL + code);
		resp.then().assertThat().statusCode(HttpStatus.SC_OK);
	}
	
	
}