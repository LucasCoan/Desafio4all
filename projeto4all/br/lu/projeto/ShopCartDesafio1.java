package br.lu.projeto4all.br.lu.projeto;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShopCartDesafio1 {

	private WebDriver driver;
	private DSL dsl;
	private ShopCartPage page;
	WebDriverWait wait;

	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://shopcart-challenge.4all.com/");
		dsl = new DSL(driver);
	}

	@Rule
	public TestName testName = new TestName();

	@After
	public void finaliza() throws IOException {
		TakesScreenshot ss = (TakesScreenshot) getDriver();
		File Arquivo = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Arquivo, new File(
				"target" + File.separator + "screenshot" + File.separator + testName.getMethodName() + ".jpg"));
		driver.quit();

	}

	private TakesScreenshot getDriver() {
		return null;
	}

	@Test
	public void telaShopCartDesafio1() {
		WebDriverWait wait3 = new WebDriverWait(driver, 20);
		wait3.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='open-categories-btn']"))));

		page.selecionarCategoria();
		page.selecionarCatDoces();

		page.adicionarBrigadeiro();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//div[contains(text(),\\\"O produto 'Brigadeiro' foi adicionado ao carrinho\\\")]"))));
		System.out.println("Brigadeiro foi adicionado ao carrinho");

		Assert.assertEquals("O produto 'Brigadeiro' foi adicionado ao carrinho", page.validaMensagemAdicaoBrigadeiro());

		page.adicionarAlfajor();
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		wait1.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//div[contains(text(),\\\"O produto 'Alfajor de chocolate' foi adicionado ao\\\")]"))));
		System.out.println("Alfajor de chocolate foi adicionado ao carrinho");

		Assert.assertEquals("O produto 'Alfajor de chocolate' foi adicionado ao carrinho",
				page.validaMensagemAdicaoBrigadeiro());

		page.clicarCarrinho();
		page.addQuantidadeBrigadeiro(4);
		page.clicarfinalizarCompra();

		Assert.assertEquals("Pedido realizado com sucesso!", page.validaMensagemPedidoRealizado());

		page.clicarFechar();

	}

}
