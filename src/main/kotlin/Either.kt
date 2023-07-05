import java.lang.Exception

sealed class Either<out E, out A>

data class Left<out E>(val value: E) : Either<E, Nothing>()
data class Right<out A>(val value: A) : Either<Nothing, A>()

fun <A, B, E> Either<E, A>.map(fn: (A) -> B): Either<E, B> {
   return when(this) {
       is Left -> this
       is Right -> Right(fn(this.value))
   }
}