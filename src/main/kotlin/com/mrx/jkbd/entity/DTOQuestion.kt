package com.mrx.jkbd.entity

import com.mrx.mybatis.interfaces.Decode
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.kotlinProperty

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
class DTOQuestion : BaseQuestion() {

    fun getStringQuestion(id: Int): String {
        val decodedQuestion = getDecodedField(this::question)
        val sb = StringBuilder("第 $id 题: $decodedQuestion\n")
        sb.append("A: $optionA\n").append("B: $optionB\n")
        // optionType 0 为 判断
        return if (optionType == 0) {
            sb.toString()
        } else {
            optionC?.let { if (it.isNotEmpty()) sb.append("C: $it\n") }
            optionD?.let { if (it.isNotEmpty()) sb.append("D: $it\n") }
            optionE?.let { if (it.isNotEmpty()) sb.append("E: $it\n") }
            optionF?.let { if (it.isNotEmpty()) sb.append("F: $it\n") }
            optionG?.let { if (it.isNotEmpty()) sb.append("G: $it\n") }
            sb.toString()
        }
    }

    fun toDecodedQuestion(): DecodedQuestion {
        val decodedQuestion = DecodedQuestion()
        val decodeQClazz = decodedQuestion::class
        getDeclaredFields(this::class).forEach { member ->
            member.kotlinProperty?.let { property ->
                // 如果是需要解码的 成员变量
                if (member.isAnnotationPresent(Decode::class.java) && member.type.isArray) {
                    // TODO: 2022-05-08-0008 Mr.X 处理其它可能的类型, 虽然大概不会有其它类型
                    member.getAnnotation(Decode::class.java).targetType.let {
                        if (it == String::class) {
                            for (deq in decodeQClazz.memberProperties) {
                                if (deq.name == property.name) {
                                    deq.isAccessible = true
                                    deq.javaField?.set(
                                        decodedQuestion,
                                        decode(property.getter.call(this) as ByteArray?)
                                    )
                                    break
                                }
                            }
                        }
                    }
                } else {
                    for (deq in decodeQClazz.memberProperties) {
                        if (deq.name == property.name) {
                            deq.isAccessible = true
                            deq.javaField?.set(
                                decodedQuestion,
                                property.getter.call(this)
                            )
                            break
                        }
                    }
                }
            }
        }
        return decodedQuestion
    }

}