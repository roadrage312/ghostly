package com.ghostly.android.ui.components

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()

fun Context.toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, id, duration).show()

fun Context.versionName() = try {
    val pInfo = packageManager.getPackageInfo(this.packageName, 0);
    pInfo.versionName
} catch (e: PackageManager.NameNotFoundException) {
    e.printStackTrace();
    0
}