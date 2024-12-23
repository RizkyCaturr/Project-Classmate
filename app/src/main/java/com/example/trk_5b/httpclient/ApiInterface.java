package com.example.trk_5b.httpclient;

import com.example.trk_5b.model.ResponseMahasiswa;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("mahasiswa/-OEeQeuOvLDBdjCZIeMR.json")
    Call<ResponseMahasiswa> getMahasiswa();
}

