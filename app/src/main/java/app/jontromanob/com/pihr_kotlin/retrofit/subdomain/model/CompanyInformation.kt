package app.jontromanob.com.pihr_kotlin.retrofit.subdomain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import lombok.Data
import lombok.Getter
import lombok.Setter

class CompanyInformation {

    @SerializedName("CompanyId")
    @Expose
    var companyId: Int? = null
    @SerializedName("CompanyName")
    @Expose
    var companyName: String? = null
    @SerializedName("AppClientId")
    @Expose
    var appClientId: Int? = null
    @SerializedName("Email")
    @Expose
    var email: String? = null
    @SerializedName("Logo")
    @Expose
    var logo: Any? = null
    @SerializedName("DateFormat")
    @Expose
    var dateFormat: String? = null
    @SerializedName("CurrencyId")
    @Expose
    var currencyId: Int? = null
    @SerializedName("TimeZoneId")
    @Expose
    var timeZoneId: Int? = null
    @SerializedName("PasswordMinLength")
    @Expose
    var passwordMinLength: Int? = null
    @SerializedName("WrongTry")
    @Expose
    var wrongTry: Int? = null
    @SerializedName("LockedDurationMinutes")
    @Expose
    var lockedDurationMinutes: Int? = null
    @SerializedName("SpecialCharRequired")
    @Expose
    var specialCharRequired: Boolean? = null
    @SerializedName("PasswordExpireMonths")
    @Expose
    var passwordExpireMonths: Int? = null
    @SerializedName("AutoGenerateEmployeeCode")
    @Expose
    var autoGenerateEmployeeCode: Boolean? = null
    @SerializedName("EmployeeCodeLength")
    @Expose
    var employeeCodeLength: Int? = null
    @SerializedName("Prefix")
    @Expose
    var prefix: Any? = null
    @SerializedName("Suffix")
    @Expose
    var suffix: Any? = null
    @SerializedName("EmployeeHaveAccount")
    @Expose
    var employeeHaveAccount: Boolean? = null
    @SerializedName("Address")
    @Expose
    var address: String? = null
    @SerializedName("CountryId")
    @Expose
    var countryId: Int? = null
    @SerializedName("StateId")
    @Expose
    var stateId: Int? = null
    @SerializedName("CityId")
    @Expose
    var cityId: Int? = null
    @SerializedName("ZipCode")
    @Expose
    var zipCode: String? = null
    @SerializedName("StartMonth")
    @Expose
    var startMonth: Int? = null
    @SerializedName("EndMonth")
    @Expose
    var endMonth: Int? = null
    @SerializedName("Subdomain")
    @Expose
    var subdomain: String? = null
    @SerializedName("CountryName")
    @Expose
    var countryName: String? = null
    @SerializedName("StateName")
    @Expose
    var stateName: String? = null
    @SerializedName("CityName")
    @Expose
    var cityName: String? = null
    @SerializedName("CurrencyName")
    @Expose
    var currencyName: String? = null
    @SerializedName("CurrencySymbol")
    @Expose
    var currencySymbol: String? = null
    @SerializedName("TimeZoneOffset")
    @Expose
    var timeZoneOffset: String? = null
    @SerializedName("ImagePath")
    @Expose
    var imagePath: String? = null
    @SerializedName("TimeZone")
    @Expose
    var timeZone: Any? = null
    @SerializedName("Currency")
    @Expose
    var currency: Any? = null
    @SerializedName("CD")
    @Expose
    var cD: String? = null
    @SerializedName("MD")
    @Expose
    var mD: String? = null
    @SerializedName("CB")
    @Expose
    var cB: Int? = null
    @SerializedName("MB")
    @Expose
    var mB: Int? = null
    @SerializedName("Rows")
    @Expose
    var rows: Int? = null
    @SerializedName("MessageType")
    @Expose
    var messageType: Int? = null
    @SerializedName("MessageString")
    @Expose
    var messageString: Any? = null
    @SerializedName("MessageDetails")
    @Expose
    var messageDetails: Any? = null


}