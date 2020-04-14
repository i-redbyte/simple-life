package org.redbyte.simplelife.presentation.life

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.redbyte.simplelife.base.BasePresenter
import org.redbyte.simplelife.di.scopes.LifeScope
import org.redbyte.simplelife.domain.UseCase
import org.redbyte.simplelife.domain.life.GenerateCell
import org.redbyte.simplelife.model.Cell
import org.redbyte.simplelife.model.Type.*
import javax.inject.Inject

@LifeScope
class LifePresenter @Inject constructor(
    private val generateCell: GenerateCell
) : BasePresenter<LifeContract.View>(), LifeContract.Presenter {
    override  lateinit var view: LifeContract.View

    override fun start() = Unit

    override fun generateCell() {
        disposables += generateCell.execute(UseCase.None)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ view.addCellAndCheck(it) }) {
                view.showError(
                    it.message ?: "${this.javaClass.simpleName} failed: generate cell"
                )
            }
    }

    override fun checkCell(list: List<Cell>) {
        if (list.size < CRITICAL_MASS + 1) return
        val cells: MutableList<Cell> = list.toMutableList()
        val n = cells.size - 1
        var livingCellsCount = 0
        var deadCellsCount = 0
        var oldCell: Cell? = null

        for (i in 0..n) {
            val cell = cells[i]
            if (cell.type == DEAD && oldCell?.type == DEAD) {
                deadCellsCount++
                livingCellsCount = 0
            } else if (cell.type == LIVELY && oldCell?.type == LIVELY) {
                livingCellsCount++
                deadCellsCount = 0
            } else {
                livingCellsCount = 0
                deadCellsCount = 0
            }

            oldCell = cell
        }

        if (livingCellsCount == CRITICAL_MASS) {
            cells.add(Cell(LIFE))
            view.showCells(cells)
            return
        }

        if (deadCellsCount == CRITICAL_MASS) {
            for (i in n downTo 0) {
                if (cells[i].type == LIFE) {
                    cells.removeAt(i)
                    view.showCells(cells)
                    break
                }
            }

        }
    }

    companion object {
        private const val CRITICAL_MASS = 2
    }
}