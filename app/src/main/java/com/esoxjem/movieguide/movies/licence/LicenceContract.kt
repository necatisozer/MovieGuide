package com.esoxjem.movieguide.movies.licence

/**
 * @author necatisozer
 */
interface LicenceContract {
    interface View {
        fun dismissDialog()
        fun showSuccess()
        fun showError()
    }

    interface Presenter {
        fun checkAuthentication()
        fun checkLicenceKey(licenceKey: String)
        fun setView(view: View)
        fun destroy()
    }

    interface Interactor {
        fun checkAuthentication(): Boolean
        fun checkLicenceKey(licenceKey: String): Boolean
    }
}