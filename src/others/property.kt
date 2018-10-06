package others

/**
 * Created by park on 2017-12-31.
 */
var name : String = "박모씨"
    get(){
        if(field.length > 5) {
            field =  "외우기 힘듬"
        }
        return field
    }
    set(s : String ){
        println ("\"${s}\"이 입력됨.")
        field = s
    }

// 기존 클래스에 filed를 추가할 수 없지만
// Properites는 추가가능하다.
// 그러나 확장함수보다는 활용성이 무척적다.

class EmptyClass {
    var message : String = ""
}
var EmptyClass?.newProp : String
        get(){
            return this!!.message
        }
        set(value) { this!!.message = value + "-" + this!!.message}

// MutableList에 first 프로퍼티 추가
var MutableList<String>?.first : String
    get() {return this!!.get(0)}
    set(value) {this!!.add(0, value)}

fun main(args : Array<String> ){
    name = "동작구 에미넴 #2"
    println (name)

    // 내가만든 클래스에 확장 프로퍼티 추가
    val empty = EmptyClass()
    empty.newProp = "안녕"
    empty.newProp = "반가와요"
    empty.newProp = "새로운 클래스"
    println (empty.newProp)

    // mutableList에 확장 프로퍼티 추가
    val mList = mutableListOf<String>("나는", "코틀린", "입니다")
    println(mList)
    mList.first = "너는"
    println(mList)
    println(mList.first)

}
