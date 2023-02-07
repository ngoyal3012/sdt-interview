using config;

namespace API
{
    public class AuthorizationHeaders
    {
        public static String venaBasic(IAuth auth)
        {
            APIToken token = auth.toAPIToken();
            return venaBasic(token.getUserId(), token.getCustomerId(), token.getToken());
        }
        public static String venaBasic(long userId, long customerId, String token)
        {
            return "VenaBasic " + Base64Encode(String.Format("{0}.{1}:{2}", userId, customerId, token));
        }
        public static String venaBasic(String username, String password)
        {
            return "VenaBasic " + Base64Encode((username + ":" + password));
        }
        private static string Base64Encode(string plainText)
        {
            var plainTextBytes = System.Text.Encoding.UTF8.GetBytes(plainText);
            return System.Convert.ToBase64String(plainTextBytes);
        }
    }
}