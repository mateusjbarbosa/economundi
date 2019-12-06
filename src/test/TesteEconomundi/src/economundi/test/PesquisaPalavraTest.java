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

import economundi.pages.DicionarioPage;

public class PesquisaPalavraTest {

	static WebDriver driver;
	static WebDriverWait wait;

	public PesquisaPalavraTest() {
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
	public void testPesquisarPalavraOk() throws InterruptedException {
		System.out.println("testPesquisarPalavraOk");
		
		String definicao = "";
		String expectedDefinicao = "Rendimento percentual a que se tem direito pelo dinheiro emprestado, investido ou depositado, durante um determinado período de tempo. O juro traduz o privilégio que possui o proprietário dum capital de, mesmo sem fornecer qualquer trabalho ou desenvolver qualquer atividade, receber rendimentos periódicos, mantendo intacto ou aumentado o capital inicial. O capital emprestado pode ser solicitado por agentes económicos ativos para obterem com ele uma mais-valia, que em parte será entregue ao prestamista.";

		driver.get("http://economundi-frontend.herokuapp.com/dicionario");

		DicionarioPage dicionario = new DicionarioPage(driver, wait);

		if (dicionario.isValida()) {
			definicao = dicionario.pesquisarPalavra("JURO");
		}
		
		assertTrue(definicao.equals(expectedDefinicao));
	}
	
	@Test
	public void testPesquisarPalavraNok() throws InterruptedException {
		System.out.println("testPesquisarPalavraNok");

		driver.get("http://economundi-frontend.herokuapp.com/dicionario");

		DicionarioPage dicionario = new DicionarioPage(driver, wait);

		if (dicionario.isValida()) {
			String definicao = dicionario.pesquisarPalavra("ECONOMUNDI");

			assertTrue(definicao.isEmpty());
		}
	}

}
