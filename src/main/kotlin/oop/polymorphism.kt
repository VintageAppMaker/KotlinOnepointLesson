package oop

/**
 * Created by snake on 17. 5. 22.
 */
fun main(args : Array<String>){
    var obj1 = baseClass();
    obj1.func1();

    var obj2 = childClass();
    obj2.func1();
    obj2.func2();
    obj2.func2("문자열 파라메터");
    obj2.func2("문자열 파라메터", 100);
}

open class baseClass{
    // 상속받은 클래스에서 오버라이드하려면 선조클래스에서 open으로 정의.
    open var name = "base";
    open fun func1() = println(this.toString());

    // 외부사용금지 .찍고 메소드 사용못함.
    private fun onlyMyFunc() = println("클래스내부에서만 사용");
    constructor(){
        onlyMyFunc();
    }
}

class childClass : baseClass(){
    override var name = "";
    override fun func1() = println(this.toString() + " 재정의함.");
    fun func2() = println("func2");

    // overloding
    fun func2(s : String ) = println("func2:$s ");
    fun func2(s : String, num : Int ) = println("func2: $s, $num  ");
}