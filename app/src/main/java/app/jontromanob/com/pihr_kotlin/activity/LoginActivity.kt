package app.jontromanob.com.pihr_kotlin.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import app.jontromanob.com.pihr_kotlin.R
import app.jontromanob.com.pihr_kotlin.retrofit.SubDomainApiClient
import app.jontromanob.com.pihr_kotlin.retrofit.login.Call.CallLogin
import app.jontromanob.com.pihr_kotlin.retrofit.login.Model.LogInResponse
import app.jontromanob.com.pihr_kotlin.util.CompanyInformationUtil
import app.jontromanob.com.pihr_kotlin.util.CustomSnackbar
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
        onClickLogin()

    }

    private fun showCompanyInfo(){

        val companyInformation = CompanyInformationUtil.getCompanyInfo(applicationContext)
        if(companyInformation?.imagePath != null) {
             try {
                 Picasso.with(companyLogo.context).load(companyInformation.imagePath).fit().error(R.drawable.avatar).into(companyLogo)
             } catch (ex:Exception){

             }
        }
    }

    private fun onClickSettings(){

        settings.setOnClickListener {
            if(changeSubDomain.visibility == View.VISIBLE){
                changeSubDomain.visibility = View.INVISIBLE
            } else if(changeSubDomain.visibility == View.INVISIBLE){
                changeSubDomain.visibility = View.VISIBLE
            }
        }

    }

    private fun onClickChangeSubdomain(){

        changeSubDomain.setOnClickListener {

            CompanyInformationUtil.clearCompanyInfo(this@LoginActivity)
            SaveUserInformationUtil.clearUserInfo(this@LoginActivity)
            SubDomainApiClient.retrofit = null
            val myIntent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }

    private fun onClickLogin(){


        login_btn.setOnClickListener {

            if(inputValidated()){

                loginProcess(login_username.text.toString(),login_password.text.toString(),
                        getString(R.string.login_processing), false)
            }
        }

    }

    private fun inputValidated() : Boolean {

        if(login_username.text.trim().isEmpty()){
            CustomSnackbar.showMessage(this, getView(), getString(R.string.provide_username))
            return false
        }

        if (login_password.text.trim().isEmpty()) {
            CustomSnackbar.showMessage(this, getView(), getString(R.string.provide_password))
            return false
        }

     /*   if ((!checkInternetConnection())!!) {
            CustomSnackbar.showMessage(this, getView(), getString(R.string.check_internet_connection))
            return false
        }*/


        return true
    }

    private fun getView(): View {
        return (this@LoginActivity
                .findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)
    }

    private fun loginProcess(userName : String,password : String, dialogTitle : String, isForRefresh : Boolean){

        val companyInformationUtil = CompanyInformationUtil.getCompanyInfo(this@LoginActivity)
        val companyId = companyInformationUtil?.companyId

        if(companyId != null){

            CallLogin.call(userName,password,"password",
                    companyId,object : CallLogin.LogInCallBack{
                override fun onLogInSuccess(logInResponse: LogInResponse?) {

                    SaveUserInformationUtil.saveUserInfo(applicationContext, logInResponse!!)
                    CustomSnackbar.showMessage(this@LoginActivity,getView(),"Login Successful")
                    gotoDashBoard()
                }

                override fun onLogInFailure() {

                    CustomSnackbar.showMessage(this@LoginActivity, getView(), "Failed to authenticate! Please login again.")
                    SaveUserInformationUtil.clearUserInfo(this@LoginActivity)

                }

                override fun onServerFailure() {

                    CustomSnackbar.showMessage(this@LoginActivity, getView(),  getString(R.string.invalid_username_password))
                }


            })
        }

    }

    private fun gotoDashBoard(){
        val intent  = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }

}
