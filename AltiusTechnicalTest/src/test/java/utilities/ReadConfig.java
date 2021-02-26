package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

Properties prop;
	
	public ReadConfig() {
		File src = new File("./Configuration/config.properties");
		try {
			FileInputStream fInputStream = new FileInputStream(src);
			prop = new Properties();
			prop.load(fInputStream);
		} catch (Exception e) {
			System.out.println("Exception is : " + e.getMessage());
		}
	}
	
	public String getApplicationURL() {
		String url = prop.getProperty("baseURL");
		return url;
	}
	
	public String getChromePath() {
		String brPath = prop.getProperty("chromepath");
		return brPath;
	}
	
	public String getFirefoxPath() {
		String brPath = prop.getProperty("firefoxpath");
		return brPath;
	}
	
	public String getIEPath() {
		String brPath = prop.getProperty("iepath");
		return brPath;
	}
	
	public String getScreenshotPath() {
		String screenshotPath = prop.getProperty("screenshotPath");
		return screenshotPath;
	}
	
}
