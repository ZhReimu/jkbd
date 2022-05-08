package com.mrx.jkbd.entity

import com.mrx.mybatis.interfaces.Decode
import java.nio.charset.StandardCharsets
import kotlin.reflect.KMutableProperty
import kotlin.reflect.jvm.kotlinProperty

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
@Suppress("unused")
abstract class BaseQuestion {

    var id: Long? = null
    var answer: Int = 0
    var mediaType: Boolean? = null
    var optionA: String? = null
    var optionB: String? = null
    var optionC: String? = null
    var optionD: String? = null
    var optionE: String? = null
    var optionF: String? = null
    var optionG: String? = null
    var optionH: String? = null
    var optionType: Int? = null

    @Decode
    var question: ByteArray? = null

    @Decode
    var explain: ByteArray? = null

    @Decode
    var illiteracyExplain: ByteArray? = null

    @Decode
    var knackDetail: ByteArray? = null

    fun getDecodedAnswer(): Char {
        return Char((answer / 16 + 64).toUShort())
    }

    fun getDecodedField(field: KMutableProperty<ByteArray?>): String {
        return decode(field.getter.call())
    }

    override fun toString(): String {
        val clazz = this::class
        val sb = StringBuilder("${clazz.simpleName}(")
        for (member in clazz.java.declaredFields) {
            sb.append(member.name).append("=")
            if (member.isAnnotationPresent(Decode::class.java) && member.type.isArray) {
                member.kotlinProperty?.let { property ->
                    // TODO: 2022-05-08-0008 Mr.X 处理其它可能的类型, 虽然大概不会有其它类型
                    member.getAnnotation(Decode::class.java).targetType.let {
                        if (it == String::class) {
                            sb.append(decode(property.getter.call(this) as ByteArray?))
                        }
                    }
                }
            } else {
                member.kotlinProperty?.let {
                    sb.append("${it.getter.call(this)}".ifBlank { "null" })
                }
            }
            sb.append(", ")
        }
        sb.deleteRange(sb.lastIndex - 1, sb.lastIndex + 1).append(")")
        return sb.toString()
    }

    fun decode(paramArrayOfByte: ByteArray?): String {
        if (paramArrayOfByte == null) {
            return "null"
        }
        val arrayOfByte = "_jiakaobaodian.com_".toByteArray(StandardCharsets.UTF_8)
        for (i in paramArrayOfByte.indices) {
            paramArrayOfByte[i] = (paramArrayOfByte[i].toInt() xor arrayOfByte[i % arrayOfByte.size].toInt()).toByte()
        }
        return String(paramArrayOfByte, StandardCharsets.UTF_8)
    }

}