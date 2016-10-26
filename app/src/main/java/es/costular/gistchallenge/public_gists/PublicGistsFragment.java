package es.costular.gistchallenge.public_gists;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.costular.gistchallenge.R;
import es.costular.gistchallenge.public_gists.datamodel.Gist;
import es.costular.gistchallenge.ui.dialogs.InfoDialog;

/**
 * Created by costular on 26/10/16.
 */

public class PublicGistsFragment extends Fragment implements PublicGistsContract.MyView, PublicGistsAdapter.GistOnClickListener{

    @BindView(R.id.recyclerView_gists)
    RecyclerView recyclerViewGists;
    @BindView(R.id.loading_indicator) ProgressBar loadingIndicator;

    private PublicGistsPresenter presenter;
    private PublicGistsAdapter recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gist_list, parent, false);
        ButterKnife.bind(this, root);
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new PublicGistsPresenter(this, getActivity());
        setupRecyclerAdapter();
        setupRecyclerView();

        presenter.load();
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    private void setupRecyclerAdapter() {
        recyclerAdapter = new PublicGistsAdapter(this);
    }

    private void setupRecyclerView() {
        recyclerViewGists.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        recyclerViewGists.setItemAnimator(new DefaultItemAnimator());
        recyclerViewGists.setAdapter(recyclerAdapter);

        RecyclerView.ItemDecoration divider = new HorizontalDividerItemDecoration
                .Builder(getActivity())
                .marginResId(R.dimen.material_list_left_divider_margin, R.dimen.zero)
                .colorResId(R.color.dividerColor)
                .build();
        recyclerViewGists.addItemDecoration(divider);
    }

    @Override
    public void showLoading(boolean isLoading) {
        loadingIndicator.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showList(List<Gist> gistList) {
        recyclerAdapter.swapAndUpdateAdapter(gistList);
    }

    @Override
    public void gistClicked(Gist gist) {
        Intent openGistInBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(gist.gistURL));
        startActivity(openGistInBrowser);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main, menu);
    }

    private void refresh() {
        presenter.load();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_action_refresh:
                refresh();
                return true;
            case R.id.ic_info:
                InfoDialog.makeDialog(getActivity()).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onGistClicked(Gist gist){
        presenter.onGistClicked(gist);
    }
}
