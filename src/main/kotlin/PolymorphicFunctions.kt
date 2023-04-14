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

fun main(){
    val intArray = arrayOf(1,2,3,5,9)
    printAll(intArray)
    val doubleArray = arrayOf(1.9,3.4,1.6,0.67)
    printAll(doubleArray)
    val stringArray = arrayOf("Hi","Bye","Good","Yes")
    printAll(stringArray)
}