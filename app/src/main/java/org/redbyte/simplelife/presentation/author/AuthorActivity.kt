package org.redbyte.simplelife.presentation.author

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_author.*
import org.redbyte.App
import org.redbyte.simplelife.R
import org.redbyte.simplelife.base.BaseActivity
import org.redbyte.simplelife.base.extensions.openUrl
import org.redbyte.simplelife.base.extensions.resolveIntent

class AuthorActivity : BaseActivity<AuthorContract.Presenter>(), AuthorContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author)
        injectDependency()
        (presenter as AuthorPresenter).view = this
        presenter.start()
        setupViews()
    }

    private fun setupViews() {
        tvTelegram.setOnClickListener { presenter.onTelegramClick(tvTelegram.text.toString()) }
        tvEmail.setOnClickListener { presenter.onEmailClick(tvEmail.text.toString()) }
        tvWhatsApp.setOnClickListener { presenter.onWhatsAppCLick(tvWhatsApp.text.toString()) }
    }

    private fun injectDependency() {
        App.instance.getAppComponent()
            .authorComponentBuilder()
            .build()
            .inject(this)
    }

    override fun openApp(uri: String, appName: String) {
        val context = this
        val intent = context.openUrl(uri)
        context.resolveIntent(intent, ::startActivity) { showOpenAppErrorToast(appName) }
    }

    override fun sendEmail(email: String) {
        val subject = getString(R.string.author_email_subject)
        openApp("mailto:$email?subject=$subject", "Mail")
    }

    private fun showOpenAppErrorToast(appName: String) {
        val text = getString(R.string.author_error_open_app, appName)
        Toast.makeText(this, text, Toast.LENGTH_LONG)
            .show()
    }

    companion object {
        fun openScreen(context: Context): Intent =
            Intent(context, AuthorActivity::class.java)
    }
}

