package economundi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CadastrarPage {
	private final WebDriver driver;

	public CadastrarPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
	}

	public boolean isValida() {
		String title = driver.findElement(By.cssSelector("h1")).getText();

		if (title.equals("OLÁ!")) {
			return true;
		}

		return false;
	}

	public String efetuarCadastro(String nome, String sobrenome, String dataNasc, String email, String senha)
			throws InterruptedException {
		String saudacao;

		driver.findElement(By.cssSelector(".profile-box-register")).click();

		Thread.sleep(2000);

		driver.findElement(By.cssSelector(".data-user > input:nth-child(1)")).click();
		driver.findElement(By.cssSelector(".data-user > input:nth-child(1)")).sendKeys(nome);

		Thread.sleep(2000);

		driver.findElement(By.cssSelector(".data-user > input:nth-child(2)")).sendKeys(sobrenome);

		Thread.sleep(2000);

		driver.findElement(By.cssSelector("input:nth-child(3)")).sendKeys(dataNasc);

		Thread.sleep(2000);

		driver.findElement(By.cssSelector(".data-credentials > input:nth-child(1)")).sendKeys(email);
		driver.findElement(By.cssSelector(".data-credentials > input:nth-child(2)")).sendKeys(email);
		driver.findElement(By.cssSelector("input:nth-child(4)")).sendKeys(senha);
		driver.findElement(By.cssSelector("input:nth-child(5)")).sendKeys(senha);

		Thread.sleep(2000);

		driver.findElement(By.cssSelector(".btn-login")).click();

		Thread.sleep(5000);

		if (driver.findElement(By.cssSelector(".data-user-error")).isDisplayed()) {
			saudacao = "ERRO AO CADASTRAR!";
		} else {
			saudacao = driver.findElement(By.cssSelector(".pop-up-green > h2")).getText();
		}

		return saudacao;
	}
}
