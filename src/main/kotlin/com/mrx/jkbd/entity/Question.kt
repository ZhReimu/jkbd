package com.mrx.jkbd.entity

import com.mrx.mybatis.interfaces.Decode

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
@Suppress("unused")
class Question : BaseQuestion() {

    var questionId: Long = 0
    var chapterId: Long = 0
    var label: String? = null
    var mediaKey: String? = null
    var difficulty: Long = 0
    var wrongRate = 0.0
    var M: Long = 0
    var sort: Long = 0
    var supreme: Long = 0

    @Decode
    var conciseExplain: ByteArray? = null

    @Decode
    var keywords: ByteArray? = null

    @Decode
    var assuredKeywords: ByteArray? = null

    @Decode
    var illiteracyKeywords: ByteArray? = null

    @Decode
    var knackKeyword: ByteArray? = null

    @Decode
    var knackImgUrl: ByteArray? = null

    @Decode
    var knackVoiceTxt: ByteArray? = null

}