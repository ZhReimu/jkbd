package com.mrx.mybatis.config

import com.baomidou.mybatisplus.core.MybatisConfiguration
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder
import com.baomidou.mybatisplus.core.config.GlobalConfig
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import java.io.InputStream


/**
 * 配置 Mybatis
 */
object MybatisUtil {

    private val insDev: InputStream = this::class.java.getResourceAsStream("/mybatis-config.xml")!!

    private val insEnv: InputStream = this::class.java.getResourceAsStream("/mybatis-config.xml")!!

    private val devEnv: SqlSessionFactory = SqlSessionFactoryBuilder().build(insDev, "dev")

    private val decEnv: SqlSessionFactory = SqlSessionFactoryBuilder().build(insEnv, "dec")

    fun <T> getMapper(mapperClazz: Class<T>): T {
        return devEnv.configuration.getMapper(mapperClazz, devEnv.openSession(true))
    }

    fun <T> getMapperPlus(clazz: Class<T>): T {
        val configuration = MybatisConfiguration(decEnv.configuration.environment).apply {
            addMapper(clazz)
        }
        with(GlobalConfig()) {
            sqlInjector = DefaultSqlInjector()
            identifierGenerator = DefaultIdentifierGenerator()
            superMapperClass = BaseMapper::class.java
            GlobalConfigUtils.setGlobalConfig(configuration, this)
        }
        val session = MybatisSqlSessionFactoryBuilder().build(configuration)
        return session.openSession(true).getMapper(clazz)
    }

}