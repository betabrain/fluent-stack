package ch.ergonomics

sealed class Stack3<A, B, C> {
  abstract fun drop(): Stack2<A, B>
  abstract fun <D> push(v: D): Stack4<A, B, C, D>
  abstract fun <D> map(m: Mapper1<C, D>): Stack3<A, B, D>
  abstract fun <D> map(m: Mapper2<B, C, D>): Stack2<A, D>
  abstract fun <D> map(m: Mapper3<A, B, C, D>): Stack1<D>
  open fun rethrow() {}
  open fun tos(): C = throw TopOfStackException()
  abstract fun swap(): Stack3<A, C, B>
  abstract fun dup(): Stack4<A, B, C, C>
  abstract fun over(): Stack4<A, B, C, B>

  class Okay<A, B, C>(private val v1: A, private val v2: B, private val v3: C) : Stack3<A, B, C>() {
    override fun drop(): Stack2<A, B> = Stack2.Okay(v1, v2)
    override fun <D> push(v: D): Stack4<A, B, C, D> = Stack4.Okay(v1, v2, v3, v)
    override fun <D> map(m: Mapper1<C, D>): Stack3<A, B, D> {
      return try {
        Okay(v1, v2, m.invoke(v3))
      } catch (ex: Exception) {
        Error(ex)
      }
    }

    override fun <D> map(m: Mapper2<B, C, D>): Stack2<A, D> {
      return try {
        Stack2.Okay(v1, m.invoke(v2, v3))
      } catch (ex: Exception) {
        Stack2.Error(ex)
      }
    }

    override fun <D> map(m: Mapper3<A, B, C, D>): Stack1<D> {
      return try {
        Stack1.Okay(m.invoke(v1, v2, v3))
      } catch (ex: Exception) {
        Stack1.Error(ex)
      }
    }

    override fun tos() = v3
    override fun swap(): Stack3<A, C, B> = Okay(v1, v3, v2)
    override fun dup(): Stack4<A, B, C, C> = Stack4.Okay(v1, v2, v3, v3)
    override fun over(): Stack4<A, B, C, B> = Stack4.Okay(v1, v2, v3, v2)
  }

  class Error<A, B, C>(private val ex: Exception) : Stack3<A, B, C>() {
    override fun drop(): Stack2<A, B> = Stack2.Error(ex)
    override fun <D> push(v: D): Stack4<A, B, C, D> = Stack4.Error(ex)
    override fun <D> map(m: Mapper1<C, D>): Stack3<A, B, D> = Error(ex)
    override fun <D> map(m: Mapper2<B, C, D>): Stack2<A, D> = Stack2.Error(ex)
    override fun <D> map(m: Mapper3<A, B, C, D>): Stack1<D> = Stack1.Error(ex)
    override fun rethrow() = throw RethrowException(ex)
    override fun swap(): Stack3<A, C, B> = Error(ex)
    override fun dup(): Stack4<A, B, C, C> = Stack4.Error(ex)
    override fun over(): Stack4<A, B, C, B> = Stack4.Error(ex)
  }
}