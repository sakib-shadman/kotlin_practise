package app.jontromanob.com.pihr_kotlin.retrofit.subdomain

import app.jontromanob.com.pihr_kotlin.retrofit.subdomain.model.CompanyInformation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SubDomainInterface {


    @Headers("Content-Type: application/json")
    @GET("api/PIEERPApi/MatchSubDomain")
    fun getCompanyInformation(
        @Query("domainName") domainName: String
    ): Call<CompanyInformation>
}
