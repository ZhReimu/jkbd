package com.mrx

import com.mrx.jkbd.entity.DTOQuestion
import com.mrx.jkbd.ui.UI
import com.mrx.mybatis.config.MybatisUtil
import com.mrx.mybatis.mapper.QuestionMapper
import java.awt.EventQueue
import kotlin.random.Random

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        EventQueue.invokeLater {
            UI(getRandomQuestions(10)).isVisible = true
        }
    }

    private fun getRandomQuestions(num: Int): List<DTOQuestion> {
        val mapper = MybatisUtil.getMapper(QuestionMapper::class.java)
        val rad = Random(System.currentTimeMillis())
        val radLong = rad.nextLong(17649, 85338 + 1)
        // TODO: 2022-05-09-0009 Mr.X 实现随机出题
        val res = mapper.getQuestionsByRange(radLong, radLong + num)
        return if (res.size < num) getRandomQuestions(num) else res
    }

}