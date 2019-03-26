package app.jontromanob.com.pihr_kotlin.util

import android.content.Context
import android.preference.PreferenceManager
import app.jontromanob.com.pihr_kotlin.retrofit.subdomain.model.CompanyInformation
import com.google.gson.Gson

class CompanyInformationUtil {

    var mContext: Context? = null
    companion object {

        var COMPANY_INFORMATION_CONSTANT = "companyinformation"



       /* fun setmContext(mContext: Context) {
            CompanyInformationUtil.mContext = mContext
        }*/

        fun saveCompanyInfo(context: Context, companyInformation: CompanyInformation) {

            val mPrefs = PreferenceManager.getDefaultSharedPreferences(context)
            val prefsEditor = mPrefs.edit()
            val gson = Gson()
            val json = gson.toJson(companyInformation)
            prefsEditor.putString(COMPANY_INFORMATION_CONSTANT, json)
            prefsEditor.commit()
        }

        fun getCompanyInfo(context: Context): CompanyInformation? {
            val mPrefs = PreferenceManager.getDefaultSharedPreferences(context)
            val gson = Gson()
            val json = mPrefs.getString(COMPANY_INFORMATION_CONSTANT, "")
            return if (json != null && json.length > 1) {
                gson.fromJson<CompanyInformation>(json, CompanyInformation::class.java)
            } else null
        }

        fun clearCompanyInfo(context: Context) {

            val mPrefs = PreferenceManager.getDefaultSharedPreferences(context)
            val prefsEditor = mPrefs.edit()
            prefsEditor.remove(COMPANY_INFORMATION_CONSTANT)
            prefsEditor.commit()
        }

        /**
         *
         * @return
         */
        /*fun getSubDomainName(): String {

            if (mContext == null) {
                mContext = HomeActivity.getContext()
            }

            var companyInformation: CompanyInformation? = null
            val mPrefs = PreferenceManager.getDefaultSharedPreferences(mContext)
            val gson = Gson()
            val json = mPrefs.getString(COMPANY_INFORMATION_CONSTANT, "")
            if (json != null && json.length > 1) {
                companyInformation = gson.fromJson<CompanyInformation>(json, CompanyInformation::class.java!!)
            }

            return companyInformation!!.getSubdomain()
        }*/
    }
}