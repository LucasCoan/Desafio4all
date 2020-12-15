package br.lu.projeto4all.br.lu.projeto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class DSL {
	
	private WebDriver driver;

	public DSL(WebDriver driver) {
		super();
		this.driver = driver;
	}


	public void clicarBotaoXpath(By by) {
		driver.findElement(by).click();
	}


	public void clicarBotao(String id) {
		driver.findElement(By.id(id)).click();
	}

	
	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}

	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}

}
