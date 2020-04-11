package org.redbyte.simplelife.di

import dagger.Subcomponent
import org.redbyte.simplelife.di.modules.LifeModule
import org.redbyte.simplelife.di.scopes.LifeScope
import org.redbyte.simplelife.presentation.life.LifeActivity

@LifeScope
@Subcomponent(modules = [LifeModule::class])
interface LifeComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): LifeComponent
    }

    fun inject(lifeActivity: LifeActivity)
}