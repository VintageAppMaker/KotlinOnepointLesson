package advance
/**
 * Created by snake on 17. 5. 23.
 */
fun main(args : Array<String>){
    var sTestExtFunc : String  = "일반 문자열"
    println (
        sTestExtFunc.myFunction("myFunction")
    );

}

// 이미 만들어진 객체에 함수를 추가할 수 있다.
fun String?.myFunction(s : String ) : String{
    return "나는 $this 너는 $s"
}

