package app.jontromanob.com.pihr_kotlin.retrofit

import android.support.v4.content.LocalBroadcastManager
import app.jontromanob.com.pihr_kotlin.util.CompanyInformationUtil
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {

        var BASE_URL_PREFIX = "http://"// Live Server_1
        var BASE_URL_Extension = ".pihr.xyz/"   // Live Server_1

        val BASE_URL_LOCAL = "http://61.247.185.122:85/"


        var retrofit: Retrofit? = null

        fun getClient(): Retrofit {
            var url: String? = ""
            if (retrofit == null) {

                url = getCompleteUrl()

                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY

                val httpClient = OkHttpClient.Builder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(600, TimeUnit.SECONDS)

                // add your other interceptors …

                // add logging as last interceptor
              /*  httpClient.addInterceptor(logging)
                        .addNetworkInterceptor(StethoInterceptor())  // <-*/

                /*httpClient.addInterceptor { chain ->
                    val request = chain.request()
                    val response = chain.proceed(request)
                    if (response.code() == 401 || response.code() == 404) {
                        if (HomeActivity.getContext() != null) {
                            val intent = Intent(HomeActivity.RESPONSE_INTENT)
                            LocalBroadcastManager.getInstance(HomeActivity.getContext()).sendBroadcast(intent)
                        }
                    }
                    response
                }*/

                val gson = GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        .setLenient()
                        .create()
                retrofit = Retrofit.Builder()
                        .baseUrl(url!!)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(httpClient.build())
                        .build()

            }
            return retrofit!!
        }

        fun getCompleteUrl(): String? {
            return if (CompanyInformationUtil != null) {

                BASE_URL_PREFIX + "viva" + BASE_URL_Extension;

            }
            else ""
        }
    }
}