package org.redbyte.simplelife.presentation.life

import org.redbyte.simplelife.base.BaseContract
import org.redbyte.simplelife.model.Cell

interface LifeContract {

    interface View : BaseContract.View {
        fun addCellAndCheck(cell: Cell)
        fun showCells(cells: List<Cell>)
        fun showError(message: String)
    }

    interface Presenter : BaseContract.Presenter {
        fun generateCell()
        fun checkCell(cells: List<Cell>)
    }
}