package com.mrx.mybatis.mapper

import com.mrx.jkbd.entity.Question
import org.apache.ibatis.annotations.Param

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
interface QuestionMapper {

    fun getQuestionById(@Param("qid") qid: Long): Question

}