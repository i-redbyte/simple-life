package org.redbyte.simplelife.presentation.author

import org.redbyte.simplelife.base.BaseContract

interface AuthorContract {

    interface View : BaseContract.View {
        fun openApp(uri: String, appName: String)
        fun sendEmail(email: String)
    }

    interface Presenter : BaseContract.Presenter {
        var view: View
        fun onEmailClick(email: String)
        fun onTelegramClick(telegram: String)
        fun onWhatsAppCLick(whatsApp: String)
    }
}