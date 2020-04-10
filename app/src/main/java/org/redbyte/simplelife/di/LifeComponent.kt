package org.redbyte.simplelife.di

import dagger.Component
import org.redbyte.simplelife.life.LifeActivity
import org.redbyte.simplelife.di.modules.LifeModule
import org.redbyte.simplelife.di.scoups.LifeScreen

@LifeScreen
@Component(modules = [LifeModule::class])
interface LifeComponent {
    fun inject(activity: LifeActivity)
}