package com.mateeooss.projeto.usando.openAI;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

import static com.mateeooss.projeto.usando.openAI.service.ImageService.imageToBase64;

@SpringBootApplication
@Slf4j
public class ProjetoUsandoOpenAiApplication {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "src\\drive\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Boolean existQrCode = false;
		Boolean botOn = false;
//		try{

			driver.get("https://web.whatsapp.com/");
			By QRCodeLocator = By.xpath("/html/body/div[1]/div/div[2]/div[3]/div[1]/div/div/div[2]/div/canvas");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera até 10 segundos
			WebElement qrCodeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(QRCodeLocator));

			if(Objects.nonNull(qrCodeElement)) existQrCode = true;
//			wait.until(ExpectedConditions.visibilityOf(qrCodeElement));

		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		while(existQrCode){
			System.out.println("ta mostrando");
			try {
				botOn = existQrCode = driver.findElement(QRCodeLocator).isDisplayed();
				Thread.sleep(2000);
			} catch (NoSuchElementException e) {
				existQrCode = false;
				botOn = true;
				System.out.println("---QRCode lido com sucesso---");
			} catch (Exception e) {
				// Lida com a interrupção do thread, se necessário
				botOn = existQrCode = false;
				System.out.println("navegador fechado");
				driver.quit();
			}
		}

		while(botOn){
			System.out.println("Bot Funcionando");
			try {
				WebDriverWait waitChat = new WebDriverWait(driver, Duration.ofSeconds(10));
				java.util.List<WebElement> chatTeste = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"pane-side\"]/*//span[@title=\"mateeooss Novo Pohha\"]")));

				for (WebElement chat : chatTeste){
					chat.click();
					WebDriverWait waitInbox = new WebDriverWait(driver, Duration.ofSeconds(10));
					WebElement inboxText = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div[2]/div[1]/p"))).get(0);

					inboxText.sendKeys("bot enviando");
					inboxText.sendKeys(Keys.RETURN);
//					WebElement send = driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[2]/button/span"));
//					send.click();
				}
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				log.error("erro em tempo de execução");
				throw new RuntimeException(e);
			}
		}


		// Utilizar uma biblioteca de imagem para cortar a screenshot para incluir apenas o elemento
//		BufferedImage imagemCompleta = null;
//		try{
//			imagemCompleta = ImageIO.read(screenshotFile);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}

		// Obter as coordenadas do elemento
//		int x = qrCodeElement.getLocation().getX();
//		int y = qrCodeElement.getLocation().getY();
//		int width = qrCodeElement.getSize().getWidth();
//		int height = qrCodeElement.getSize().getHeight();

//		BufferedImage imagemCortada = imagemCompleta.getSubimage(x, y, width, height);
//		String out = "D:\\teste\\QRCodeZap.png";
//
//		try {
//			ImageIO.write(imagemCortada, "png", new File(out));
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}

//			System.out.println("passo 3");
//			Screenshot screenshot = new AShot()
//					.shootingStrategy(ShootingStrategies.viewportPasting(1000))
//					.takeScreenshot(driver, qrCodeElement);
//			System.out.println("passo 4");
//			System.out.println("passo");
//			String base64Image = null;
//			try {
//				base64Image = imageToBase64(imagemCortada);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			String asciiArt = convertImageToAsciiArt(imagemCortada, 100);
			// Imprime o código Base64 no console
//			System.out.println("QR Code em formato Base64:");
//			System.out.println(asciiArt);

			// Agora, permita que o usuário veja o QR code no console
//			System.out.println("Por favor, leia o QR code no console.");
//		} catch (Exception e){
//			System.out.println(e.getMessage());
//		}

//		driver.quit();
//		SpringApplication.run(ProjetoUsandoOpenAiApplication.class, args);
	}
}
