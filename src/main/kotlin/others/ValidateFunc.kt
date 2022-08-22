package others

import java.util.regex.Pattern

sealed class checkFunc
class ValidateFunc {
    var fnValidate : MutableList<checkFunc> = mutableListOf()

    fun add(item : checkFunc) = fnValidate.add(item)

    fun validate () : Boolean{
        var bRst = true
        fnValidate.forEach {
            when (it){
                is chkSizeLimted -> {
                    if(it.isTrue == false ) it.onFail(it.value.length)
                    bRst = bRst && it.isTrue
                }
                is  chkPattern -> {
                    if(it.isTrue == false ) it.onFail()
                    bRst = bRst && it.isTrue
                }
            }
        }

        fnValidate.clear()
        return bRst
    }
}

data class chkSizeLimted (
    var value   : String = "",
    var onFail  : (Int) -> Unit ={},
    var max : Int = 0,
    var min  : Int = 0) : checkFunc(){
        var isTrue : Boolean = false
        get (){ if (value.length > min && value.length <max) return true else return false }
}

data class chkPattern (
    var value   : String = "",
    var onFail  : () -> Unit = {},
    var sPattern : String = "") : checkFunc(){
    var isTrue : Boolean = false
        get (){ if ( Pattern.matches(sPattern, value ) ) return true else return false }
}

// DSL
fun ValidateDSL(block : ValidateFunc.() ->Unit) = ValidateFunc().apply(block)
fun ValidateFunc.Limited(block : chkSizeLimted.() ->Unit) {
    add(chkSizeLimted().apply(block))
}

fun ValidateFunc.Regex(block : chkPattern.() ->Unit) {
    add(chkPattern().apply(block))
}

fun ValidateFunc.checkAll(block : ValidateFunc.(Boolean) ->Unit) {
    block(validate())
}

fun main() {
    val checkValid = ValidateFunc()

    val sTest1  = "123456"
    val sTest2  = "움하하하"

    checkValid.add(
        chkSizeLimted(value= sTest1, min=5, max = 12,
            onFail = { n -> println("$sTest1 <- $n is not allowed") })
    )

    checkValid.add(
        chkPattern(value= sTest1, sPattern = "^[0-9]*\$",
            onFail = { println("$sTest1 <- 숫자가 아닙니다") })
    )

    println ("${sTest1} 결과는 ${checkValid.validate()}")

    // DSL 처리
    ValidateDSL {
        Limited {
            value = sTest1
            max   = 10
            min   = 8
            onFail = { n -> println("$sTest1 <- $n is not allowed")}
        }

        Regex {
            value    = sTest1
            sPattern = "^[가-힣]*\$"
            onFail   = {println("$sTest1 <- 한글아님")}
        }

        Regex {
            value    = sTest2
            sPattern = "^[0-9]*\$"
            onFail   = {println("$sTest2 <- 숫자가 아님")}
        }

        checkAll{
            b -> println ("ValidateDSL{} 테스트 결과는 $b")
        }
    }
}