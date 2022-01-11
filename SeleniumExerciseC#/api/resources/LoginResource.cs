using API;
using config;
using RestSharp;
using RestSharp.Authenticators;
using System.Text.Json;

namespace resources
{
    public class LoginResource
    {
        /**
         * Log in as the specified user.
         * <p>
         * POST /login
         */
        public static APIToken login(APIUser user)
        {
            return login(user.getUsername(), user.getPassword());
        }

        /**
         * Log in as the user with the specified username and password.
         * <p>
         * POST /login
         */
        public static APIToken login(String username, String password)
        {
            LoginDTO loginDTO = loginInner(username, password);
            return new APIToken(loginDTO.apiUser, loginDTO.apiKey);
        }

        private static LoginDTO loginInner(String username, String password)
        {
            RestClient client = new RestClient("https://dev.vena.io/login");
            RestRequest request = new RestRequest();
            client.Authenticator = new HttpBasicAuthenticator(username, password);
            Task<RestResponse> task = client.ExecuteAsync(request, Method.Post);
            return JsonSerializer.Deserialize<LoginDTO>(task.Result.Content);
        }
    }
}