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
        return Single.just(Cell(type = randomEnum(Type::class.java)))
    }

    fun <T : Enum<*>?> randomEnum(clazz: Class<T>): T {
        val x: Int = Random.nextInt(clazz.enumConstants?.size ?: 0)
        return clazz.enumConstants?.get(x) ?: throw RuntimeException()
    }

}