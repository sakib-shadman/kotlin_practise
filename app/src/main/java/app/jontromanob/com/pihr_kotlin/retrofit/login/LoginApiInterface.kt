package app.jontromanob.com.pihr_kotlin.retrofit.login

import app.jontromanob.com.pihr_kotlin.retrofit.login.Model.LogInResponse
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Hi on 3/27/2019.
 */
interface LoginApiInterface {

    @FormUrlEncoded
    @POST("token")
    abstract fun postLoginInfo(

            @Field("username") username: String,
            @Field("password") password: String,
            @Field("grant_type") grant_type: String,
            @Field("companyId") companyId: Int

    ): Call<LogInResponse>
}