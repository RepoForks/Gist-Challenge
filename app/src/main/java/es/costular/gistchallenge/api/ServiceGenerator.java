package es.costular.gistchallenge.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by costular on 26/10/16.
 */

public class ServiceGenerator {

    public static final String API_BASE_URL = "https://api.github.com";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit() {
        return builder.build();
    }

    public static <S> S createService(Class<S> serviceClass) {
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

}
