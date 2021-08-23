package applicationpages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30, 500);
	}

	public void waitForElementClickable(WebElement element) {
		ExpectedCondition<Boolean> condition = (unused -> {
			try {
				if (element.isDisplayed() && element.getAttribute("aria-disabled").equals(false)) {
					return true;
				} else {
					return false;
				}
			} catch (ElementClickInterceptedException e) {
				return false;
			}
		});
		if (!wait.until(condition)) {
			throw new TimeoutException("Element is not clickable.");
		}
	}

}
