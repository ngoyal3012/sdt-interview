using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using WebDriverManager;
using WebDriverManager.DriverConfigs.Impl;
using Pages;

namespace Test
{
    [TestClass]
    public class InterviewTest : BaseTest
    {
        private static ManagerPage _managerPage { get; set; } = default!;
        private static IWebDriver _driver { get; set; } = default!;

        [ClassInitialize]
        static public void SetUp(TestContext context)
        {
            new DriverManager().SetUpDriver(new ChromeConfig());
            _driver = new ChromeDriver();
            _driver.Manage().Window.Maximize();
        }

        [TestMethod]
        public void test1_testLogin()
        {
            _driver.Navigate().GoToUrl("https://dev.vena.io");
            _driver.FindElement(By.Id("email")).SendKeys(credentials.username);
            _driver.FindElement(By.Id("password")).SendKeys(credentials.password);
            _driver.FindElement(By.CssSelector("[data-testid*='login-pw']")).Click();
            _driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(30);
            Assert.IsNotNull(_driver.FindElement(By.CssSelector("[name='manager']")));
            String userName = _driver.FindElement(By.Id("accountTab")).Text;
            Assert.AreEqual(userName, "Admin User");
        }

        [TestMethod]
        public void test2_testCreateFolder()
        {
            _managerPage = new ManagerPage(_driver);
            _managerPage.clickCreateButton();
            CreateModalPage createModalPage = _managerPage.selectFolderOption();
            createModalPage.setName("Test New Folder");
            _managerPage = createModalPage.clickCreateButton();
            Assert.IsTrue(_managerPage.getFoldersList().Contains("Test New Folder"));
        }

        [TestMethod]
        public void test3_testCreateProcessUnderFolder()
        {
            _managerPage = new ManagerPage(_driver);
            _managerPage.clickCreateButton();
            _managerPage.selectFolderOption();
            IWebElement nameTextBox = _driver.FindElement(By.CssSelector("[data-testid='inputDialog-TextField'] div input"));
            nameTextBox.SendKeys("Test New Folder 1");
            IWebElement createButtonOnModal = _driver.FindElement(By.CssSelector("[data-testid='submit-Create']"));
            createButtonOnModal.Click();
            Thread.Sleep(5000);
            _managerPage.clickFolder("Test New Folder 1");
            _managerPage.clickCreateButton();
            _managerPage.selectProcessOption();
            nameTextBox.SendKeys("Test New Process 1");
            createButtonOnModal.Click();
        }

        [TestMethod]
        public void test4_testCreateButtonDisabled()
        {
            _managerPage = new ManagerPage(_driver);
            _managerPage.clickCreateButton();
            CreateModalPage createModalPage = _managerPage.selectFolderOption();
            Assert.IsTrue(createModalPage.isCreateButtonEnabled());
        }

        [TestMethod]
        public void test5_testClickProcess()
        {
            _managerPage = new ManagerPage(_driver);
            _managerPage.clickProcess("Test New Process 1");
        }

        [ClassCleanup]
        public static void TearDown()
        {
            _driver.Quit();
            BaseTest.deleteAllCustomFolders();
        }
    }
}