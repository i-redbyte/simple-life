package org.redbyte.simplelife.presentation.life

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_life.*
import org.redbyte.App
import org.redbyte.simplelife.R
import org.redbyte.simplelife.base.BaseActivity
import org.redbyte.simplelife.model.Cell

class LifeActivity : BaseActivity<LifeContract.Presenter>(), LifeContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life)
        injectDependency()
        (presenter as LifePresenter).view = this
        presenter.start()
    }

    private fun injectDependency() {
        App.instance.getAppComponent()
            .lifeComponentBuilder()
            .build()
            .inject(this)
    }

    override fun addCell(cell: Cell) {

    }

    override fun showError(message: String) {
        Snackbar
            .make(clMainContainer, message, Snackbar.LENGTH_LONG)
            .show()
    }
}
