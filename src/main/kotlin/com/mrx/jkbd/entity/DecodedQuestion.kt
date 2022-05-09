package com.mrx.jkbd.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

/**
 * @author Mr.X
 * @since 2022-05-09-0009
 **/
@TableName("t_question")
@Suppress("unused")
class DecodedQuestion {

    @TableId("_id")
    var id: Long? = null
    var answer: Char? = null
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
    var questionId: Long? = null
    var chapterId: Long? = null
    var label: String? = null
    var mediaKey: String? = null
    var difficulty: Long? = null
    var wrongRate: Double? = null
    var M: Long? = null
    var sort: Long? = null
    var supreme: Long? = null


    var conciseExplain: String? = null
    var keywords: String? = null
    var assuredKeywords: String? = null
    var illiteracyKeywords: String? = null
    var knackKeyword: String? = null
    var knackImgUrl: String? = null
    var knackVoiceTxt: String? = null
    var question: String? = null
    var explain: String? = null
    var illiteracyExplain: String? = null
    var knackDetail: String? = null

    override fun toString(): String {
        return "DecodedQuestion(id=$id, answer=$answer, mediaType=$mediaType, optionA=$optionA, optionB=$optionB, optionC=$optionC, optionD=$optionD, optionE=$optionE, optionF=$optionF, optionG=$optionG, optionH=$optionH, optionType=$optionType, questionId=$questionId, chapterId=$chapterId, label=$label, mediaKey=$mediaKey, difficulty=$difficulty, wrongRate=$wrongRate, M=$M, sort=$sort, supreme=$supreme, conciseExplain=$conciseExplain, keywords=$keywords, assuredKeywords=$assuredKeywords, illiteracyKeywords=$illiteracyKeywords, knackKeyword=$knackKeyword, knackImgUrl=$knackImgUrl, knackVoiceTxt=$knackVoiceTxt, question=$question, explain=$explain, illiteracyExplain=$illiteracyExplain, knackDetail=$knackDetail)"
    }

}