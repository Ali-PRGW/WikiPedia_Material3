package ir.dunijet.wikipedia_m3.ext

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.viewbinding.ViewBinding
import com.google.android.material.color.MaterialColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import ir.dunijet.wikipedia_m3.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "Pref")

suspend fun Context.writePref(key: String, value: String) {
    val preferenceKey = stringPreferencesKey(key)
    dataStore.edit {
        it[preferenceKey] = value
    }
}

suspend fun Context.readPref(key: String) :String? {
    val preferenceKey = stringPreferencesKey(key)
    val preference = dataStore.data.first()
    return preference[preferenceKey]
}

fun Context.showSnackbar(view: View, str: String) {
    Snackbar.make(view, str, Snackbar.LENGTH_SHORT).show()
}

fun Context.showDialog(title: String, detail: String) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(detail)
        .setPositiveButton("Submit") { dialog, which ->
            dialog.dismiss()
        }
        .show()
}

fun Context.copyToClipboard(text: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("url", text)
    clipboard.setPrimaryClip(clip)
}

fun AppCompatActivity.setNavigationColor() {
    val colorSurface =
        MaterialColors.getColor(this, com.google.android.material.R.attr.colorSurface, null)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.window.navigationBarColor = colorSurface
    }
}