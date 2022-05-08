package com.mrx

import com.mrx.jkbd.ui.UI
import java.awt.EventQueue

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
//        val mapper = MybatisUtil.getMapper(QuestionMapper::class.java)
//        val res = mapper.getQuestionById(800100).toOtherQuestion<DTOQuestion>()
//        println(res.toString())
        EventQueue.invokeLater {
            UI().isVisible = true
        }
    }

}