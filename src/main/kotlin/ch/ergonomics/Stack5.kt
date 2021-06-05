package ch.ergonomics

sealed class Stack5<A, B, C, D, E> {
  abstract fun drop(): Stack4<A, B, C, D>
  abstract fun <F> push(v: F): Stack6<A, B, C, D, E, F>
  abstract fun <F> push(supplier: Supplier<F>): Stack6<A, B, C, D, E, F>
  abstract fun <F> map(m: Mapper1<E, F>): Stack5<A, B, C, D, F>
  abstract fun <F> map(m: Mapper2<D, E, F>): Stack4<A, B, C, F>
  abstract fun <F> map(m: Mapper3<C, D, E, F>): Stack3<A, B, F>
  abstract fun <F> map(m: Mapper4<B, C, D, E, F>): Stack2<A, F>
  abstract fun <F> map(m: Mapper5<A, B, C, D, E, F>): Stack1<F>
  open fun rethrow() {}
  open fun tos(): E = throw TopOfStackException()
  abstract fun swap(): Stack5<A, B, C, E, D>
  abstract fun dup(): Stack6<A, B, C, D, E, E>
  abstract fun over(): Stack6<A, B, C, D, E, D>

  class Okay<A, B, C, D, E>(
    private val v1: A,
    private val v2: B,
    private val v3: C,
    private val v4: D,
    private val v5: E,
  ) :
    Stack5<A, B, C, D, E>() {
    override fun drop(): Stack4<A, B, C, D> = Stack4.Okay(v1, v2, v3, v4)
    override fun <F> push(v: F): Stack6<A, B, C, D, E, F> = Stack6.Okay(v1, v2, v3, v4, v5, v)
    override fun <F> push(supplier: Supplier<F>): Stack6<A, B, C, D, E, F> {
      return try {
        Stack6.Okay(v1, v2, v3, v4, v5, supplier.get())
      } catch (ex: Exception) {
        Stack6.Error(ex)
      }
    }

    override fun <F> map(m: Mapper1<E, F>): Stack5<A, B, C, D, F> {
      return try {
        Okay(v1, v2, v3, v4, m.invoke(v5))
      } catch (ex: Exception) {
        Error(ex)
      }
    }

    override fun <F> map(m: Mapper2<D, E, F>): Stack4<A, B, C, F> {
      return try {
        Stack4.Okay(v1, v2, v3, m.invoke(v4, v5))
      } catch (ex: Exception) {
        Stack4.Error(ex)
      }
    }

    override fun <F> map(m: Mapper3<C, D, E, F>): Stack3<A, B, F> {
      return try {
        Stack3.Okay(v1, v2, m.invoke(v3, v4, v5))
      } catch (ex: Exception) {
        Stack3.Error(ex)
      }
    }

    override fun <F> map(m: Mapper4<B, C, D, E, F>): Stack2<A, F> {
      return try {
        Stack2.Okay(v1, m.invoke(v2, v3, v4, v5))
      } catch (ex: Exception) {
        Stack2.Error(ex)
      }
    }

    override fun <F> map(m: Mapper5<A, B, C, D, E, F>): Stack1<F> {
      return try {
        Stack1.Okay(m.invoke(v1, v2, v3, v4, v5))
      } catch (ex: Exception) {
        Stack1.Error(ex)
      }
    }

    override fun tos() = v5
    override fun swap(): Stack5<A, B, C, E, D> = Okay(v1, v2, v3, v5, v4)
    override fun dup(): Stack6<A, B, C, D, E, E> = Stack6.Okay(v1, v2, v3, v4, v5, v5)
    override fun over(): Stack6<A, B, C, D, E, D> = Stack6.Okay(v1, v2, v3, v4, v5, v4)
  }

  class Error<A, B, C, D, E>(private val ex: Exception) : Stack5<A, B, C, D, E>() {
    override fun drop(): Stack4<A, B, C, D> = Stack4.Error(ex)
    override fun <F> push(v: F): Stack6<A, B, C, D, E, F> = Stack6.Error(ex)
    override fun <F> push(supplier: Supplier<F>): Stack6<A, B, C, D, E, F> = Stack6.Error(ex)
    override fun <F> map(m: Mapper1<E, F>): Stack5<A, B, C, D, F> = Error(ex)
    override fun <F> map(m: Mapper2<D, E, F>): Stack4<A, B, C, F> = Stack4.Error(ex)
    override fun <F> map(m: Mapper3<C, D, E, F>): Stack3<A, B, F> = Stack3.Error(ex)
    override fun <F> map(m: Mapper4<B, C, D, E, F>): Stack2<A, F> = Stack2.Error(ex)
    override fun <F> map(m: Mapper5<A, B, C, D, E, F>): Stack1<F> = Stack1.Error(ex)
    override fun rethrow() = throw RethrowException(ex)
    override fun swap(): Stack5<A, B, C, E, D> = Error(ex)
    override fun dup(): Stack6<A, B, C, D, E, E> = Stack6.Error(ex)
    override fun over(): Stack6<A, B, C, D, E, D> = Stack6.Error(ex)
  }
}