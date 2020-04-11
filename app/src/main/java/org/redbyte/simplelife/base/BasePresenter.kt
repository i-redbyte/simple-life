package org.redbyte.simplelife.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter {
    protected var disposables = CompositeDisposable()

    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun stop() {
        disposables.clear()
    }
}