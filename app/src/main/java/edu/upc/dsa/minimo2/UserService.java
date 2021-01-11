package edu.upc.dsa.minimo2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
@GET("{user}/followers")
    Call<List<Usuario>> getInfo(@Path("user")String name);
@GET("{user}")
    Call<Usuario> getUser(@Path("user")String name);

@GET("{user}/repos")
Call<List<Repos>> getRespos(@Path("user")String name);

@GET("{user}/repos/{repo}/languages")
Call<Lenguage> getLenguage(@Path("user")String name,@Path("repo")String repo);

}
