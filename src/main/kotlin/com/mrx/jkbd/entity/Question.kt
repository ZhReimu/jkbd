package com.mrx.jkbd.entity

import com.mrx.mybatis.interfaces.Decode
import com.mrx.mybatis.util.ReflectUtil
import java.io.Serializable
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

    @Decode
    var question: ByteArray? = null

    @Decode
    var explain: ByteArray? = null

    @Decode
    var conciseExplain: ByteArray? = null

    @Decode
    var keywords: ByteArray? = null

    @Decode
    var assuredKeywords: ByteArray? = null

    @Decode
    var illiteracyExplain: ByteArray? = null

    @Decode
    var illiteracyKeywords: ByteArray? = null

    @Decode
    var knackKeyword: ByteArray? = null

    @Decode
    var knackImgUrl: ByteArray? = null

    @Decode
    var knackDetail: ByteArray? = null

    @Decode
    var knackVoiceTxt: ByteArray? = null

    fun getDecodedAnswer(): Char {
        return Char((answer / 16 + 64).toUShort())
    }

    fun getDecodedField(field: KMutableProperty<ByteArray?>): String {
        return ReflectUtil.decode(field.getter.call())
    }

}