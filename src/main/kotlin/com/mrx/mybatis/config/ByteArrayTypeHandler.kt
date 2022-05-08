package com.mrx.mybatis.config

import org.apache.ibatis.type.JdbcType
import org.apache.ibatis.type.TypeHandler
import org.slf4j.LoggerFactory
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
@Suppress("unused")
class ByteArrayTypeHandler : TypeHandler<ByteArray> {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun setParameter(ps: PreparedStatement, i: Int, parameter: ByteArray, jdbcType: JdbcType) {
        ps.setBytes(i, parameter)
    }

    override fun getResult(rs: ResultSet, columnName: String): ByteArray? {
        return rs.getBytes(columnName)
    }

    override fun getResult(rs: ResultSet, columnIndex: Int): ByteArray? {
        return rs.getBytes(columnIndex)
    }

    override fun getResult(cs: CallableStatement, columnIndex: Int): ByteArray? {
        return cs.getBytes(columnIndex)
    }

}