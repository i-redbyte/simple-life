package org.redbyte.simplelife.di

import dagger.Subcomponent
import org.redbyte.simplelife.di.modules.AuthorModule
import org.redbyte.simplelife.di.scopes.AuthorScope
import org.redbyte.simplelife.presentation.author.AuthorActivity

@AuthorScope
@Subcomponent(modules = [AuthorModule::class])
interface AuthorComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): AuthorComponent
    }

    fun inject(authorActivity: AuthorActivity)
}