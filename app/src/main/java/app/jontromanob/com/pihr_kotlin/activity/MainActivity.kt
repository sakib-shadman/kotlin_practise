package app.jontromanob.com.pihr_kotlin.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import app.jontromanob.com.pihr_kotlin.R
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


            }
        })
    }

    private fun getInput(){

        if (subDomainName.text.toString().trim { it <= ' ' }.isEmpty()) {
            CustomSnackbar.showMessage(this, getView(), getString(R.string.provide_sub_domain))
            return
        }

        if ((!checkInternetConnection())!!) {
            CustomSnackbar.showMessage(this, getView(), getString(R.string.check_internet_connection))
            return
        }
    }




}
