package com.mrx.jkbd.entity

import java.io.Serializable
import java.nio.charset.StandardCharsets
import kotlin.reflect.KMutableProperty

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
@Suppress("unused")
class Question : Serializable {

    var id: Long = 0
    var questionId: Long = 0
    var mediaType: Long = 0
    var chapterId: Long = 0
    var label: String? = null
    var mediaKey: String? = null
    var answer: Long = 0
    var optionA: String? = null
    var optionB: String? = null
    var optionC: String? = null
    var optionD: String? = null
    var optionE: String? = null
    var optionF: String? = null
    var optionG: String? = null
    var optionH: String? = null
    var difficulty: Long = 0
    var wrongRate = 0.0
    var optionType: Long = 0
    var M: Long = 0
    var sort: Long = 0
    var supreme: Long = 0
    var question: ByteArray? = null
    var explain: ByteArray? = null
    var conciseExplain: ByteArray? = null
    var keywords: ByteArray? = null
    var assuredKeywords: ByteArray? = null
    var illiteracyExplain: ByteArray? = null
    var illiteracyKeywords: ByteArray? = null
    var knackKeyword: ByteArray? = null
    var knackImgUrl: ByteArray? = null
    var knackDetail: ByteArray? = null
    var knackVoiceTxt: ByteArray? = null

    fun getDecodedAnswer(): Char {
        return Char((answer / 16 + 64).toUShort())
    }

    fun getDecodedField(field: KMutableProperty<ByteArray?>): String {
        return decode(field.getter.call())
    }

    private fun decode(paramArrayOfByte: ByteArray?): String {
        if (paramArrayOfByte == null) {
            return "null"
        }
        val arrayOfByte = "_jiakaobaodian.com_".toByteArray(StandardCharsets.UTF_8)
        for (i in paramArrayOfByte.indices) {
            paramArrayOfByte[i] = (paramArrayOfByte[i].toInt() xor arrayOfByte[i % arrayOfByte.size].toInt()).toByte()
        }
        return String(paramArrayOfByte, StandardCharsets.UTF_8)
    }

    override fun toString(): String {
        val sb = StringBuilder("Question(")
        for (member in this::class.java.declaredFields) {
            sb.append(member.name).append("=")
            if (member.type.isArray) {
                sb.append(decode(member.get(this) as ByteArray?))
            } else {
                sb.append("${member.get(this)}".ifBlank { "null" })
            }
            sb.append(", ")
        }
        sb.deleteRange(sb.lastIndex - 1, sb.lastIndex + 1).append(")")
        return sb.toString()
    }


}