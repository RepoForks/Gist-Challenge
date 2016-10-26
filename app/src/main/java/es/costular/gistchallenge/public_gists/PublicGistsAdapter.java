package es.costular.gistchallenge.public_gists;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import es.costular.gistchallenge.R;
import es.costular.gistchallenge.public_gists.datamodel.GistFile;
import es.costular.gistchallenge.public_gists.datamodel.Gist;

/**
 * Created by costular on 26/10/16.
 */

public class PublicGistsAdapter extends RecyclerView.Adapter<PublicGistsAdapter.PublicGistsViewHolder>{

    public static final String TAG = "PublicGistsAdapter";

    public interface GistOnClickListener {
        void onGistClicked(Gist gist);
    }

    public List<Gist> gistList;
    private GistOnClickListener listener;
    private Context context;

    public PublicGistsAdapter(GistOnClickListener listener) {
        gistList = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public PublicGistsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewHolderView = inflater.inflate(R.layout.item_list_gist, parent, false);
        PublicGistsViewHolder viewHolder = new PublicGistsViewHolder(viewHolderView, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PublicGistsViewHolder holder, int position) {
        Gist currentGist = gistList.get(position);

        if (currentGist != null) {
            if (currentGist.owner != null) {
                holder.gistOwnerName.setText(currentGist.owner.username);

                Picasso.with(context)
                        .load(currentGist.owner.avatarURL)
                        .fit()
                        .centerCrop()
                        .into(holder.gistOwnerAvatar);
            }

            Map.Entry<String, GistFile> entry = currentGist.files.entrySet().iterator().next();
            String firstFileName = entry.getValue().filename;
            holder.gistFirstFilename.setText(firstFileName);
        }
    }

    @Override
    public int getItemCount() {
        return gistList.size();
    }

    public void swapAndUpdateAdapter(List<Gist> data) {
        gistList.clear();
        gistList.addAll(data);
        notifyDataSetChanged();
    }

    class PublicGistsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.gist_owner_name) TextView gistOwnerName;
        @BindView(R.id.gist_first_filename) TextView gistFirstFilename;
        @BindView(R.id.gist_owner_profile_image)
        CircleImageView gistOwnerAvatar;

        public PublicGistsViewHolder(View itemView, final GistOnClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onGistClicked(gistList.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
}
