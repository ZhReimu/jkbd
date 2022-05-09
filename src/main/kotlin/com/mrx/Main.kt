package com.mrx

import com.mrx.jkbd.entity.DTOQuestion
import com.mrx.mybatis.config.MybatisUtil
import com.mrx.mybatis.mapper.DecodeQuestionMapper
import com.mrx.mybatis.mapper.QuestionMapper
import kotlin.random.Random

@Suppress("unused")
object Main {

    private val decMapper by lazy { MybatisUtil.getMapperPlus(DecodeQuestionMapper::class.java) }

    private val encMapper by lazy { MybatisUtil.getMapper(QuestionMapper::class.java) }

    @JvmStatic
    fun main(args: Array<String>) {
//        EventQueue.invokeLater {
//            UI(getRandomQuestions(10)).isVisible = true
//        }
//        val dqs = DecQuestionServiceImpl(decMapper)
        val res = encMapper.getQuestionByID(17649)?.toDecodedQuestion()
        println(res)
    }

    private fun getRandomQuestions(num: Int): List<DTOQuestion> {
        val rad = Random(System.currentTimeMillis())
        val radLong = rad.nextLong(17649, 85338 + 1)
        // TODO: 2022-05-09-0009 Mr.X 实现随机出题
        val res = encMapper.getQuestionsByRange(radLong, radLong + num)
        return if (res.size < num) getRandomQuestions(num) else res
    }

}