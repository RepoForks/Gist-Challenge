package es.costular.gistchallenge.public_gists;

import android.content.Context;

import java.util.List;

import es.costular.gistchallenge.api.GistService;
import es.costular.gistchallenge.api.ServiceGenerator;
import es.costular.gistchallenge.public_gists.datamodel.Gist;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by costular on 26/10/16.
 */

public class PublicGistsPresenter implements PublicGistsContract.UserActionListner {

    public PublicGistsContract.MyView view;
    public Context context;

    public PublicGistsPresenter(PublicGistsContract.MyView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void load() {
        view.showLoading(true);
        ServiceGenerator.createService(GistService.class)
                .getGists()
                .enqueue(new Callback<List<Gist>>() {
                    @Override
                    public void onResponse(Call<List<Gist>> call, Response<List<Gist>> response) {
                        // We could check if the user have internet connection
                        if (response.isSuccessful()) {
                            view.showList(response.body());
                            view.showLoading(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Gist>> call, Throwable t) {
                        // We should catch the failure
                    }
                });
    }

    @Override
    public void onGistClicked(Gist gist) {
        view.gistClicked(gist);
    }
}
