package app.jontromanob.com.pihr_kotlin.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import app.jontromanob.com.pihr_kotlin.R
import app.jontromanob.com.pihr_kotlin.retrofit.SubDomainApiClient
import app.jontromanob.com.pihr_kotlin.retrofit.subdomain.call.SubDomainCheck
import app.jontromanob.com.pihr_kotlin.retrofit.subdomain.model.CompanyInformation
import app.jontromanob.com.pihr_kotlin.util.CompanyInformationUtil
import app.jontromanob.com.pihr_kotlin.util.CustomSnackbar
import app.jontromanob.com.pihr_kotlin.util.CustomSnackbar.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var animShow: Animation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAnimation()
        setSubmitBtnClicklistener()
    }


    private fun initAnimation() {
        animShow = AnimationUtils.loadAnimation(this@MainActivity, R.anim.view_show)
        animShow?.duration = 500
        linearSubDomain.startAnimation(animShow)

    }

    private fun setSubmitBtnClicklistener(){

        submit_btn.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {


                getInput()
                callSubdomainApi()

            }
        })
    }

    private fun getInput(){

        if (subDomainName.text.toString().trim { it <= ' ' }.isEmpty()) {
            CustomSnackbar.showMessage(this, getView(), getString(R.string.provide_sub_domain))
            return
        }

        if ((!checkInternetConnection())) {
            CustomSnackbar.showMessage(this, getView(), getString(R.string.check_internet_connection))
            return
        }
    }

    private fun getView(): View {
        return (this@MainActivity
                .findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)
    }

    private fun checkInternetConnection() : Boolean {

        var connected = false
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        connected = networkInfo != null && networkInfo.detailedState == NetworkInfo.DetailedState.CONNECTED
        return connected
    }


    private fun callSubdomainApi(){
        SubDomainCheck.getCompanyInformation(subDomainName.text.toString(),object : SubDomainCheck.SubDomainCallback{
            override fun onSuccess(companyInformation: CompanyInformation?) {

                CompanyInformationUtil.saveCompanyInfo(this@MainActivity,companyInformation!!)

            }

            override fun onFailure() {

                SubDomainApiClient.retrofit = null
                CustomSnackbar.showMessage(this@MainActivity, getView(), "No Company found under this Sub Domain")

            }

            override fun onServerFailure() {

                SubDomainApiClient.retrofit = null
                CustomSnackbar.showMessage(this@MainActivity, getView(), "Server is currently down! Please try again later.")
            }

            override fun onNotFound() {

                SubDomainApiClient.retrofit = null
                CustomSnackbar.showMessage(this@MainActivity, getView(), "Server Error! Please try again later.");
            }

        })
    }


}

