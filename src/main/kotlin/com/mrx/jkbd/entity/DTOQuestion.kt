package com.mrx.jkbd.entity

import com.mrx.mybatis.interfaces.Decode

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
@Suppress("unused")
class DTOQuestion : BaseQuestion() {
    var id: Long? = null
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

}