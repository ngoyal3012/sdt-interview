using config;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace API
{
    public class APIToken : IAuth
    {
        private readonly long customerId;
        private readonly long userId;
        private readonly String token;
        public APIToken(String apiUser, String apiKey)
        {
            if (apiUser == null)
            {
                throw new ArgumentException("apiUser must not be null");
            }
            if (apiKey == null)
            {
                throw new ArgumentException("apiKey must not be null");
            }
            String[] userIdCustomerIdSplit = apiUser.Split(".");
            Assert.IsTrue(userIdCustomerIdSplit.Length == 2);
            this.customerId = Convert.ToInt64(userIdCustomerIdSplit[1]);
            this.userId = Convert.ToInt64(userIdCustomerIdSplit[0]);
            this.token = apiKey;
        }
        public APIToken toAPIToken()
        {
            return this;
        }
        public long getUserId()
        {
            return this.userId;
        }
        public long getCustomerId()
        {
            return this.customerId;
        }
        public String getToken()
        {
            return this.token;
        }
    }
}
