package com.mrx.mybatis.config

import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import java.io.InputStream


/**
 * 配置 Mybatis
 */
object MybatisUtil {

    private val ins: InputStream = this::class.java.getResourceAsStream("/mybatis-config.xml")!!

    //定义默认 environment
    private val env: SqlSessionFactory = SqlSessionFactoryBuilder().build(ins)

    fun <T> getMapper(mapperClazz: Class<T>): T {

        return env.configuration.getMapper(mapperClazz, env.openSession())
    }

}