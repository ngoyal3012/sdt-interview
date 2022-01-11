using SeleniumExtras.PageObjects;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;

namespace Pages
{
    class BasePage
    {
        protected IWebDriver driver;
        protected WebDriverWait wait;

        public BasePage(IWebDriver driver)
        {
            this.driver = driver;
            PageFactory.InitElements(driver, this);
            wait = new WebDriverWait(driver, TimeSpan.FromSeconds(30));
        }

        public void waitForElementClickable(IWebElement element)
        {
            Func<IWebDriver, Boolean> condition = (driver) =>
            {
                try
                {
                    if (element.Displayed && element.GetAttribute("aria-disabled").Equals(false))
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                catch (ElementClickInterceptedException)
                {
                    return false;
                }
            };

            if (wait.Until<Boolean>(condition))
            {
                throw new TimeoutException("Element is not clickable");
            }
        }
    }
}

