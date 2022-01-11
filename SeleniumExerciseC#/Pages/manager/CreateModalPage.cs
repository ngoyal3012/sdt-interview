using OpenQA.Selenium;
using SeleniumExtras.PageObjects;

namespace Pages
{
    class CreateModalPage : BasePage
    {
        [FindsBy(How = How.CssSelector, Using = "[data-testid='submit-Create']")]
        private IWebElement createButton;

        [FindsBy(How = How.CssSelector, Using = "[data-testid='submit-Cancel']")]
        private IWebElement cancelButton;

        [FindsBy(How = How.CssSelector, Using = "[data-testid='inputDialog-TextField'] div input")]
        private IWebElement nameTextBox;

        public CreateModalPage(IWebDriver driver) : base(driver)
        {
            waitForElementClickable(createButton);
        }
        public void setName(String folderName)
        {
            nameTextBox.Click();
            nameTextBox.SendKeys(folderName);
        }

        public bool isCreateButtonEnabled()
        {
            return !bool.Parse(createButton.GetAttribute("aria-disabled"));
        }

        public ManagerPage clickCreateButton()
        {
            createButton.Click();
            try
            {
                Thread.Sleep(5000);
            }
            catch (ArgumentOutOfRangeException e)
            {
                Console.WriteLine(e.ToString());
            }
            return new ManagerPage(driver);
        }

        public ManagerPage clickCancelButton()
        {
            cancelButton.Click();
            return new ManagerPage(driver);
        }
    }
}