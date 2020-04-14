package org.redbyte.simplelife.presentation.life

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_life.*
import org.redbyte.App
import org.redbyte.simplelife.R
import org.redbyte.simplelife.base.BaseActivity
import org.redbyte.simplelife.base.DelegationAdapter
import org.redbyte.simplelife.model.Cell

class LifeActivity : BaseActivity<LifeContract.Presenter>(), LifeContract.View {
    private lateinit var adapter: DelegationAdapter
    private lateinit var lifeDelegate: LifeDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life)
        injectDependency()
        (presenter as LifePresenter).view = this
        presenter.start()
        setupViews()
    }

    private fun setupViews() {
        btnCreate.setOnClickListener {
            presenter.generateCell()
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = DelegationAdapter()
        lifeDelegate = LifeDelegate(this)
        rvLive.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter
            .delegatesManager
            .addDelegate(lifeDelegate)
        rvLive.adapter = adapter
    }

    private fun injectDependency() {
        App.instance.getAppComponent()
            .lifeComponentBuilder()
            .build()
            .inject(this)
    }

    override fun addCellAndCheck(cell: Cell) {
        val cells = adapter.items.toMutableList()
        cells.add(cell)
        adapter.setItems(cells, DelegationAdapter.Payload.UPDATE)
        presenter.checkCell(cells.map { it as Cell })
        scrollToEnd()
    }

    override fun showCells(cells: List<Cell>) {
        adapter.setItems(cells, DelegationAdapter.Payload.UPDATE)
        scrollToEnd()
    }

    private fun scrollToEnd() {
        val pos = rvLive.adapter?.itemCount ?: 0
        rvLive.smoothScrollToPosition(pos - 1)
    }

    override fun showError(message: String) {
        Snackbar
            .make(clMainContainer, message, Snackbar.LENGTH_LONG)
            .show()
    }
}
