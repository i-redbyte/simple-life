package org.redbyte.simplelife.di.modules

import dagger.Binds
import dagger.Module
import org.redbyte.simplelife.di.scopes.AuthorScope
import org.redbyte.simplelife.presentation.author.AuthorContract
import org.redbyte.simplelife.presentation.author.AuthorPresenter

@Module
interface AuthorModule {

    @AuthorScope
    @Binds
    fun presenter(presenter: AuthorPresenter): AuthorContract.Presenter

}