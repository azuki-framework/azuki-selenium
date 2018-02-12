package org.azkfw.selenium.browser;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class InternetExplorerBrowserTest {

	private InternetExplorerBrowser browser;

	@Before
	public void setUp() {
		browser = new InternetExplorerBrowser();
		browser.initialize();
	}

	@After
	public void tearDown() {
		browser.destory();
	}

	@Test
	public void test() throws Exception {
		browser.open("http://localhost:8080");

		browser.screenshot(new File("test01.png"));

		Thread.sleep(1000);
		
		browser.click(By.id("modalDialog"));

		Thread.sleep(1000);

		browser.screenshot(new File("test02.png"));

		browser.click(By.id("button"));

		Thread.sleep(1000);
		
		browser.switchWindowForTitle("Child page");

		browser.screenshot(new File("test03.png"));

		System.setProperty("java.awt.headless", "false");
		Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(browser.getWindowRectangle());
        ImageIO.write(image, "PNG", new File("test04.png"));
		
		Thread.sleep(1000);
	}
}
