package com.app.ecom.utils
import android.util.Log
import com.app.ecom.BaseApplication
import com.app.ecom.common.Constants



fun setUserSelectedLanuage(value: String) {
    val preferenceKey = Constants.PreferencesKeys.USER_SELECTED_LANGUAGE
    BaseApplication.instance.getLocalPreferneces()?.addString(
        preferenceKey,
        value
    )
}

 fun getUserSelectedLanguage(): String {
    val preferenceKey = Constants.PreferencesKeys.USER_SELECTED_LANGUAGE
    return BaseApplication.instance.getLocalPreferneces()?.getString(preferenceKey, "en") ?: "en"
}

fun setUserSelectedRegion(value: String) {
    val preferenceKey = Constants.PreferencesKeys.USER_SELECTED_REGION
    BaseApplication.instance.getLocalPreferneces()?.addString(
        preferenceKey,
        value
    )
}

 fun getUserSelectedRegion(): String {
    val preferenceKey = Constants.PreferencesKeys.USER_SELECTED_REGION
    return BaseApplication.instance.getLocalPreferneces()?.getString(preferenceKey, "") ?: ""
}



 fun setLoggedInUserName(value: String) {
    val preferenceKey = Constants.PreferencesKeys.USER_NAME
    BaseApplication.instance.getLocalPreferneces()?.addString(
        preferenceKey,
        value
    )
}

 fun getLoggedInUserName(): String {
    val preferenceKey = Constants.PreferencesKeys.USER_NAME
    return BaseApplication.instance.getLocalPreferneces()?.getString(preferenceKey, "") ?: ""
}

 fun setLoggedInUserId(value: String) {
    val preferenceKey = Constants.PreferencesKeys.USER_ID
    BaseApplication.instance.getLocalPreferneces()?.addString(
        preferenceKey,
        value
    )
}

 fun getLoggedInUserId(): String {
    val preferenceKey = Constants.PreferencesKeys.USER_ID
    return BaseApplication.instance.getLocalPreferneces()?.getString(preferenceKey, "") ?: ""
}

 fun setLoggedInUserEmail(value: String) {
    val preferenceKey = Constants.PreferencesKeys.USER_EMAIL
    BaseApplication.instance.getLocalPreferneces()?.addString(
        preferenceKey,
        value
    )
}

 fun getLoggedInUserEmail(): String {
    val preferenceKey = Constants.PreferencesKeys.USER_EMAIL
    return BaseApplication.instance.getLocalPreferneces()?.getString(preferenceKey, "") ?: ""
}

 fun setLoggedInUserContactNumber(value: String) {
    val preferenceKey = Constants.PreferencesKeys.USER_CONTACT_NUMBER
    BaseApplication.instance.getLocalPreferneces()?.addString(
        preferenceKey,
        value
    )
}

 fun getLoggedInUserContactNumber(): String {
    val preferenceKey = Constants.PreferencesKeys.USER_CONTACT_NUMBER
    return BaseApplication.instance.getLocalPreferneces()?.getString(preferenceKey, "") ?: ""
}

 fun setLoggedInUserDistrict(value: String) {
    val preferenceKey = Constants.PreferencesKeys.USER_DISTRICT
    BaseApplication.instance.getLocalPreferneces()?.addString(
        preferenceKey,
        value
    )
}

 fun getLoggedInUserDistrict(): String {
    val preferenceKey = Constants.PreferencesKeys.USER_DISTRICT
    return BaseApplication.instance.getLocalPreferneces()?.getString(preferenceKey, "") ?: ""
}
 fun setLoggedInUserToken(value: String) {
    val preferenceKey = Constants.PreferencesKeys.USER_TOKEN
    BaseApplication.instance.getLocalPreferneces()?.addString(
        preferenceKey,
        value
    )
}

 fun getLoggedInUserToken(): String {
    val preferenceKey = Constants.PreferencesKeys.USER_TOKEN
    return BaseApplication.instance.getLocalPreferneces()?.getString(preferenceKey, "") ?: ""
}

