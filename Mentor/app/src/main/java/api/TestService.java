package api;

import model.Slots;
import model.TestApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TestService {
    String SLOTS = "api-definition";    // "slots" is table name in API

    @GET(SLOTS)
    Call<TestApi> getAllSlots();

    @GET(SLOTS + "/{id}")
    Call<TestApi> getSlotById(@Path("id") Object id);

    @POST(SLOTS)
    Call<TestApi> createSlot(@Body Slots slots);

    @PUT(SLOTS + "/{id}")
    Call<TestApi> updateSlots(@Path("id") Object id, @Body Slots slots);

    @DELETE(SLOTS + "/{id}")
    Call<TestApi> deleteSlotrById(@Path("id") Object id);
}
