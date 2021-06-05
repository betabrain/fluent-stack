package ch.ergonomics

sealed class Stack0 {
  abstract fun <A> push(v: A): Stack1<A>
  abstract fun <A> push(supplier: Supplier<A>): Stack1<A>
  open fun rethrow() {}

  class Okay : Stack0() {
    override fun <A> push(v: A): Stack1<A> = Stack1.Okay(v)
    override fun <A> push(supplier: Supplier<A>): Stack1<A> {
      return try {
        Stack1.Okay(supplier.get())
      } catch (ex: Exception) {
        Stack1.Error(ex)
      }
    }
  }

  class Error(private val ex: Exception) : Stack0() {
    override fun <A> push(v: A): Stack1<A> = Stack1.Error(ex)
    override fun <A> push(supplier: Supplier<A>): Stack1<A> = Stack1.Error(ex)
    override fun rethrow() = throw RethrowException(ex)
  }
}