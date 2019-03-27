package app.jontromanob.com.pihr_kotlin.retrofit.login.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Hi on 3/27/2019.
 */
class LogInResponse {

    @SerializedName("access_token")
    @Expose
    var accessToken: String? = null
    @SerializedName("token_type")
    @Expose
    var tokenType: String? = null
    @SerializedName("expires_in")
    @Expose
    var expiresIn: Int? = null
    @SerializedName("refresh_token")
    @Expose
    var refresh_token: String? = null
    @SerializedName("UserId")
    @Expose
    var userId: String? = null
    @SerializedName("CompanyId")
    @Expose
    var companyId: String? = null
    @SerializedName("DateFormat")
    @Expose
    var dateFormat: String? = null
    @SerializedName("UserName")
    @Expose
    var userName: String? = null
    @SerializedName("EmployeeId")
    @Expose
    var employeeId: String? = null
    @SerializedName("UserCategoryId")
    @Expose
    var userCategoryId: String? = null
    @SerializedName("UserCategory")
    @Expose
    var userCategory: String? = null
    @SerializedName("IsTransportManager")
    @Expose
    var isTransportManager: String? = null
    @SerializedName("DriverId")
    @Expose
    var driverId: String? = null
    @SerializedName("UserImageUrl")
    @Expose
    var userImageUrl: String? = null
    @SerializedName("EmployeeName")
    @Expose
    var employeeName: String? = null
    @SerializedName("Designation")
    @Expose
    var designation: String? = null
    @SerializedName(".issued")
    @Expose
    var issued: String? = null
    @SerializedName(".expires")
    @Expose
    var expires: String? = null
}