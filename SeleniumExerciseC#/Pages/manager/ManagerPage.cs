using OpenQA.Selenium;
using SeleniumExtras.PageObjects;

namespace Pages
{
    class ManagerPage : BasePage
    {
        [FindsBy(How = How.XPath, Using = "//button/span[text()='Create']")]
        private IWebElement createButton;

        [FindsBy(How = How.XPath, Using = "//li[text()='Folder']")]
        private IWebElement folderOption;

        [FindsBy(How = How.XPath, Using = "//li[text()='Process']")]
        private IWebElement processOption;

        [FindsBy(How = How.XPath, Using = "//div[@aria-label='Select Folder']/div[1]")]
        private IList<IWebElement> foldersList;

        [FindsBy(How = How.XPath, Using = "//div[contains(@class,'ProcessListInnerContainer')]/div/div[contains(@class,'ProcessListRowItem')]")]
        private IList<IWebElement> processesList;

        public ManagerPage(IWebDriver driver) : base(driver) { }

        public ManagerPage clickCreateButton()
        {
            createButton.Click();
            return new ManagerPage(driver);
        }

        public CreateModalPage selectFolderOption()
        {
            folderOption.Click();
            return new CreateModalPage(driver);
        }

        public CreateModalPage selectProcessOption()
        {
            processOption.Click();
            return new CreateModalPage(driver);
        }

        public List<String> getFoldersList()
        {
            List<String> folderNames = new List<string>();
            foreach (IWebElement folder in foldersList)
            {
                folderNames.Add(folder.Text);
            }
            return folderNames;
        }

        public ManagerPage clickFolder(String folderName)
        {
            foreach (IWebElement folder in foldersList)
            {
                if (folder.Text.Equals(folderName))
                {
                    folder.Click();
                    break;
                }
            }
            return new ManagerPage(driver);
        }

        public ProcessPage clickProcess(String processName)
        {
            foreach (IWebElement process in processesList)
            {
                if (process.Text.Equals(processName))
                {
                    process.Click();
                    break;
                }
            }
            return new ProcessPage(driver);
        }
    }
}