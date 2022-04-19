package ru.zar1official.hackathon_final.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.zar1official.hackathon_final.di.appModule
import ru.zar1official.hackathon_final.di.dataModule
import ru.zar1official.hackathon_final.di.domainModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}