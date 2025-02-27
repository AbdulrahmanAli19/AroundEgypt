package abdulrahman.ali19.aroundegypt.presentation

import abdulrahman.ali19.aroundegypt.di.HomeModule
import abdulrahman.ali19.aroundegypt.di.appUtils
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AroundEgyptApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AroundEgyptApplication)
            modules(
                appUtils + HomeModule
            )
        }
    }
}