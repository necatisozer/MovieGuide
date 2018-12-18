package com.esoxjem.movieguide.movies.licence

import dagger.Module
import dagger.Provides

/**
 * @author necatisozer
 */
@Module
class LicenceModule {
    @Provides
    fun providesLicenceDialogInteractor(store: LicenceKeyStore): LicenceContract.Interactor {
        return LicenceDialogInteractor(store)
    }

    @Provides
    fun providePresenter(interactor: LicenceContract.Interactor): LicenceContract.Presenter {
        return LicenceDialogPresenter(interactor)
    }
}
