package com.mrx.jkbd.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

/**
 * @author Mr.X
 * @since 2022-05-09-0009
 **/
@TableName("t_question")
class DecodedQuestion {
    @TableId("_id")
    var id: Long? = null
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
    var questionId: Long? = null
    var chapterId: Long? = null
    var label: String? = null
    var mediaKey: String? = null
    var difficulty: Long? = null
    var wrongRate = 0.0
    var M: Long = 0
    var sort: Long = 0
    var supreme: Long = 0


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

    fun getStringQuestion(id: Int): String {
        val sb = StringBuilder("第 $id 题: $question\n")
        sb.append("A: $optionA\n").append("B: $optionB\n")
        // optionType 0 为 判断
        return if (optionType == 0) {
            sb.toString()
        } else {
            optionC?.let { if (it.isNotEmpty()) sb.append("C: $it\n") }
            optionD?.let { if (it.isNotEmpty()) sb.append("D: $it\n") }
            optionE?.let { if (it.isNotEmpty()) sb.append("E: $it\n") }
            optionF?.let { if (it.isNotEmpty()) sb.append("F: $it\n") }
            optionG?.let { if (it.isNotEmpty()) sb.append("G: $it\n") }
            sb.toString()
        }
    }
}