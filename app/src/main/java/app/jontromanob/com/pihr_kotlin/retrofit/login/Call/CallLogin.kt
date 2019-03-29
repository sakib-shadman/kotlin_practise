package app.jontromanob.com.pihr_kotlin.retrofit.login.Call

import app.jontromanob.com.pihr_kotlin.retrofit.ApiClient
import app.jontromanob.com.pihr_kotlin.retrofit.login.LoginApiInterface
import app.jontromanob.com.pihr_kotlin.retrofit.login.Model.LogInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * Created by Hi on 3/27/2019.
 */
class CallLogin {

    companion object {

        fun call(username: String, password: String, grant_type: String, companyId: Int, callback: LogInCallBack) {

            val apiInterface = ApiClient.getClient().create(LoginApiInterface::class.java)
            val call = apiInterface.postLoginInfo(username, password, grant_type, companyId)
            call.enqueue(object : Callback<LogInResponse> {

                override fun onFailure(call: Call<LogInResponse>, t: Throwable) {
                    callback.onLogInFailure()
                }

                override fun onResponse(call: Call<LogInResponse>, response: Response<LogInResponse>) {

                    if (HttpURLConnection.HTTP_NOT_FOUND == response.code()) {
                        callback.onServerFailure()
                    } else if (HttpURLConnection.HTTP_INTERNAL_ERROR == response.code()) {
                        callback.onServerFailure()
                    } else if (response.body() != null && HttpURLConnection.HTTP_OK == response.code()) {
                        if (response.body() != null) {

                            callback.onLogInSuccess(response.body())

                        }

                    }

                }
            })

        }
    }

    interface LogInCallBack {
        fun onLogInSuccess(logInResponse: LogInResponse?)

        fun onLogInFailure()

        fun onServerFailure()
    }
}