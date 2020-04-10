package redbyte.pikabu.base

import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<P : BaseContract.Presenter> :
    AppCompatActivity(),
    BaseContract.View {

    @Inject
    lateinit var presenter: P

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
    }

}

