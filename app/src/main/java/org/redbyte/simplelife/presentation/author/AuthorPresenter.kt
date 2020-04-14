package org.redbyte.simplelife.presentation.author

import org.redbyte.simplelife.base.BasePresenter
import org.redbyte.simplelife.di.scopes.AuthorScope
import javax.inject.Inject

@AuthorScope
class AuthorPresenter @Inject constructor(
) : BasePresenter<AuthorContract.View>(), AuthorContract.Presenter {
    override fun start() {
    }
}