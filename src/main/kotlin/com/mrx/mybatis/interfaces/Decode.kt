package com.mrx.mybatis.interfaces

import kotlin.reflect.KClass

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Decode(val targetType: KClass<*> = String::class)