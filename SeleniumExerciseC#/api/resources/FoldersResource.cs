using API;
using config;
using RestSharp;
using System.Text.Json;

namespace resources
{
    public class FoldersResource
    {
        public static FolderDTO[] list(IAuth auth, Boolean includeHidden = false)
        {
            RestClient client = new RestClient("https://dev.vena.io/api/manager/folders");
            RestRequest request = new RestRequest();
            request.AddHeader("Authorization", AuthorizationHeaders.venaBasic(auth));
            Task<RestResponse> task = client.GetAsync(request);
            task.Wait(TimeSpan.FromSeconds(5));
            return JsonSerializer.Deserialize<FolderDTO[]>(task.Result.Content);
        }

        public static void delete(IAuth auth, string folderId)
        {
            RestClient client = new RestClient("https://dev.vena.io/api/manager/folders/{folderId}");
            RestRequest request = new RestRequest();
            request.AddHeader("Authorization", AuthorizationHeaders.venaBasic(auth));
            request.AddParameter("folderId", folderId, ParameterType.UrlSegment);
            Task<RestResponse> task = client.DeleteAsync(request);
            task.Wait(TimeSpan.FromSeconds(5));
        }
    }
}