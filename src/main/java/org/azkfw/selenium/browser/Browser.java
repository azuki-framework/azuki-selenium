package org.azkfw.selenium.browser;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;

public interface Browser {

	void initialize();

	void destory();

	void open(String url);

	void screenshot(File file) throws IOException;
	
	void click(By by);
	
	void switchWindowForTitle(String title);
	
	Rectangle getWindowRectangle();
}
