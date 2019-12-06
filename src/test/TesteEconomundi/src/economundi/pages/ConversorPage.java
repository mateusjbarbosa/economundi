package economundi.pages;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConversorPage {
	private final WebDriver driver;

	public ConversorPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
	}

	public boolean isValida() throws InterruptedException {
		Thread.sleep(5000);

		String title = driver.findElement(By.cssSelector("h1")).getText();

		if (title.equals("ÍNDICES")) {
			return true;
		}

		return false;
	}

	public Float getDolar() {
		String dolar = "";

		driver.findElement(By.cssSelector(".box-indexes:nth-child(1) .variation-negative")).click();
		dolar = driver.findElement(By.cssSelector(".variation-info-negative:nth-child(1)")).getText();

		Float dolarF = Float.parseFloat(dolar.substring(9, 13));

		return dolarF;
	}

	public String efetuarCambio(Integer valor, String moeda) {
		String cambio = "";

		driver.findElement(By.cssSelector("input:nth-child(2)")).click();
		driver.findElement(By.cssSelector("input:nth-child(2)")).sendKeys(valor.toString());
		driver.findElement(By.cssSelector("select")).click();
		{
			WebElement dropdown = driver.findElement(By.cssSelector("select"));
			dropdown.findElement(By.xpath("//option[. = '" + moeda + "']")).click();
		}

		cambio = driver.findElement(By.xpath("//input[@value=\'$ 9.55\']")).getAttribute("value");

		return cambio;
	}
}
