package org.redbyte.simplelife.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import org.redbyte.App
import org.redbyte.simplelife.di.scoups.PerApplication
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {
    @Provides
    @Singleton
    @PerApplication
    fun provideApp(): Application {
        return app
    }
}