package com.mrx.mybatis.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.mrx.jkbd.entity.DecodedQuestion
import com.mrx.mybatis.mapper.DecodeQuestionMapper
import com.mrx.mybatis.service.IDecQuestionService

/**
 * @author Mr.X
 * @since 2022-05-09-0009
 **/
class DecQuestionServiceImpl(bMapper: DecodeQuestionMapper) : ServiceImpl<DecodeQuestionMapper, DecodedQuestion>(),
    IDecQuestionService {

    init {
        baseMapper = bMapper
    }

}