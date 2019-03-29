package app.jontromanob.com.pihr_kotlin.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import app.jontromanob.com.pihr_kotlin.R
import app.jontromanob.com.pihr_kotlin.util.SaveUserInformationUtil
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_pihrlauncher.*

class HomeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        title = ""
        val mContext = this
        toolbar.setNavigationIcon(R.drawable.nav_drwaer_option)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        toggle.isDrawerIndicatorEnabled = false

        toggle.toolbarNavigationClickListener = View.OnClickListener {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            } else {
                drawer_layout.openDrawer(GravityCompat.START)
            }
        }

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener {
            when (it.itemId){

                R.id.navLogout -> logout()


            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true

        }
        nav_view.itemIconTintList = null

    }

    private fun changeToolbarTitle(title : String){
        toolbar.title = title
    }

    private fun logout() {
        //SaveUserCredentials.clearCredentials(this)
        SaveUserInformationUtil.clearUserInfo(this)
        //DashBoardMenuFragment.currentMenu = MenuNames.DASHBOARD
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


}
