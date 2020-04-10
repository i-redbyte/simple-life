package org.redbyte.simplelife.di.modules

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import org.redbyte.simplelife.life.LifeContract
import org.redbyte.simplelife.life.LifePresenter

@Module
class LifeModule(private var activity: AppCompatActivity) {

    @Provides
    fun provideActivity(): AppCompatActivity {
        return activity
    }

    @Provides
    fun providePresenter(): LifeContract.Presenter {
        return LifePresenter()
    }

}