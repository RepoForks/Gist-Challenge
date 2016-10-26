package es.costular.gistchallenge.public_gists;

import android.os.Bundle;

import es.costular.gistchallenge.R;
import es.costular.gistchallenge.ui.activities.BaseActivity;


/**
 * Created by costular on 26/10/16.
 */

public class PublicGistsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar(false);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new PublicGistsFragment())
                .commit();
    }
}
