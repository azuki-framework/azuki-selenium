package org.azkfw.selenium.browser;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasicBrowser implements Browser {

	/** Logger */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BasicBrowser.class);

	/** WebDriver */
	private WebDriver webDriver;

	protected abstract WebDriver createWebDriver();

	@Override
	public final void initialize() {

		doInitialize();
	}

	@Override
	public final void destory() {
		doDestory();

		if (null != webDriver) {
			webDriver.quit();
		}
		webDriver = null;
	}

	@Override
	public void open(final String url) {
		if (null == webDriver) {
			webDriver = createWebDriver();
		}

		webDriver.get(url);
	}

	@Override
	public void screenshot(final File file) throws IOException {
		LOGGER.trace("screenshot : {}", file.getAbsoluteFile());
		
		final File src = ((TakesScreenshot) webDriver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, file);
	}

	protected abstract void doInitialize();

	protected abstract void doDestory();

	protected final WebDriver getWebDriver() {
		return webDriver;
	}
}
