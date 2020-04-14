package org.redbyte.simplelife.presentation.author

import org.redbyte.simplelife.base.BasePresenter
import org.redbyte.simplelife.di.scopes.AuthorScope
import javax.inject.Inject

@AuthorScope
class AuthorPresenter @Inject constructor(
) : BasePresenter<AuthorContract.View>(), AuthorContract.Presenter {

    override lateinit var view: AuthorContract.View

    override fun start() = Unit

    override fun onEmailClick(email: String) {
        view.sendEmail(email)
    }

    override fun onTelegramClick(telegram: String) = tryOpenUrl(
        "https://telegram.me/",
        telegram,
        "Telegram"
    )

    override fun onWhatsAppCLick(whatsApp: String) = tryOpenUrl(
        "https://api.whatsapp.com/send?phone=",
        whatsApp,
        "WhatsApp"
    )

    private fun tryOpenUrl(url: String, urlPostfix: String?, appName: String) {
        val finalUrl = concatOrNull(url, urlPostfix) ?: return
        view.openApp(finalUrl, appName)
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun concatOrNull(string: String, postfix: String?): String? =
        if (postfix.isNullOrBlank()) null else string + postfix
}