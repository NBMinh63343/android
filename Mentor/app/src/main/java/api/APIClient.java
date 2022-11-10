package api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final String baseURL = "http://54.179.200.182/api/abp/";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseURL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
