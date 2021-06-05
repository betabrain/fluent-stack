package ch.ergonomics

sealed class Stack0 {
  abstract fun <A> push(v: A): Stack1<A>
  open fun rethrow() {}

  class Okay() : Stack0() {
    override fun <A> push(v: A): Stack1<A> = Stack1.Okay(v)
  }

  class Error(private val ex: Exception) : Stack0() {
    override fun <A> push(v: A): Stack1<A> = Stack1.Error(ex)
    override fun rethrow() = throw RethrowException(ex)
  }
}