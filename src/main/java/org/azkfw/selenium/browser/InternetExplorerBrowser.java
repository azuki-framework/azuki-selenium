package org.azkfw.selenium.browser;

import java.awt.Rectangle;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InternetExplorerBrowser extends BasicBrowser {

	/** Logger */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(InternetExplorerBrowser.class);

	/** WebDriver */
	private WebDriver webDriver;

	protected WebDriver createWebDriver() {
		System.setProperty("webdriver.ie.driver",
				"src\\main\\WebDriver\\InternetExplorer\\win\\x86\\IEDriverServer.exe");

		System.setProperty("webdriver.ie.silentOutput", "true");
		System.setProperty("webdriver.ie.logFile", "nul");

		final DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
		capability.setVersion("6");
		webDriver = new InternetExplorerDriver(capability);

		return webDriver;
	}

	@Override
	public void doInitialize() {

	}

	@Override
	public void doDestory() {
	}

	@Override
	public void click(final By by) {
		LOGGER.trace("click : {}", by);

		Action action = new Actions(webDriver)
				.moveToElement(webDriver.findElement(by)).click().build();
		action.perform();
	}

	@Override
	public void switchWindowForTitle(String title) {

		final String current = webDriver.getWindowHandle();

		final Set<String> handles = webDriver.getWindowHandles();

		boolean match = false;
		for (String handle : handles) {
			webDriver.switchTo().window(handle);

			String targetTitle = webDriver.getTitle();
			if (targetTitle.equals(title)) {
				match = true;
				break;
			}
		}

		if (!match) {
			webDriver.switchTo().window(current);
		}
	}

	public Rectangle getWindowRectangle() {
		Rectangle rect = new Rectangle();

		final Window window = webDriver.manage().window();

		rect.setLocation(window.getPosition().getX(), window.getPosition()
				.getY());
		rect.setSize(window.getSize().getWidth(), window.getSize().getHeight());

		return rect;
	}
}
