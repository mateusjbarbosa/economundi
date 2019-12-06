package economundi.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import economundi.pages.CadastrarPage;

public class CadastrarTest {

	static WebDriver driver;
	static WebDriverWait wait;

	public CadastrarTest() {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C://Users//mateu//Downloads//geckodriver.exe");
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 60);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCadastrarOk() throws InterruptedException {
		System.out.println("testCadastrarOk");

		String expectedSaudacao = "USUÁRIO CRIADO COM SUCESSO, ATIVE SUA CONTA ATRAVÉS DO E-MAIL QUE ENVIAMOS!";
		String saudacao = "";

		driver.get("http://economundi-frontend.herokuapp.com/perfil/");

		CadastrarPage perfil = new CadastrarPage(driver, wait);

		if (perfil.isValida()) {
			saudacao = perfil.efetuarCadastro("Teste5", "Usuario", "1999-01-01", "teste5.usuario@email.com",
					"12345678");
		}

		assertTrue(saudacao.equals(expectedSaudacao));

	}

	@Test
	public void testCadastrarNok() throws InterruptedException {
		System.out.println("testCadastrarNok");

		String expectedSaudacao = "ERRO AO CADASTRAR!";
		String saudacao = "";

		driver.get("http://economundi-frontend.herokuapp.com/perfil/");

		CadastrarPage perfil = new CadastrarPage(driver, wait);

		if (perfil.isValida()) {
			saudacao = perfil.efetuarCadastro("Teste3", "Usuario", "", "teste3.usuario@email.com",
					"12345678");
		}

		assertTrue(saudacao.equals(expectedSaudacao));

	}

}
