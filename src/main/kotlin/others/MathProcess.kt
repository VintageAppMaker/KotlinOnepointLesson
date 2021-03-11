package others

import java.lang.Math.max
import java.lang.Math.random

fun main( args : Array<String>) {
    VedicAdd().process()
    VedicSub().process()
}

class VedicAdd{
    val op1 : Int by lazy { ( random() * 10000 ).toInt() }
    val op2 : Int by lazy { ( random() * 10000 ).toInt() }
    val sum : Int by lazy { op1 + op2 }
    val sArrayOp1 by lazy { op1.toString().let { s ->
        s.map {
            item -> "${item}".toInt()
        }.toMutableList()
    }}
    val sArrayOp2 by lazy{
        op2.toString().let { s ->
            s.map { item ->
                "${item}".toInt()
            }
        }.toMutableList()
    }
    val sSumArray by lazy{
        sum.toString().let { s ->
            s.map { item ->
                "${item}".toInt()
            }
        }.toMutableList()
    }

    var sumHistory : String = ""

    fun process (){
        showQuestion()
        makeDigitItem()
        (0..sArrayOp1.size - 1).forEachIndexed { index, i ->
            solveLineAdd(index)
        }

        println(sSumArray)
    }

    private fun solveLineAdd(nSelected : Int) {
        print(" ")
        sArrayOp1.forEachIndexed { index, i ->
            if (nSelected == index)
                print("[$i]")
            else
                print(i)
        }
        println ("")
        print("+")
        sArrayOp2.forEachIndexed { index, i ->
            if (nSelected == index)
                print("[$i]")
            else
                print(i)
        }
        println ("")
        println ("___________")

        sumHistory += makeSpaceAndSum(nSelected,sArrayOp1[nSelected], sArrayOp2[nSelected ] ) + "\n"
        println (sumHistory)
    }

    private fun makeSpaceAndSum(nSelected: Int, num1 : Int, num2: Int) : String{
        // 계산값 넣기
        var space = ""
        val sub = if( num1 + num2  > 9) 1 else 0
        for(i  in (0..nSelected - sub) ){
            space += " "
        }
        return "${space}${num1 + num2}"
    }

    private fun makeDigitItem() {
        val nCount = max(sArrayOp1.size, sArrayOp2.size)
        if (sArrayOp1.size < nCount){
            (nCount - sArrayOp1.size).let {
                (0..it -1 ).forEach { sArrayOp1.add(0,0) }
            }
        }

        if (sArrayOp2.size < nCount){
            (nCount - sArrayOp2.size).let {
                (0..it -1 ).forEach { sArrayOp2.add(0,0) }
            }
        }
    }

    private fun showQuestion() {
        println("\n")
        print(" ")
        sArrayOp1.forEach { print(it) }
        print("+")
        sArrayOp2.forEach { print(it) }
        println("=?\n")
    }
}

class VedicSub{
    var op1 : Int = 0
    var op2 : Int = 0
    val sub : Int by lazy { op1 - op2 }
    val sArrayOp1 by lazy { op1.toString().let { s ->
        s.map {
            item -> "${item}".toInt()
        }.toMutableList()
    }}
    val sArrayOp2 by lazy{
        op2.toString().let { s ->
            s.map { item ->
                "${item}".toInt()
            }
        }.toMutableList()
    }
    val sSumArray by lazy{
        sub.toString().let { s ->
            s.map { item ->
                "${item}".toInt()
            }
        }.toMutableList()
    }

    var subHistory : String = ""

    fun process (){
        initOperand()
        showQuestion()
        makeDigitItem()
        (0..sArrayOp1.size - 1).forEachIndexed { index, i ->
            println ("")
            solveLineSub(index)
        }

        println(sSumArray)
    }

    private fun initOperand() {
        op1 =  ( random() * 10000 ).toInt()
        op2 =  ( random() * 10000 ).toInt()
        if(op1 < op2){
            val temp = op1
            op1 = op2
            op2 = temp
        }
    }

    private fun solveLineSub(nSelected : Int) {
        print(" ")
        sArrayOp1.forEachIndexed { index, i ->
            if (nSelected == index)
                print("[$i]")
            else
                print(i)
        }
        println ("")
        print("-")
        sArrayOp2.forEachIndexed { index, i ->
            if (nSelected == index)
                print("[$i]")
            else
                print(i)
        }
        println ("")
        println ("___________")

        subHistory += makeSpaceAndSub(nSelected,sArrayOp1[nSelected], sArrayOp2[nSelected ] ) + "\n"
        println (subHistory)
    }

    private fun makeSpaceAndSub(nSelected: Int, num1 : Int, num2: Int) : String{
        // 계산값 넣기
        var space = " "
        val cal  = if ( num1 - num2 > -1  ) num1 - num2 else ( num1 + (10 - num2) ) + 10
        val sub  = if ( num1 - num2 > -1  ) 0 else 1

        for(i  in (0..nSelected - 1 - sub ) ){
            space += " "
        }

        return "${space}${cal}"
    }

    private fun makeDigitItem() {
        val nCount = max(sArrayOp1.size, sArrayOp2.size)
        if (sArrayOp1.size < nCount){
            (nCount - sArrayOp1.size).let {
                (0..it -1 ).forEach { sArrayOp1.add(0,0) }
            }
        }

        if (sArrayOp2.size < nCount){
            (nCount - sArrayOp2.size).let {
                (0..it -1 ).forEach { sArrayOp2.add(0,0) }
            }
        }
    }

    private fun showQuestion() {
        println("\n")
        print(" ")
        sArrayOp1.forEach { print(it) }
        print("-")
        sArrayOp2.forEach { print(it) }
        println("=\n")
    }
}