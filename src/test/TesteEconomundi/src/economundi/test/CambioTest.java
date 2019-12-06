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
import economundi.pages.ConversorPage;

public class CambioTest {

	static WebDriver driver;
	static WebDriverWait wait;

	public CambioTest() {
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
	public void testCambioOk() throws InterruptedException {
		System.out.println("testCambio");
		
		String expectedCambio = "$ 9.55";
		String cambio = "";

		driver.get("http://economundi-frontend.herokuapp.com/indices/");

		ConversorPage conversor = new ConversorPage(driver, wait);

		if (conversor.isValida()) {
			cambio = conversor.efetuarCambio(40, "Dólar");
		}

		assertTrue(cambio.equals(expectedCambio));

	}

}
