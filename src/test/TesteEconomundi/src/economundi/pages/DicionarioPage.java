package economundi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DicionarioPage {
	private final WebDriver driver;
	private final WebDriverWait wait;

	public DicionarioPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public boolean isValida() {
		String title = driver.findElement(By.cssSelector("h1")).getText();

		if (title.equals("DICIONÁRIO")) {
			return true;
		}

		return false;
	}

	public String pesquisarPalavra(String palavra) throws InterruptedException {
		driver.findElement(By.cssSelector(".input")).click();
		driver.findElement(By.cssSelector(".input")).sendKeys(palavra);
		Thread.sleep(3000);

		String palavraRetorno = driver.findElement(By.cssSelector("li:nth-child(1)")).getText();
		
		System.out.println(palavraRetorno);

		if (palavraRetorno.equals(palavra)) {
			driver.findElement(By.cssSelector("li:nth-child(1)")).click();
		}

		String definicao = driver.findElement(By.cssSelector(".box-text-content")).getText();

		return definicao;
	}
}
