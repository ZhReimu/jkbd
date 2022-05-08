package com.mrx.jkbd.entity

import com.mrx.mybatis.util.ReflectUtil
import kotlin.reflect.KMutableProperty

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
@Suppress("unused")
abstract class BaseQuestion {

    var answer: Int = 0

    fun getDecodedAnswer(): Char {
        return Char((answer / 16 + 64).toUShort())
    }

    fun getDecodedField(field: KMutableProperty<ByteArray?>): String {
        return ReflectUtil.decode(field.getter.call())
    }
}