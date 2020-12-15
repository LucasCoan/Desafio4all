package br.lu.projeto4all.br.lu.projeto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShopCartPage {

	private DSL dsl;

	public ShopCartPage(WebDriver driver) {
		dsl = new DSL(driver);

	}

	public void selecionarCategoria() {
		dsl.clicarBotaoXpath(By.xpath("//div[@id='open-categories-btn']"));
	}

	public void selecionarCatDoces() {
		dsl.clicarBotao("category-1");
	}

	public void adicionarBrigadeiro() {
		dsl.clicarBotao("add-product-0-btn");
	}

	public void adicionarAlfajor() {
		dsl.clicarBotao("add-product-5-btn");
	}

	public void clicarCarrinho() {
		dsl.clicarBotao("cart-btn");
	}

	public void clicarQtdBrigadeiro() {
		dsl.clicarBotao("add-product-4-qtd");
	}

	public void clicarfinalizarCompra() {
		dsl.clicarBotao("finish-checkout-button");
	}

	public void clicarFechar() {
		dsl.clicarBotaoXpath(By.xpath("//button[contains(text(),'Fechar')]"));

	}

	public void addQuantidadeBrigadeiro(int qtd) {
		for (int i = 0; i <= qtd; i++) {
			dsl.clicarBotao("add-product-5-qtd");
		}
	}

	public void addQuantidadeRissoles(int qtd) {
		for (int i = 0; i <= qtd; i++) {
			dsl.clicarBotao("add-product-3-qtd");
		}
	}

	public void remQuantidadeRissoles(int qtd) {
		for (int i = 0; i <= qtd; i++) {
			dsl.clicarBotao("add-product-3-qtd");
		}
	}

	
	public void selecionaCatBebidas() {
		dsl.clicarBotao("category-0");

	}

	public void adicionarCocaCola() {
		dsl.clicarBotao("add-product-0-btn");
	}

	public void adicionarFanta() {
		dsl.clicarBotao("add-product-1-btn");
	}

	public void adicionarAguaMineral() {
		dsl.clicarBotao("add-product-2-btn");
	}

	public void selecionarRissoleMedio() {
		dsl.clicarBotao("add-product-3-btn");
	}

	public void clickMultiplosRissoleMedio(int x) {
		for (int i = 0; i < x; i++) {
			dsl.clicarBotao("add-product-3-btn");
		}
	}

	public void clickMultiplosRemoveRissoleMedio(int x) {
		for (int i = 0; i < x; i++) {
			dsl.clicarBotao("remove-product-2-qtd");
		}

	}
	// ----------------------------------VALIDAÇÕES---------------------------//

	public String validaMensagemAdicaoBrigadeiro() {
		return dsl
				.obterTexto(By.xpath("//div[contains(text(),\"O produto 'Brigadeiro' foi adicionado ao carrinho\")]"));
	}

	public String validaMensagemAdicaoAlfajor() {
		return dsl
				.obterTexto(By.xpath("//div[contains(text(),\"O produto 'Alfajor de chocolate' foi adicionado ao\")]"));
	}

	public String validaMensagemPedidoRealizado() {
		return dsl.obterTexto(By.xpath("//h2[contains(text(),'Pedido realizado com sucesso!')]"));

	}

	public String validaMensagemAdicaoCocaCola() {
		return dsl
				.obterTexto(By.xpath("//div[contains(text(),\"O produto 'Coca-cola lata' foi adicionado ao carri\")]"));
	}

	public String validaMensagemAdicaoFanta() {
		return dsl
				.obterTexto(By.xpath("//div[contains(text(),\"O produto 'Fanta uva lata' foi adicionado ao carri\")]"));
	}

	public String validaMensagemAdicaoAguaMinetal() {
		return dsl
				.obterTexto(By.xpath("//div[contains(text(),\"O produto 'Água mineral sem gás' foi adicionado ao\")]"));

	}
}
