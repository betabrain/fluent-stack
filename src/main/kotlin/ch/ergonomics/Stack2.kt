package ch.ergonomics

sealed class Stack2<A, B> {
  abstract fun drop(): Stack1<A>
  abstract fun <C> push(v: C): Stack3<A, B, C>
  abstract fun <C> map(m: Mapper1<B, C>): Stack2<A, C>
  abstract fun <C> map(m: Mapper2<A, B, C>): Stack1<C>
  open fun rethrow() {}
  open fun tos(): B = throw TopOfStackException()
  abstract fun swap(): Stack2<B, A>
  abstract fun dup(): Stack3<A, B, B>
  abstract fun dip(): Stack3<A, B, A>

  class Okay<A, B>(private val v1: A, private val v2: B) : Stack2<A, B>() {
    override fun drop(): Stack1<A> = Stack1.Okay(v1)
    override fun <C> push(v: C): Stack3<A, B, C> = Stack3.Okay(v1, v2, v)
    override fun <C> map(m: Mapper1<B, C>): Stack2<A, C> {
      return try {
        Okay(v1, m.invoke(v2))
      } catch (ex: Exception) {
        Error(ex)
      }
    }

    override fun <C> map(m: Mapper2<A, B, C>): Stack1<C> {
      return try {
        Stack1.Okay(m.invoke(v1, v2))
      } catch (ex: Exception) {
        Stack1.Error(ex)
      }
    }

    override fun tos() = v2
    override fun swap(): Stack2<B, A> = Okay(v2, v1)
    override fun dup(): Stack3<A, B, B> = Stack3.Okay(v1, v2, v2)
    override fun dip(): Stack3<A, B, A> = Stack3.Okay(v1, v2, v1)
  }

  class Error<A, B>(private val ex: Exception) : Stack2<A, B>() {
    override fun drop(): Stack1<A> = Stack1.Error(ex)
    override fun <C> push(v: C): Stack3<A, B, C> = Stack3.Error(ex)
    override fun <C> map(m: Mapper1<B, C>): Stack2<A, C> = Error(ex)
    override fun <C> map(m: Mapper2<A, B, C>): Stack1<C> = Stack1.Error(ex)
    override fun rethrow() = throw RethrowException(ex)
    override fun swap(): Stack2<B, A> = Error(ex)
    override fun dup(): Stack3<A, B, B> = Stack3.Error(ex)
    override fun dip(): Stack3<A, B, A> = Stack3.Error(ex)
  }
}