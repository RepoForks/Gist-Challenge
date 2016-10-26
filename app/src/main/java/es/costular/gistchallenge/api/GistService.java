package es.costular.gistchallenge.api;

import java.util.List;

import es.costular.gistchallenge.public_gists.datamodel.Gist;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by costular on 26/10/16.
 */

public interface GistService {

    @GET("/gists/public")
    Call<List<Gist>> getGists();

}
