package com.mrx.mybatis

import com.mrx.mybatis.interfaces.Decode
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.nio.charset.StandardCharsets

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
@Deprecated(message = "有问题, 暂时不用", level = DeprecationLevel.ERROR)
@Suppress("unused")
class ReflectUtil constructor(private val obj: Any) : InvocationHandler {

    @Suppress("unchecked_cast")
    fun <E> createProxyTQuestion(): E {
        return Proxy.newProxyInstance(
            obj.javaClass.classLoader,
            obj.javaClass.interfaces, this
        ) as E
    }

    private fun decode(paramArrayOfByte: ByteArray?): String {
        if (paramArrayOfByte == null) {
            return "null"
        }
        val arrayOfByte = "_jiakaobaodian.com_".toByteArray(StandardCharsets.UTF_8)
        for (i in paramArrayOfByte.indices) {
            paramArrayOfByte[i] = (paramArrayOfByte[i].toInt() xor arrayOfByte[i % arrayOfByte.size].toInt()).toByte()
        }
        return String(paramArrayOfByte, StandardCharsets.UTF_8)
    }

    override fun invoke(proxy: Any?, method: Method?, args: Array<Any?>?): Any? {
        method ?: return null
        if (method.isAnnotationPresent(Decode::class.java)) {
            if (args == null) return decode(method.invoke(proxy) as ByteArray)
            return decode(method.invoke(proxy, *args) as ByteArray)
        } else if (args == null) {
            return method.invoke(proxy)
        } else {
            return method.invoke(proxy, *args)
        }
    }

}