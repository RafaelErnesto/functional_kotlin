sealed class Option<out A>

data class Some<out A>(val get: A): Option<A>()

object None: Option<Nothing>()


fun <A, B> Option<A>.map(fn: (A) -> B): Option<B> {
    return when(this) {
        is None -> None
        is Some -> Some(fn(this.get))
    }
}
fun <A, B> Option<A>.flatMap(fn: (A) -> Option<B>): Option<B> {
    return when(this) {
        is None -> None
        is Some -> fn(this.get)
    }
}

fun <A> Option<A>.getOrElse(default: () -> A): A {
    return when(this) {
        is None -> default()
        is Some -> this.get
    }
}

fun <A> Option<A>.orElse(ob: () -> Option<A>): Option<A> {
    return when(this) {
        is None -> ob()
        is Some -> Some(this.get)
    }
}

fun <A> Option<A>.filter(fn: (A) -> Boolean): Option<A> {
    return when(this) {
      is Some -> {
          if(!fn(this.get)) return None
          else Some(this.get)
      }
        is None -> None
    }
}