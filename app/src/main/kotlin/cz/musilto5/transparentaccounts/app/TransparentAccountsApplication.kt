package cz.musilto5.transparentaccounts.app

import android.app.Application
import cz.musilto5.transparentaccounts.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TransparentAccountsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TransparentAccountsApplication)
            modules(appModule)
        }
    }
}
