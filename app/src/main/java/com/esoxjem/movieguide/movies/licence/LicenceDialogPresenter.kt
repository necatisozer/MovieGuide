package com.esoxjem.movieguide.movies.licence

/**
 * @author necatisozer
 */
class LicenceDialogPresenter(
        private val licenceDialogInteractor: LicenceContract.Interactor) :
        LicenceContract.Presenter {
    private var view: LicenceContract.View? = null

    override fun setView(view: LicenceContract.View) {
        this.view = view
    }

    override fun destroy() {
        view = null
    }

    override fun checkAuthentication() {
        if (licenceDialogInteractor.checkAuthentication()) {
            view?.dismissDialog()
        }
    }

    override fun checkLicenceKey(licenceKey: String) {
        if (licenceDialogInteractor.checkLicenceKey(licenceKey))
            view?.showSuccess()
        else {
            view?.showError()
        }
    }
}
