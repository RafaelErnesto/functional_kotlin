sealed class List<out A> {
    companion object {
        fun <A> of(vararg a: A): List<A> {
            val tail = a.sliceArray(1 until a.size)
            return if (a.isEmpty()) Nil else Cons(a[0], of(*tail))
        }

        fun <A> tail(list: List<A>): List<A> {
            return when(list) {
                is Nil -> Nil
                is Cons -> list.tail
            }
        }

        fun <A> setHead(newHead: A, list: List<A>): List<A> {
            return Cons(newHead, list)
        }

        fun <A> getHead(list: List<A>): A? {
            return when(list) {
                is Nil -> null
                is Cons -> list.head
            }
        }

        fun <A> drop(n: Int, list: List<A>): List<A> {
            if(n <= 0) return list
           return  when(list) {
                is Nil -> Nil
                is Cons -> drop(n - 1, list.tail)
            }
        }

        fun <A> dropWhile(fn: (A) -> Boolean, list: List<A>): List<A> {
            val consList = list as Cons
            consList.head
            if (!fn(list.head)) return list
            return when(list) {
                is Nil -> Nil
                is Cons -> dropWhile(fn, list.tail)
            }
        }

        fun <A> init(list: List<A>): List<A> {
            val consList = list as Cons
            if(consList.tail == Nil) {
                return Nil
            }
            val returnedList = init(consList.tail)
            return Cons(list.head, returnedList)
        }

        fun <A, B> foldRight(list: List<A>, z: B, fn: (A,B) -> B): B {
            return when(list) {
                is Nil -> z
                is Cons -> fn(list.head, foldRight(list.tail, z, fn))
            }
        }

        tailrec fun <A, B> foldLeft(list: List<A>, z: B, fn: (B, A) -> B): B {
            return when(list) {
                is Nil -> z
                is Cons -> foldLeft(list.tail, fn(z, list.head), fn)
            }
        }

        fun sum(list: List<Int>): Int =
            foldRight(list, 0) { a, b -> a + b }

        fun sumTailRec(list: List<Int>) =
           foldLeft(list, 0) { b, a -> a + b }

        fun product(list: List<Int>): Double =
            foldRight(list, 1.0) { a, b -> a * b }

        fun length(list: List<Int>): Int =
            foldRight(list, 0) {_, b ->  b + 1}

        fun lengthTailRec(list: List<Int>): Int =
            foldLeft(list, 0) { b, _ -> b + 1 }

        fun reverse(list: List<Int>): List<Int> =
            foldLeft(list, Cons(0, Nil)) { a, b -> Cons(b, a) }
    }
}

object Nil: List<Nothing>()

data class Cons<out A>(val head: A, val tail: List<A>): List<A>()

fun main() {
    val myList = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
    val length = List.length(myList)
    println("Length is $length")
    println("The sumTailRec is ${List.sumTailRec(myList)}")
    println("The lengthTailRec is ${List.lengthTailRec(myList)}")
    println(List.reverse(myList))
}

