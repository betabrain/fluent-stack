package ch.ergonomics

sealed class Stack4<A, B, C, D> {
  abstract fun drop(): Stack3<A, B, C>
  abstract fun <E> push(v: E): Stack5<A, B, C, D, E>
  abstract fun <E> map(m: (D) -> E): Stack4<A, B, C, E>
  abstract fun <E> map(m: (C, D) -> E): Stack3<A, B, E>
  abstract fun <E> map(m: (B, C, D) -> E): Stack2<A, E>
  abstract fun <E> map(m: (A, B, C, D) -> E): Stack1<E>
  open fun rethrow() {}
  open fun tos(): D = throw TopOfStackException()

  class Okay<A, B, C, D>(private val v1: A, private val v2: B, private val v3: C, private val v4: D) :
    Stack4<A, B, C, D>() {
    override fun drop(): Stack3<A, B, C> = Stack3.Okay(v1, v2, v3)
    override fun <E> push(v: E): Stack5<A, B, C, D, E> = Stack5.Okay(v1, v2, v3, v4, v)
    override fun <E> map(m: (D) -> E): Stack4<A, B, C, E> {
      return try {
        Okay(v1, v2, v3, m.invoke(v4))
      } catch (ex: Exception) {
        Error(ex)
      }
    }

    override fun <E> map(m: (C, D) -> E): Stack3<A, B, E> {
      return try {
        Stack3.Okay(v1, v2, m.invoke(v3, v4))
      } catch (ex: Exception) {
        Stack3.Error(ex)
      }
    }

    override fun <E> map(m: (B, C, D) -> E): Stack2<A, E> {
      return try {
        Stack2.Okay(v1, m.invoke(v2, v3, v4))
      } catch (ex: Exception) {
        Stack2.Error(ex)
      }
    }

    override fun <E> map(m: (A, B, C, D) -> E): Stack1<E> {
      return try {
        Stack1.Okay(m.invoke(v1, v2, v3, v4))
      } catch (ex: Exception) {
        Stack1.Error(ex)
      }
    }

    override fun tos() = v4
  }

  class Error<A, B, C, D>(private val ex: Exception) : Stack4<A, B, C, D>() {
    override fun drop(): Stack3<A, B, C> = Stack3.Error(ex)
    override fun <E> push(v: E): Stack5<A, B, C, D, E> = Stack5.Error(ex)
    override fun <E> map(m: (D) -> E): Stack4<A, B, C, E> = Error(ex)
    override fun <E> map(m: (C, D) -> E): Stack3<A, B, E> = Stack3.Error(ex)
    override fun <E> map(m: (B, C, D) -> E): Stack2<A, E> = Stack2.Error(ex)
    override fun <E> map(m: (A, B, C, D) -> E): Stack1<E> = Stack1.Error(ex)
    override fun rethrow() = throw FluentException(ex)
  }
}