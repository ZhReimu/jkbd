package com.mrx.mybatis.mapper

import com.mrx.jkbd.entity.DTOQuestion
import com.mrx.jkbd.entity.Question
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Select

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
interface QuestionMapper {

    @Select("SELECT *FROM t_question WHERE question_id = #{qid}")
    @ResultMap("com.mrx.mybatis.mapper.QuestionMapper.questionMap")
    fun getQuestionByQID(@Param("qid") qid: Long): Question

    @Select("SELECT *FROM t_question WHERE _id = #{id}")
    @ResultMap("com.mrx.mybatis.mapper.QuestionMapper.questionMap")
    fun getQuestionByID(@Param("id") id: Long): Question?

    @Select("SELECT *FROM t_question WHERE _id BETWEEN #{start} AND #{end}")
    @ResultMap("com.mrx.mybatis.mapper.QuestionMapper.questionMap")
    fun getQuestionsByRange(@Param("start") start: Long, @Param("end") end: Long): List<DTOQuestion>

}