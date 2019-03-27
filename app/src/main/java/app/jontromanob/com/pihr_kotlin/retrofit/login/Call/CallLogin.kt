package app.jontromanob.com.pihr_kotlin.retrofit.login.Call

import app.jontromanob.com.pihr_kotlin.retrofit.SubDomainApiClient
import app.jontromanob.com.pihr_kotlin.retrofit.login.Model.LogInResponse
import app.jontromanob.com.pihr_kotlin.retrofit.subdomain.SubDomainInterface
import app.jontromanob.com.pihr_kotlin.retrofit.subdomain.call.SubDomainCheck
import app.jontromanob.com.pihr_kotlin.retrofit.subdomain.model.CompanyInformation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * Created by Hi on 3/27/2019.
 */
class CallLogin {

    companion object {

        fun call( username : String, password : String,grant_type : String,companyId : Int,callback: LogInCallBack){

            val apiInterface = SubDomainApiClient.getClient(domainName).create(SubDomainInterface::class.java)
            val call = apiInterface.getCompanyInformation(domainName)
            call.enqueue(object : Callback<CompanyInformation> {
                override fun onFailure(call: Call<CompanyInformation>, t: Throwable) {
                    callback.onFailure()
                }

                override fun onResponse(call: Call<CompanyInformation>, response: Response<CompanyInformation>) {
                    if(HttpURLConnection.HTTP_NOT_FOUND == response.code()){
                        callback.onNotFound()
                    }
                    else if(HttpURLConnection.HTTP_INTERNAL_ERROR == response.code()){
                        callback.onServerFailure()
                    }
                    else if(response.body() != null && HttpURLConnection.HTTP_OK == response.code()){
                        if (response.body()!!.companyId != null){

                            callback.onSuccess(response.body())

                        }

                    }

                }
            })

        }
    }

    interface LogInCallBack {
         fun onLogInSuccess(logInResponse: LogInResponse)

         fun onLogInFailure()

         fun onServerFailure()
    }
}