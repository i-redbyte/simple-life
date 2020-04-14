package org.redbyte.simplelife.presentation.author

import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.redbyte.App
import org.redbyte.simplelife.R
import org.redbyte.simplelife.base.BaseActivity

class AuthorActivity : BaseActivity<AuthorContract.Presenter>(), AuthorContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author)
        injectDependency()
        presenter.start()
    }

    private fun injectDependency() {
        App.instance.getAppComponent()
            .authorComponentBuilder()
            .build()
            .inject(this)
    }

    companion object {
        fun openScreen(context: Context): Intent =
            Intent(context, AuthorActivity::class.java)
    }
}

