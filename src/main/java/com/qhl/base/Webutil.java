package com.qhl.base;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class Webutil {

	public static WebDriver wdriver;

	public static WebDriver launch_browser(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", ".\\Exe\\91.exe");
			wdriver = new ChromeDriver();
			return wdriver;
		} else if (browser.equalsIgnoreCase("firefox")) {

			System.out.println("sorry such browser not available in your machine");

		}
		return null;
	}

	public static String getScreenshot(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/Screenshot/" + System.currentTimeMillis() + ".png";

		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}

		return path;
	}
	
	/*method for click an webelement*/
	public static void click(WebElement ele)
	{
		ele.click();
	}
	/*method for send keys*/
	public static void sendkeys(WebElement ele, String txt)
	{
		ele.sendKeys(txt);
	}
	/*method for open URL*/
	public static void open_url(String URL)
	{
		wdriver.get(URL);
	}
	/*method for refresh web page */
	public static void refreshpage()
	{
		wdriver.navigate().refresh();
	}
	/* method for switch window or tab*/
	public static void switchWindowFocusByTitle(String title) {
		try {
			Set<String> handleValues = wdriver.getWindowHandles();
			for (String handleValue : handleValues) {
				wdriver.switchTo().window(handleValue);
				if (wdriver.getTitle().trim().equalsIgnoreCase(title)) {
					break;
				}
			}
		} catch (Exception e) {
			// logger.debug( Constants.ELEMENT_SEARCH_ERROR_MESSAGE, e);
		}
	}
	/*method for close window */
	public static void close_window()
	{
		wdriver.close();
	}

	/*method for drop down handling by value*/
	public static void selectbyvalue(WebElement ele, String value)
	{
		Select sc=new Select(ele);
		sc.selectByValue(value);

	}
	/*method for get URL */
	public static String getcurrentUrl()
	{
		String url = wdriver.getCurrentUrl();
		return url;
	}
	/*method for mouse over*/
	public static void mouseover(WebElement element  )
	{
		Actions action = new Actions(wdriver);
		action.moveToElement(element).build().perform();
	}
	/*method for check the visibility of text*/
	public static void selectbyvisbletext(WebElement element ,String text)
	{
		Select selctobj = new Select(element);
		selctobj.selectByVisibleText(text);
	}
	/*method for drop down handling by index value*/
	public static void selectbyindexing(WebElement element ,String indexing){
		Select selctobj=new Select(element);
		selctobj.selectByValue(indexing);
	}
	/*method for scroll down web page*/
	public static void jsScroll(){
		JavascriptExecutor jse = (JavascriptExecutor)wdriver;
		jse.executeScript("window.scrollBy(0,1000)");
	}
	/*method for click on enter on web element */
	public static void keys(WebElement element){
		element.sendKeys(Keys.ENTER);
	}
	/*method for scroll web page till web element*/
	public static void jsscrolldown(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) wdriver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	/*method for action scroll*/
	public static void ActionScroll(){
		Actions actions = new Actions(wdriver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).build().perform();
	}
	/*method for handle frame by web element*/
	public static void HandleIframe(WebElement element){
		wdriver.switchTo().frame(element);
	}
	/*method for handle frame by frame num*/
	public static void HandleIframe(int num){
		wdriver.switchTo().frame(num);
	}
	/*method for clear field  */
	public static void clear(WebElement element){
		element.clear();
	}

	/*method for wait till element visible*/
	public static boolean waitUntilElementVisible(WebElement we, int timeOut, String ElementName) {        
		int timeTaken=0;
		boolean flag=false;
		for(int i=1; i<=timeOut;i++){
			try{
				if(we.getSize().getHeight()>0 || we.getSize().getWidth()>0){
					wdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					flag=true;
					break;
				}
			}catch(Exception e){
				timeTaken=timeTaken+1;
			}


		}
		wdriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		if(!flag){
			//Testscript.logger.log(Status.WARNING, MarkupHelper.createLabel(ElementName+" searched until "+"120"+" seconds but not found the element", ExtentColor.BLUE));        


			return false;
		}else{
			return true;
		}
	}

	/*method for get text of web element*/
	public static String gettext(WebElement element)
	{
		String text = element.getText();
		return text;
	}  

	/*method for js click*/
	public static void jsClick( WebElement element){
		JavascriptExecutor executor = (JavascriptExecutor)wdriver;
		executor.executeScript("arguments[0].click();", element);
	}
	/*method for get local time */
	 public static LocalTime get_current_time()
     {
             LocalTime time = java.time.LocalTime.now();          
             return time;
     }
	 /*method for get current date*/
	 public static LocalDate get_current_Date()
     {
             LocalDate date = java.time.LocalDate.now();
             return date;
     }
	
	
}
