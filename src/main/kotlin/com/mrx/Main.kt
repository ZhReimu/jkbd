package com.mrx

import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme
import com.mrx.jkbd.entity.DecodedQuestion
import com.mrx.jkbd.ui.UI
import com.mrx.mybatis.config.MybatisUtil
import com.mrx.mybatis.mapper.DecodeQuestionMapper
import com.mrx.mybatis.mapper.QuestionMapper
import com.mrx.mybatis.service.impl.DecQuestionServiceImpl
import org.slf4j.LoggerFactory
import java.awt.EventQueue
import kotlin.random.Random

@Suppress("unused")
object Main {

    private val logger = LoggerFactory.getLogger(this::class.java)

    private val decMapper by lazy { MybatisUtil.getMapperPlus(DecodeQuestionMapper::class.java) }

    private val encMapper by lazy { MybatisUtil.getMapper(QuestionMapper::class.java) }

    private val dqs by lazy { DecQuestionServiceImpl(decMapper) }

    @JvmStatic
    fun main(args: Array<String>) {
        logger.debug("准备初始化试题")
        val questions = getRandomQuestions()
        logger.debug("初始化题目完成!")
        FlatDarkFlatIJTheme.setup()
        EventQueue.invokeLater {
            UI(questions).isVisible = true
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