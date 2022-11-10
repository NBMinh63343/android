package api;




import model.Slots;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SlotsService {
    String SLOTS = "slots";    // "slots" is table name in API

    @GET(SLOTS)
    Call<Slots[]> getAllSlots();

    @GET(SLOTS + "/{id}")
    Call<Slots> getSlotById(@Path("id") Object id);

    @POST(SLOTS)
    Call<Slots> createSlot(@Body Slots slots);

    @PUT(SLOTS + "/{id}")
    Call<Slots> updateSlots(@Path("id") Object id, @Body Slots slots);

    @DELETE(SLOTS + "/{id}")
    Call<Slots> deleteSlotrById(@Path("id") Object id);
}
