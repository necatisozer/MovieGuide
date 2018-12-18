package com.esoxjem.movieguide.movies.licence

import com.esoxjem.movieguide.BuildConfig
import com.esoxjem.movieguide.movies.utils.extensions.encrypt

/**
 * @author necatisozer
 */
internal class LicenceDialogInteractor(
        private val licenceKeyStore: LicenceKeyStore) :
        LicenceContract.Interactor {

    val validLicenceKeys = BuildConfig.ENCODED_LICENCE_KEYS.split(",")

    override fun checkAuthentication(): Boolean {
        licenceKeyStore.getLicenceKey().let {
            return validLicenceKeys.contains(it)
        }
    }

    override fun checkLicenceKey(licenceKey: String): Boolean {
        try {
            licenceKey.encrypt(BuildConfig.ENCRYPTION_KEY).let {
                if (validLicenceKeys.contains(it)) {
                    licenceKeyStore.setLicenceKey(it)
                    return true
                } else {
                    return false
                }
            }
        } catch (_: Exception) {
            return false
        }

    }
}
