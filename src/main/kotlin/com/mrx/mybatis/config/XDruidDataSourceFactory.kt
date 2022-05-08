package com.mrx.mybatis.config

import com.alibaba.druid.pool.DruidDataSource
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
class XDruidDataSourceFactory : UnpooledDataSourceFactory() {

    init {
        dataSource = DruidDataSource()
    }

}