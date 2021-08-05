package applicationpages.manager;

import applicationpages.BasePage;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ManagerPage extends BasePage {
	@FindBy(how = How.XPATH, using = "//button/span[text()='Create']")
	private WebElement createButton;

	@FindBy(how = How.XPATH, using = "//li[text()='Folder']")
	private WebElement folderOption;

	@FindBy(how = How.XPATH, using = "//li[text()='Process']")
	private WebElement processOption;

	@FindBy(how = How.XPATH, using = "//div[@aria-label='Select Folder']/div[1]")
	private List<WebElement> foldersList;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'ProcessListInnerContainer')]/div/div[contains(@class,'ProcessListRowItem')]")
	private List<WebElement> processesList;

	public ManagerPage(WebDriver driver) {
		super(driver);
	}

	public ManagerPage clickCreateButton() {
		createButton.click();
		return new ManagerPage(driver);
	}

	public CreateModalPage selectFolderOption() {
		folderOption.click();
		return new CreateModalPage(driver);
	}

	public CreateModalPage selectProcessOption() {
		processOption.click();
		return new CreateModalPage(driver);
	}

	public List<String> getFoldersList() {
		List<String> folderNames = new ArrayList<>();
		for (WebElement folder : foldersList) {
			folderNames.add(folder.getText());
		}
		return folderNames;
	}

	public ManagerPage clickFolder(String folderName) {
		for (WebElement folder : foldersList) {
			if (folder.getText().equals(folderName)) {
				folder.click();
				break;
			}
		}
		return new ManagerPage(driver);
	}


	public ProcessPage clickProcess(String processName){
		for (WebElement process : processesList) {
			if (process.getText().equals(processName)) {
				process.click();
				break;
			}
		}
		return new ProcessPage(driver);
	}
}
