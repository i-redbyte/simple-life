package org.redbyte.simplelife.di.modules

import dagger.Binds
import dagger.Module
import org.redbyte.simplelife.di.scopes.LifeScope
import org.redbyte.simplelife.presentation.life.LifeActivity
import org.redbyte.simplelife.presentation.life.LifeContract
import org.redbyte.simplelife.presentation.life.LifePresenter

@Module
interface LifeModule {

//    @LifeScope
//    @Binds
//    fun view(view: LifeActivity): LifeContract.View

    @LifeScope
    @Binds
    fun presenter(presenter: LifePresenter): LifeContract.Presenter

}