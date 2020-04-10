package org.redbyte.simplelife.di

import dagger.Component
import org.redbyte.App
import org.redbyte.simplelife.di.modules.AppModule

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(application: App)

}