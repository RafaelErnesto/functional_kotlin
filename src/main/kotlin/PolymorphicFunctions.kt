fun <T> printAll(data: Array<T>) {
    println("Starting")
    tailrec fun loop(index: Int): Unit =
       if(index < data.size) {
           println(data[index])
           loop(index + 1)
       } else {
           println("Finished")
       }
    loop(0)
}


fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C {
    return { a:A -> { b:B -> f(a,b)} }
}

fun <A, B, C> uncurry(f: (A) -> (B) -> C): (A,B) -> C {
     return { a:A,b:B -> f(a)(b) }
}

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { a:A -> f(g(a)) }
}


fun main(){
    val intArray = arrayOf(1,2,3,5,9)
    printAll(intArray)
    val doubleArray = arrayOf(1.9,3.4,1.6,0.67)
    printAll(doubleArray)
    val stringArray = arrayOf("Hi","Bye","Good","Yes")
    printAll(stringArray)

    val curryResult = curry { a: Int, b: Double -> "Arguments are $a and $b" }(1)(1.5)
    println("Result of curry function: $curryResult")
}