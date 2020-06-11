package com.example.sweetmilk.login;

import com.google.gson.annotations.SerializedName;

public class Stuff {
    @SerializedName("response")
    private String Response;
    @SerializedName("name")
    private String id;

    public String getResponseS() {
        return Response;
    }

    public String getId() {
        return id;
    }
}
