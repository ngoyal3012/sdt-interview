using API;

namespace config
{
    public interface IAuth
    {
        APIToken toAPIToken();
    }
}