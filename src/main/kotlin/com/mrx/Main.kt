package com.mrx

import com.mrx.jkbd.entity.DecodedQuestion
import com.mrx.jkbd.ui.UI
import com.mrx.mybatis.config.MybatisUtil
import com.mrx.mybatis.mapper.DecodeQuestionMapper
import com.mrx.mybatis.mapper.QuestionMapper
import com.mrx.mybatis.service.impl.DecQuestionServiceImpl
import java.awt.EventQueue
import kotlin.random.Random

@Suppress("unused")
object Main {

    private val decMapper by lazy { MybatisUtil.getMapperPlus(DecodeQuestionMapper::class.java) }

    private val encMapper by lazy { MybatisUtil.getMapper(QuestionMapper::class.java) }

    private val dqs by lazy { DecQuestionServiceImpl(decMapper) }

    @JvmStatic
    fun main(args: Array<String>) {
        EventQueue.invokeLater {
            UI(getRandomQuestions()).isVisible = true
        }
    }

    private fun getRandomQuestions(num: Int = 10): List<DecodedQuestion> {
        val rad = Random(System.currentTimeMillis())
        val res = HashSet<DecodedQuestion>(num)
        while (res.size < num) {
            val radLong = rad.nextLong(17649, 85338 + 1)
            dqs.getById(radLong)?.let { res.add(it) }
        }
        return res.toList()
    }

}