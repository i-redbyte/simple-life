package org.redbyte.simplelife.di

import dagger.Component
import org.redbyte.App
import org.redbyte.simplelife.di.modules.AppModule
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(application: App)

    fun lifeComponentBuilder(): LifeComponent.Builder

}