package api;



import model.Users;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsersService {
    String USERS = "user";    // "users" is table name in API

    @GET(USERS)
    Call<Users> getAllUsers();

    @GET(USERS + "/{id}")
    Call<Users> getUserById(@Path("id") Object id);

    @POST(USERS)
    Call<Users> createUser(@Body Users users);

    @PUT(USERS + "/{id}")
    Call<Users> updateUser(@Path("id") Object id, @Body Users users);

    @DELETE(USERS + "/{id}")
    Call<Users> deleteUserById(@Path("id") Object id);
}
