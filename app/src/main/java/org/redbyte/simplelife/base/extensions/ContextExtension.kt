@file:Suppress("NOTHING_TO_INLINE")

package org.redbyte.simplelife.base.extensions

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

inline fun Context.appSettingsIntent() = Intent().apply {
    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    data = Uri.fromParts("package", packageName, null)
}

@Suppress("unused")
inline fun Context.openLocation(latitude: Double, longitude: Double, address: String) = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("geo:<$latitude>,<$longitude>?q=<$latitude>,<$longitude>($address)")
)

inline fun Context.openNotificationSettings() = Intent().also {
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
            it.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            it.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1 -> {
            it.action = "android.settings.APP_NOTIFICATION_SETTINGS"
            it.putExtra("app_package", packageName)
            it.putExtra("app_uid", applicationInfo.uid)
        }
        else -> {
            it.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            it.addCategory(Intent.CATEGORY_DEFAULT)
            it.data = Uri.parse("package:$packageName")
        }
    }
}

@Suppress("unused")
inline fun Context.openUrl(url: String) = Intent(Intent.ACTION_VIEW).apply {
    data = Uri.parse(url)
}

inline fun Context.resolveIntent(
        intent: Intent,
        crossinline onResolved: (intent: Intent) -> Unit,
        crossinline onResolutionNotFound: () -> Unit = {}
) {
    val packageManager = packageManager
    if (intent.resolveActivity(packageManager) != null)
        onResolved.invoke(intent)
    else
        onResolutionNotFound.invoke()
}

inline fun Context.compatDrawable(@DrawableRes drawableResId: Int): Drawable =
        ContextCompat.getDrawable(this, drawableResId)!!

@ColorInt
inline fun Context.compatColor(@ColorRes colorResId: Int): Int =
        ContextCompat.getColor(this, colorResId)

inline fun Context.compatColorStateList(@ColorRes colorResId: Int): ColorStateList =
        ContextCompat.getColorStateList(this, colorResId)!!

@ColorInt
inline fun Fragment.compatColor(@ColorRes colorResId: Int): Int =
        requireContext().compatColor(colorResId)

inline fun Fragment.compatDrawable(@DrawableRes drawableResId: Int): Drawable =
        requireContext().compatDrawable(drawableResId)

inline fun Fragment.compatColorStateList(@ColorRes colorResId: Int): ColorStateList =
        ContextCompat.getColorStateList(requireContext(), colorResId)!!

inline fun Context.selectableItemBackgroundDrawable(): Drawable {
    val outValue = TypedValue()
    theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
    return compatDrawable(outValue.resourceId)
}


inline fun Context.getBitmapFromVectorDrawable(drawableId: Int): Bitmap? {
    val drawable = ContextCompat.getDrawable(this, drawableId) ?: return null
    val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888) ?: return null
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}