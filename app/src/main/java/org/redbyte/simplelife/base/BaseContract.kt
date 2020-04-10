package org.redbyte.simplelife.base

interface BaseContract {

    interface View

    interface Presenter {
        fun start()
        fun stop()
    }

}
