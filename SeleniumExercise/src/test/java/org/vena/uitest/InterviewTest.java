package org.vena.uitest;

import applicationpages.manager.CreateModalPage;
import applicationpages.manager.ManagerPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.vena.BaseTest;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

public class InterviewTest extends BaseTest {
	private WebDriver driver;

	private static ManagerPage managerPage;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1920, 1080));
	}

	@Test(priority = 1)
	public void testLogin() {
		driver.get("https://dev.vena.io");
		driver.findElement(By.id("email")).sendKeys(credentials.getUsername());
		driver.findElement(By.id("password")).sendKeys(credentials.getPassword());
		driver.findElement(By.cssSelector("[data-testid*='login-pw']")).click();
		driver.manage().timeouts().implicitlyWait(30L, TimeUnit.MILLISECONDS);
		assertNotNull(driver.findElement(By.cssSelector("[name='manager']")));
		String userName = driver.findElement(By.id("accountTab")).getText();
		assertEquals(userName, "Admin User");
	}

	@Test(priority = 2)
	public void testCreateFolder() {
		managerPage = new ManagerPage(driver);
		managerPage.clickCreateButton();
		CreateModalPage createModalPage = managerPage.selectFolderOption();
		createModalPage.setName("Test New Folder");
		managerPage = createModalPage.clickCreateButton();
		assertTrue(managerPage.getFoldersList().contains("Test New Folder"));
	}

	@Test(priority = 3)
	public void testCreateProcessUnderFolder() throws InterruptedException {
		managerPage = new ManagerPage(driver);
		managerPage.clickCreateButton();
		managerPage.selectFolderOption();
		WebElement nameTextBox = driver.findElement(By.cssSelector("[data-testid='inputDialog-TextField'] div input"));
		nameTextBox.sendKeys("Test New Folder 1");
		WebElement createButtonOnModal = driver.findElement(By.cssSelector("[data-testid='submit-Create']"));
		createButtonOnModal.click();
		Thread.sleep(5000);
		managerPage.clickFolder("Test New Folder 1");
		managerPage.clickCreateButton();
		managerPage.selectProcessOption();
		nameTextBox.sendKeys("Test New Process 1");
		createButtonOnModal.click();
	}

	@Test(priority = 4)
	public void testCreateButtonDisabled() {
		managerPage = new ManagerPage(driver);
		managerPage.clickCreateButton();
		CreateModalPage createModalPage = managerPage.selectFolderOption();
		assertTrue(createModalPage.isCreateButtonEnabled());
	}

	@Test(priority = 5)
	public void testClickProcess() {
		managerPage = new ManagerPage(driver);
		managerPage.clickProcess("Test New Process 1");
	}

	@AfterClass (alwaysRun = true)
	public void teardown() {
		driver.quit();
		deleteAllCustomFolders();
	}
}
