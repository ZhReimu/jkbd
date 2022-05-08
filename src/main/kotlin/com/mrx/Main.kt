package com.mrx

import com.mrx.jkbd.entity.DTOQuestion
import com.mrx.mybatis.config.MybatisUtil
import com.mrx.mybatis.mapper.QuestionMapper

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val mapper = MybatisUtil.getMapper(QuestionMapper::class.java)
        val res = mapper.getQuestionById(800100).toOtherQuestion<DTOQuestion>()
        println(res.toString())
    }

}