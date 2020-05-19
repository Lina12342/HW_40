package core;

import java.util.logging.*;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

public class HtmlUnit {
	public static void main(String[] cla) throws Exception {
		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
		String url = "http://alex.academy/exe/signup/www";
		WebClient driver = new WebClient();
		final long bm_start = System.currentTimeMillis();
		HtmlPage page = driver.getPage(url);
		System.out.println("Page URI: " + page.getUrl());
		System.out.println("Page Title: " + page.getTitleText());
		HtmlElement os_browser = page.getHtmlElementById("os_browser");
		System.out.println("OS & Browser: " + os_browser.asText());
		final long bm_finish = System.currentTimeMillis();
		driver.close();
		System.out.println("Response time: " + (bm_finish - bm_start) / 1000 + " seconds");
	}
}