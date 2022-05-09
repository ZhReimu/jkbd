package com.mrx.jkbd.entity

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
class DTOQuestion : BaseQuestion() {

    fun getStringQuestion(id: Int): String {
        val decodedQuestion = getDecodedField(this::question)
        val sb = StringBuilder("第 $id 题: $decodedQuestion\n")
        sb.append("A: $optionA\n").append("B: $optionB\n")
        // optionType 0 为 判断
        return if (optionType == 0) {
            sb.toString()
        } else {
            optionC?.let { sb.append("C: $it\n") }
            optionD?.let { sb.append("D: $it\n") }
            optionE?.let { sb.append("E: $it\n") }
            optionF?.let { sb.append("F: $it\n") }
            optionG?.let { sb.append("G: $it\n") }
            sb.toString()
        }
    }

}