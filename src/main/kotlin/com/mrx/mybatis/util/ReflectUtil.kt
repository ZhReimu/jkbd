package com.mrx.mybatis.util

import com.mrx.mybatis.interfaces.Decode
import java.nio.charset.StandardCharsets
import kotlin.reflect.jvm.kotlinProperty

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
object ReflectUtil {

    fun obj2String(obj: Any): String {
        val clazz = obj::class
        val sb = StringBuilder("${clazz.simpleName}(")
        for (member in clazz.java.declaredFields) {
            sb.append(member.name).append("=")
            if (member.isAnnotationPresent(Decode::class.java) && member.type.isArray) {
                member.kotlinProperty?.let { property ->
                    // TODO: 2022-05-08-0008 Mr.X 处理其它可能的类型, 虽然大概不会有其它类型
                    member.getAnnotation(Decode::class.java).targetType.let {
                        if (it == String::class) {
                            sb.append(decode(property.getter.call(obj) as ByteArray?))
                        }
                    }
                }
            } else {
                member.kotlinProperty?.let {
                    sb.append("${it.getter.call(obj)}".ifBlank { "null" })
                }
            }
            sb.append(", ")
        }
        sb.deleteRange(sb.lastIndex - 1, sb.lastIndex + 1).append(")")
        return sb.toString()
    }

    fun decode(paramArrayOfByte: ByteArray?): String {
        if (paramArrayOfByte == null) {
            return "null"
        }
        val arrayOfByte = "_jiakaobaodian.com_".toByteArray(StandardCharsets.UTF_8)
        for (i in paramArrayOfByte.indices) {
            paramArrayOfByte[i] = (paramArrayOfByte[i].toInt() xor arrayOfByte[i % arrayOfByte.size].toInt()).toByte()
        }
        return String(paramArrayOfByte, StandardCharsets.UTF_8)
    }

}