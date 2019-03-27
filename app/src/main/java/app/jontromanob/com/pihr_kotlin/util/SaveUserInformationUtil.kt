package app.jontromanob.com.pihr_kotlin.util

import android.content.Context
import android.preference.PreferenceManager
import app.jontromanob.com.pihr_kotlin.retrofit.login.Model.LogInResponse
import com.google.gson.Gson

/**
 * Created by Hi on 3/27/2019.
 */
class SaveUserInformationUtil {

    companion object {

        val USER_INFORMATION_CONSTANT = "userinformation"

        fun saveUserInfo(context: Context, logInResponse: LogInResponse) {

            val mPrefs = PreferenceManager.getDefaultSharedPreferences(context)
            val prefsEditor = mPrefs.edit()
            val gson = Gson()
            val json = gson.toJson(logInResponse)
            prefsEditor.putString(USER_INFORMATION_CONSTANT, json)
            prefsEditor.commit()
        }

        fun getUserInfo(context: Context): LogInResponse? {
            val mPrefs = PreferenceManager.getDefaultSharedPreferences(context)
            val gson = Gson()
            val json = mPrefs.getString(USER_INFORMATION_CONSTANT, "")
            return if (json != null && json.length > 1) {
                gson.fromJson<LogInResponse>(json, LogInResponse::class.java!!)
            } else null
        }

        fun clearUserInfo(context: Context) {

            val mPrefs = PreferenceManager.getDefaultSharedPreferences(context)
            val prefsEditor = mPrefs.edit()
            prefsEditor.remove(USER_INFORMATION_CONSTANT)
            prefsEditor.commit()
        }
    }

}