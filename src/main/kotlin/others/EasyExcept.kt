package others

fun main() {

    // 에러처리 안함
    SafeHandler({
        val sum = 1 / 0
    })

    // 에러처리 함
    SafeHandler({
        var name : String? = null
        print (name!!.length)
    }, {
        e-> print("👨‍✈️ ${e} 입니다")
    })
}

fun SafeHandler( fnCode : () -> Unit, fnError : ( (String) -> Unit)? = null  ){
    try{
        fnCode()
    } catch ( e: Exception){
        if (fnError != null ) {
            fnError(e.toString())
        } else {
            // [TODO] 디폴트로 하고자 하는 기능
        }
    }
}