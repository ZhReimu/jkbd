package com.mrx.jkbd.entity

import com.mrx.mybatis.interfaces.Decode
import org.apache.commons.beanutils.BeanUtils
import java.lang.reflect.Field
import java.nio.charset.StandardCharsets
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.superclasses
import kotlin.reflect.jvm.kotlinProperty

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
@Suppress("unused")
abstract class BaseQuestion {

    var id: Long? = null

    @Decode(isAnswer = true)
    var answer: Int = 0
    var mediaType: Int? = null
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

    inline fun <reified T : BaseQuestion> toOtherQuestion(): T = T::class.java.getConstructor().newInstance().apply {
        BeanUtils.copyProperties(this, this@BaseQuestion)
    }

    override fun toString(): String {
        val clazz = this::class
        val sb = StringBuilder("${clazz.simpleName}(")
        val fields = getDeclaredFields(clazz)
        for (member in fields) {
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
        if (fields.isNotEmpty()) {
            sb.deleteRange(sb.lastIndex - 1, sb.lastIndex + 1)
        }
        sb.append(")")
        return sb.toString()
    }

    protected fun decode(encBytes: ByteArray?): String {
        if (encBytes == null) {
            return "null"
        }
        val key = "_jiakaobaodian.com_".toByteArray(StandardCharsets.UTF_8)
        for (i in encBytes.indices) {
            encBytes[i] = (encBytes[i].toInt() xor key[i % key.size].toInt()).toByte()
        }
        return String(encBytes, StandardCharsets.UTF_8)
    }

    private fun getDeclaredFields(clazz: KClass<*>, depth: Int = 1): List<Field> {
        if (depth == 0) {
            return clazz.java.declaredFields.toList()
        }
        return ArrayList(getDeclaredFields(clazz.superclasses[0], depth - 1))
    }

}