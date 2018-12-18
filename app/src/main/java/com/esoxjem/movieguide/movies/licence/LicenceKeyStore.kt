package com.esoxjem.movieguide.movies.licence

import android.content.Context
import android.content.SharedPreferences

import javax.inject.Inject

/**
 * @author necatisozer
 */
private const val LICENCE_KEY = "licenceKey"
private const val PREF_NAME = "LicenceKeyStore"

class LicenceKeyStore @Inject constructor(context: Context) {
    private val pref: SharedPreferences =
            context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getLicenceKey(): String? {
        return pref.getString(LICENCE_KEY, null)
    }

    fun setLicenceKey(licenceKey: String) {
        val editor = pref.edit()
        editor.putString(LICENCE_KEY, licenceKey)
        editor.apply()
    }
}
