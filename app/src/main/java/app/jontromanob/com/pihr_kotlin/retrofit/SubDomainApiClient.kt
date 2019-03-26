package app.jontromanob.com.pihr_kotlin.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SubDomainApiClient {


    companion object {

        val DEMO = "demo"
        val BASE_URL_LIVE = "http://live.pihr.xyz/"   // Live Server_1
        val BASE_URL_DEMO = "http://demo.pihr.xyz/"   // Demo Server_1
        val BASE_URL_LOCAL = "http://61.247.185.122:85/"   // Demo Server_1
        var retrofit: Retrofit? = null


         fun getClient(subDomain: String) : Retrofit {


            if (retrofit == null) {

                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY

                val httpClient = OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(600, TimeUnit.SECONDS)

                // add your other interceptors …

                // add logging as last interceptor
               /* httpClient.addInterceptor(logging)
                    .addNetworkInterceptor(StethoInterceptor()) */ // <-

                val gson = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .setLenient()
                    .create()
                if (subDomain.equals(DEMO, ignoreCase = true)) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL_DEMO)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(httpClient.build())
                        .build()
                } else {
                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL_LIVE)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(httpClient.build())
                        .build()
                }
            }
            return retrofit!!
        }


    }
}