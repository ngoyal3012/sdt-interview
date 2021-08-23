package applicationpages.manager;

import applicationpages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreateModalPage extends BasePage {
	@FindBy(how = How.CSS, using = "[data-testid='submit-Create']")
	private WebElement createButton;

	@FindBy(how = How.CSS, using = "[data-testid='submit-Cancel']")
	private WebElement cancelButton;

	@FindBy(how = How.CSS, using = "[data-testid='inputDialog-TextField'] div input")
	private WebElement nameTextBox;

	public CreateModalPage(WebDriver driver) {
		super(driver);
		waitForElementClickable(createButton);
	}

	public void setName(String folderName) {
		nameTextBox.click();
		nameTextBox.sendKeys(folderName);
	}

	public boolean isCreateButtonEnabled() {
		return !Boolean.parseBoolean(createButton.getAttribute("aria-disabled"));
	}

	public ManagerPage clickCreateButton() {
		createButton.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new ManagerPage(driver);
	}

	public ManagerPage clickCancelButton() {
		cancelButton.click();
		return new ManagerPage(driver);
	}
}
