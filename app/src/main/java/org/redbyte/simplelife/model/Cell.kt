package org.redbyte.simplelife.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Cell(val type: Type) : Parcelable

enum class Type {
    DEAD,
    LIFE,
    LIVELY
}