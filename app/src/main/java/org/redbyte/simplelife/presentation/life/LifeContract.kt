package org.redbyte.simplelife.presentation.life

import org.redbyte.simplelife.base.BaseContract
import org.redbyte.simplelife.model.Cell

interface LifeContract {

    interface View : BaseContract.View {
        fun addCell(cell: Cell)
        fun showError(message: String)
    }

    interface Presenter : BaseContract.Presenter {

    }
}