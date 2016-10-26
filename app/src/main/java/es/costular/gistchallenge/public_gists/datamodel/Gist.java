package es.costular.gistchallenge.public_gists.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by costular on 25/10/16.
 */

public class Gist {

    @SerializedName("html_url")
    public String gistURL;

    public Map<String, GistFile> files;
    public Owner owner;

}
