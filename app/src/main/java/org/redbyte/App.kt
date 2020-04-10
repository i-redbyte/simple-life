package org.redbyte

import android.app.Application
import org.redbyte.simplelife.di.AppComponent
import org.redbyte.simplelife.di.DaggerAppComponent
import org.redbyte.simplelife.di.modules.AppModule

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setupDagger()

    }

    private fun setupDagger() {
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
        component.inject(this)
    }

    fun getAppComponent(): AppComponent {
        return component
    }

    companion object {
        lateinit var instance: App private set
    }
}
