package es.costular.gistchallenge.public_gists.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by costular on 26/10/16.
 */

public class Owner {

    @SerializedName("login")
    public String username;

    @SerializedName("avatar_url")
    public String avatarURL;

}
