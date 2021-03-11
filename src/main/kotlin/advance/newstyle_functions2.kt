package advance

// let은 연산된 값으로 return 할 수 있고
// also는 파라메터로 받은 값을 그대로 return 한다.
fun main(args: Array<String>) {
    normalStyle()
    funcionalStyle()
}

private fun normalStyle() {
    val animals = "고양이 까치 개 쥐 독수리"
    val lstAnimals = animals.split(" ")
    println("-------------");
    println("log 출력:")
    lstAnimals.forEach { println(it) }
    println("-------------");

    val nIndx = lstAnimals.indexOf("독수리")
    if (nIndx > 0) {
        println("독수리는 ${nIndx} index에 있습니다.")
    }
}

// 필요한 변수는 함수내부에서만 쓰고
// if문을 최소화 한다
// "변수와 if문을 최소화하여 예외를 없앤다". ← 함수형 프로그래밍적 사고
private fun funcionalStyle() {
    var animals = "고양이 까치 개 쥐 독수리"
    animals.split(" ")
            .also { items ->
                println("-------------");
                println("log 출력:")
                items.forEach { println(it) }
                println("-------------");
            }
            .let { items ->
                var indx = items.indexOf("독수리")
                if (indx < 0) null else indx
            }
            ?.let { println("독수리는 ${it} index에 있습니다.") }
}