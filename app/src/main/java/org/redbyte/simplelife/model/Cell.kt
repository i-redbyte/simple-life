package org.redbyte.simplelife.model

class Cell(val type: Type)

enum class Type {
    DEAD,
    LIFE,
    LIVELY
}