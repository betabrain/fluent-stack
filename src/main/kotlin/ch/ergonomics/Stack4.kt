package ch.ergonomics

sealed class Stack4<A, B, C, D> {
  abstract fun drop(): Stack3<A, B, C>
  abstract fun <E> push(v: E): Stack5<A, B, C, D, E>
  abstract fun <E> push(supplier: Supplier<E>): Stack5<A, B, C, D, E>
  abstract fun <E> map(m: Mapper1<D, E>): Stack4<A, B, C, E>
  abstract fun <E> map(m: Mapper2<C, D, E>): Stack3<A, B, E>
  abstract fun <E> map(m: Mapper3<B, C, D, E>): Stack2<A, E>
  abstract fun <E> map(m: Mapper4<A, B, C, D, E>): Stack1<E>
  open fun rethrow() {}
  open fun tos(): D = throw TopOfStackException()
  abstract fun swap(): Stack4<A, B, D, C>
  abstract fun dup(): Stack5<A, B, C, D, D>
  abstract fun over(): Stack5<A, B, C, D, C>

  class Okay<A, B, C, D>(private val v1: A, private val v2: B, private val v3: C, private val v4: D) :
    Stack4<A, B, C, D>() {
    override fun drop(): Stack3<A, B, C> = Stack3.Okay(v1, v2, v3)
    override fun <E> push(v: E): Stack5<A, B, C, D, E> = Stack5.Okay(v1, v2, v3, v4, v)
    override fun <E> push(supplier: Supplier<E>): Stack5<A, B, C, D, E> {
      return try {
        Stack5.Okay(v1, v2, v3, v4, supplier.get())
      } catch (ex: Exception) {
        Stack5.Error(ex)
      }
    }

    override fun <E> map(m: Mapper1<D, E>): Stack4<A, B, C, E> {
      return try {
        Okay(v1, v2, v3, m.invoke(v4))
      } catch (ex: Exception) {
        Error(ex)
      }
    }

    override fun <E> map(m: Mapper2<C, D, E>): Stack3<A, B, E> {
      return try {
        Stack3.Okay(v1, v2, m.invoke(v3, v4))
      } catch (ex: Exception) {
        Stack3.Error(ex)
      }
    }

    override fun <E> map(m: Mapper3<B, C, D, E>): Stack2<A, E> {
      return try {
        Stack2.Okay(v1, m.invoke(v2, v3, v4))
      } catch (ex: Exception) {
        Stack2.Error(ex)
      }
    }

    override fun <E> map(m: Mapper4<A, B, C, D, E>): Stack1<E> {
      return try {
        Stack1.Okay(m.invoke(v1, v2, v3, v4))
      } catch (ex: Exception) {
        Stack1.Error(ex)
      }
    }

    override fun tos() = v4
    override fun swap(): Stack4<A, B, D, C> = Okay(v1, v2, v4, v3)
    override fun dup(): Stack5<A, B, C, D, D> = Stack5.Okay(v1, v2, v3, v4, v4)
    override fun over(): Stack5<A, B, C, D, C> = Stack5.Okay(v1, v2, v3, v4, v3)
  }

  class Error<A, B, C, D>(private val ex: Exception) : Stack4<A, B, C, D>() {
    override fun drop(): Stack3<A, B, C> = Stack3.Error(ex)
    override fun <E> push(v: E): Stack5<A, B, C, D, E> = Stack5.Error(ex)
    override fun <E> push(supplier: Supplier<E>): Stack5<A, B, C, D, E> = Stack5.Error(ex)
    override fun <E> map(m: Mapper1<D, E>): Stack4<A, B, C, E> = Error(ex)
    override fun <E> map(m: Mapper2<C, D, E>): Stack3<A, B, E> = Stack3.Error(ex)
    override fun <E> map(m: Mapper3<B, C, D, E>): Stack2<A, E> = Stack2.Error(ex)
    override fun <E> map(m: Mapper4<A, B, C, D, E>): Stack1<E> = Stack1.Error(ex)
    override fun rethrow() = throw RethrowException(ex)
    override fun swap(): Stack4<A, B, D, C> = Error(ex)
    override fun dup(): Stack5<A, B, C, D, D> = Stack5.Error(ex)
    override fun over(): Stack5<A, B, C, D, C> = Stack5.Error(ex)
  }
}