package app.jontromanob.com.pihr_kotlin.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.jontromanob.com.pihr_kotlin.R
import app.jontromanob.com.pihr_kotlin.retrofit.SubDomainApiClient
import app.jontromanob.com.pihr_kotlin.util.CompanyInformationUtil
import app.jontromanob.com.pihr_kotlin.util.SaveUserInformationUtil
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        changeSubDomain.visibility = View.INVISIBLE
        YoYo.with(Techniques.BounceInUp).duration(1000).playOn(findViewById(R.id.login_icon))
        showCompanyInfo()
        onClickSettings()
        onClickChangeSubdomain()

    }

    fun showCompanyInfo(){

        val companyInformation = CompanyInformationUtil.getCompanyInfo(applicationContext)
        if(companyInformation?.imagePath != null) {
             try {
                 Picasso.with(companyLogo.context).load(companyInformation.imagePath).fit().error(R.drawable.avatar).into(companyLogo)
             } catch (ex:Exception){

             }
        }
    }

    fun onClickSettings(){

        settings.setOnClickListener {
            if(changeSubDomain.visibility == View.VISIBLE){
                changeSubDomain.visibility = View.INVISIBLE
            } else if(changeSubDomain.visibility == View.INVISIBLE){
                changeSubDomain.visibility = View.VISIBLE
            }
        }

    }

    fun onClickChangeSubdomain(){

        changeSubDomain.setOnClickListener {

            CompanyInformationUtil.clearCompanyInfo(this@LoginActivity)
            SaveUserInformationUtil.clearUserInfo(this@LoginActivity)
            SubDomainApiClient.retrofit = null
            val myIntent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }






}
