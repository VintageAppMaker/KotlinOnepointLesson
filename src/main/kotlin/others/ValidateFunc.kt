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

fun ValidateFunc.checkAll(block : (Boolean) ->Unit) {
    block(validate())
}

fun main() {
    val checkValid = ValidateFunc()

    val s = "123456"
    checkValid.add(
        chkSizeLimted(value= s, min=5, max = 12,
            onFail = { n -> println("$s <- $n is not allowed") })
    )

    checkValid.add(
        chkPattern(value= s, sPattern = "^[0-9]*\$",
            onFail = { println("$s <- 숫자가 아닙니다") })
    )

    println ("${s} 결과는 ${checkValid.validate()}")

    // DSL 처리
    ValidateDSL {
        Limited {
            value = s
            max   = 10
            min   = 8
            onFail = { n -> println("$s <- $n is not allowed")}
        }

        Regex {
            value    = s
            sPattern = "^[가-힣]*\$"
            onFail   = {println("$s <- 한글아님")}
        }

        checkAll{
            b -> println ("${s} 결과는 $b")
        }
    }
}