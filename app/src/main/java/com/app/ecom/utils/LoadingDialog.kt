package com.app.ecom.utils

import android.app.Activity
import com.app.ecom.presentation.dialog.WaitingDialog


fun showPd(isLoading: Boolean, activity: Activity) {
    try {
        if (!activity.isFinishing!! && !activity.isDestroyed!!) {
            if (isLoading) {
                WaitingDialog.show(activity)
            }
            else {
                WaitingDialog.dismiss()
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
