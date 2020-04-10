package org.redbyte.simplelife.life

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.redbyte.simplelife.R
import org.redbyte.simplelife.di.DaggerLifeComponent
import org.redbyte.simplelife.di.modules.LifeModule

class LifeActivity : AppCompatActivity(), LifeContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life)
        injectDependency()
    }

    private fun injectDependency() {
        val activityComponent = DaggerLifeComponent.builder()
            .lifeModule(LifeModule(this))
            .build()

        activityComponent.inject(this)
    }
}
