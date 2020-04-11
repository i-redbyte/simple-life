package org.redbyte.simplelife.presentation.life

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.redbyte.simplelife.base.BasePresenter
import org.redbyte.simplelife.di.scopes.LifeScope
import org.redbyte.simplelife.domain.UseCase
import org.redbyte.simplelife.domain.life.GenerateCell
import javax.inject.Inject

@LifeScope
class LifePresenter @Inject constructor(
    private val generateCell: GenerateCell
) : BasePresenter<LifeContract.View>(), LifeContract.Presenter {
    lateinit var view: LifeContract.View

    override fun start() {
        disposables += generateCell.execute(UseCase.None)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ view.addCell(it) }) { view.showError(it.message ?: "") }
    }

}