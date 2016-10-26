package es.costular.gistchallenge.public_gists;

import java.util.List;

import es.costular.gistchallenge.public_gists.datamodel.Gist;


/**
 * Created by costular on 26/10/16.
 */

public interface PublicGistsContract {

    public interface MyView {

        void showLoading(boolean isLoading);

        void showList(List<Gist> gistList);

        void gistClicked(Gist gist);
    }

    public interface UserActionListner {

        void load();

        void onGistClicked(Gist gist);
    }
}
