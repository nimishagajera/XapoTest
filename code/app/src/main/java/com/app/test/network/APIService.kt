package com.app.test.network


import com.app.test.model.FollowersResponse
import com.app.test.model.RepositoryModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import rx.Observable
/*
* APIservice interface - A list of APIs
* */

interface APIService {

    /*
    *  BASE_URL to be used in network module for API call
    * */
    companion object {
        val BASE_URL = "https://api.github.com/"
    }

    /*
    * GET API for repository list
    * */
    @GET("repositories")
    fun getRepositories(@Query("since") since:Int):Observable<ArrayList<RepositoryModel>>

    /*
    * GET API for followers list
    * */
    @GET()
    fun getFollowersList(@Url followUrl: String):Observable<ArrayList<FollowersResponse>>

    /*
    * GET API for following list
    * */
    @GET()
    fun getFollowingList(@Url followUrl: String):Observable<ArrayList<FollowersResponse>>


}