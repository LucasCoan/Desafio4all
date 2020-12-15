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

public class ShopCartDesafio2 {

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
		//EXECUTA PROCESSO DE PRINT
		TakesScreenshot ss = (TakesScreenshot) getDriver();
		File Arquivo = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Arquivo, new File(
				"target" + File.separator + "screenshot" + File.separator + testName.getMethodName() + ".jpg"));
		driver.quit();

	}

	private TakesScreenshot getDriver() {
		return null;
	}
	
	public void finalizaCompra(int qtdItens) {

		float soma = 0;

		for (int i = 1; i <= qtdItens; i++) {

			int qtd = Integer
					.parseInt(driver.findElement(By.xpath("//li[" + i + "]//p[contains(@id,'qtd')]")).getText());
			String valorFull = driver.findElement(By.xpath("//li[" + i + "]//p[contains(@id,'price')]")).getText();
			valorFull = valorFull.replace("R$", "").trim();
			valorFull = valorFull.replace(",", ".");
			float valor = Float.parseFloat(valorFull);

			soma += (qtd * valor);

			System.out.println("VALOR DA SOMA (" + i + " iten(s)): " + soma);
		}

		float subTotal = Float.parseFloat(driver.findElement(By.xpath("//p[contains(@id,'subtotal-price')]")).getText()
				.replace("R$", "").trim().replace(",", "."));
		float totalCheckout = Float.parseFloat(driver.findElement(By.xpath("//p[contains(@id,'price-total-checkout')]"))
				.getText().replace("R$", "").trim().replace(",", "."));

		if (soma == subTotal && soma == totalCheckout && subTotal == totalCheckout) {
			System.out
					.println("Total Soma: " + soma + "\nSubtotal: " + subTotal + "\nTotal checkout: " + totalCheckout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='finish-checkout-button']")))
					.click();
		}
	}

	@Test
	public void telaShopCartDesafio2() {
		WebDriverWait wait3 = new WebDriverWait(driver, 20);
		wait3.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='open-categories-btn']"))));

		page.selecionarCategoria();
		page.selecionaCatBebidas();

		page.adicionarCocaCola();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//div[contains(text(),\\\"O produto 'Coca-cola lata' foi adicionado ao carri\\\")]"))));
		System.out.println("Coca Cola Adicionado ao Carrinho");

		Assert.assertEquals("O produto 'Coca-cola lata' foi adicionado ao carrinho",
				page.validaMensagemAdicaoCocaCola());

		page.adicionarFanta();
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		wait1.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//div[contains(text(),\\\"O produto 'Fanta uva lata' foi adicionado ao carri\\\")]"))));
		System.out.println("Fanta uva lata' foi adicionado ao carrinho");

		Assert.assertEquals("O produto 'Fanta uva lata' foi adicionado ao carrinho",
				page.validaMensagemAdicaoCocaCola());

		page.adicionarAguaMineral();
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//div[contains(text(),\\\"O produto 'Água mineral sem gás' foi adicionado ao\\\")]"))));
		System.out.println("Água mineral sem gás foi adicionado ao carrinho");

		Assert.assertEquals("O produto 'Água mineral sem gás' foi adicionado ao carrinho",
				page.validaMensagemAdicaoCocaCola());

		page.selecionarCategoria();
		page.selecionarRissoleMedio();
		page.clicarCarrinho();

		// ADIDIONA 9 RISSOLES AO CARRINHO
		page.addQuantidadeRissoles(9);

		// REMOVE 4 RISSOLES AO CARRINHO, MANTENDO 5 NO TOTAL.
		page.remQuantidadeRissoles(4);
		
		finalizaCompra(2);
		
		page.clicarfinalizarCompra();
		Assert.assertEquals("Pedido realizado com sucesso!", page.validaMensagemPedidoRealizado());
		page.clicarFechar();

	}
}