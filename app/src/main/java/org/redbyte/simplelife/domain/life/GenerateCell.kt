package org.redbyte.simplelife.domain.life

import io.reactivex.Single
import org.redbyte.simplelife.di.scopes.LifeScope
import org.redbyte.simplelife.domain.SingleUseCase
import org.redbyte.simplelife.domain.UseCase.None
import org.redbyte.simplelife.model.Cell
import org.redbyte.simplelife.model.Type
import javax.inject.Inject
import kotlin.random.Random

@LifeScope
class GenerateCell @Inject constructor() : SingleUseCase<Cell, None>() {

    override fun execute(params: None): Single<Cell> {
        return Single.just(Cell(randomCell()))
    }

    // TODO: Red_byte 2020-04-14 For test generation
    private fun <T : Enum<*>?> randomEnumCell(clazz: Class<T>): T {
        val x: Int = Random.nextInt(clazz.enumConstants?.size ?: 0)
        return clazz.enumConstants?.get(x) ?: throw RuntimeException()
    }

    private fun randomCell(): Type =
        if (Random.nextInt(100) and 1 == 0) Type.DEAD
        else Type.LIVELY
}