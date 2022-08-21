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
    var value   : String,
    var onFail  : (Int) -> Unit,
    var max : Int,
    var min  : Int) : checkFunc(){
        var isTrue : Boolean = false
        get (){ if (value.length > min && value.length <max) return true else return false }
}

data class chkPattern (
    var value   : String,
    var onFail  : () -> Unit,
    var sPattern : String) : checkFunc(){
    var isTrue : Boolean = false
        get (){ if ( Pattern.matches(sPattern, value ) ) return true else return false }
}


fun main() {
    val words = listOf("한글이지만짧다", "한글이면서여덟글자넘는", "한글이면서여덟글자넘고숫자1")
    val checkValid = ValidateFunc()

    words.forEach {
        checkValid.add(
            chkSizeLimted(value = "$it", onFail = { nLen -> println ("$it <-  ${nLen} size is not allowed")}, max=12, min= 8)
        )

        checkValid.add(
            chkPattern(value= it, sPattern = "^[가-힣]*\$",
                onFail = { println("$it <- 한글이 아닙니다") })
        )

    }

    println ("${words.toString()} 결과는 ${checkValid.validate()}")

    val s = "123456"
    checkValid.add(
        chkSizeLimted(value= s, min=5, max = 12,
            onFail = { n -> println("$s <- $n is not alloewed") })
    )

    checkValid.add(
        chkPattern(value= s, sPattern = "^[0-9]*\$",
            onFail = { println("$s <- 숫자가 아닙니다") })
    )

    println ("${s} 결과는 ${checkValid.validate()}")

}