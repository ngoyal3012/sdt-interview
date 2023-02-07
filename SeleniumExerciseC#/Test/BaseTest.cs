using Microsoft.VisualStudio.TestTools.UnitTesting;
using YamlDotNet.Serialization;
using resources;
using API;

namespace Test
{
    [TestClass]
    public class BaseTest
    {
        protected static CredentialsFileConstants credentials;

        [AssemblyInitialize]
        public static void beforeSuiteAPITesting(TestContext context)
        {
            var deserializer = new DeserializerBuilder().Build();
            credentials = deserializer.Deserialize<CredentialsFileConstants>(File.ReadAllText("./Test/resources/credentials.yml"));
        }

        private static APIToken getAdminToken()
        {
            return LoginResource.login(credentials.username, credentials.password);
        }

        protected static void deleteAllCustomFolders()
        {
            FolderDTO[] folders = FoldersResource.list(getAdminToken());
            Array.ForEach<FolderDTO>(folders, folder => FoldersResource.delete(getAdminToken(), folder.id));
        }
    }

    public class CredentialsFileConstants
    {
        public string username { get; set; }
        public string password { get; set; }
    }
}