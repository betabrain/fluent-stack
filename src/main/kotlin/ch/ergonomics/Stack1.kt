package ch.ergonomics

sealed class Stack1<A> {
  abstract fun drop(): Stack0
  abstract fun <B> push(v: B): Stack2<A, B>
  abstract fun <B> push(supplier: Supplier<B>): Stack2<A, B>
  abstract fun <B> map(m: Mapper1<A, B>): Stack1<B>
  open fun rethrow() {}
  open fun tos(): A = throw TopOfStackException()
  abstract fun dup(): Stack2<A, A>

  class Okay<A>(private val v1: A) : Stack1<A>() {
    override fun drop(): Stack0 = Stack0.Okay()
    override fun <B> push(v: B): Stack2<A, B> = Stack2.Okay(v1, v)
    override fun <B> push(supplier: Supplier<B>): Stack2<A, B> {
      return try {
        Stack2.Okay(v1, supplier.get())
      } catch (ex: Exception) {
        Stack2.Error(ex)
      }
    }

    override fun <B> map(m: Mapper1<A, B>): Stack1<B> {
      return try {
        Okay(m.invoke(v1))
      } catch (ex: Exception) {
        Error(ex)
      }
    }

    override fun tos() = v1
    override fun dup(): Stack2<A, A> = Stack2.Okay(v1, v1)
  }

  class Error<A>(private val ex: Exception) : Stack1<A>() {
    override fun drop(): Stack0 = Stack0.Error(ex)
    override fun <B> push(v: B): Stack2<A, B> = Stack2.Error(ex)
    override fun <B> push(supplier: Supplier<B>): Stack2<A, B> = Stack2.Error(ex)
    override fun <B> map(m: Mapper1<A, B>): Stack1<B> = Error(ex)
    override fun rethrow() = throw RethrowException(ex)
    override fun dup(): Stack2<A, A> = Stack2.Error(ex)
  }
}