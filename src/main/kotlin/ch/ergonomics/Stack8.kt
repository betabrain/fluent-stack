package ch.ergonomics

sealed class Stack8<A, B, C, D, E, F, G, H> {
  abstract fun drop(): Stack7<A, B, C, D, E, F, G>
  abstract fun <I> map(m: Mapper1<H, I>): Stack8<A, B, C, D, E, F, G, I>
  abstract fun <I> map(m: Mapper2<G, H, I>): Stack7<A, B, C, D, E, F, I>
  abstract fun <I> map(m: Mapper3<F, G, H, I>): Stack6<A, B, C, D, E, I>
  abstract fun <I> map(m: Mapper4<E, F, G, H, I>): Stack5<A, B, C, D, I>
  abstract fun <I> map(m: Mapper5<D, E, F, G, H, I>): Stack4<A, B, C, I>
  abstract fun <I> map(m: Mapper6<C, D, E, F, G, H, I>): Stack3<A, B, I>
  abstract fun <I> map(m: Mapper7<B, C, D, E, F, G, H, I>): Stack2<A, I>
  abstract fun <I> map(m: Mapper8<A, B, C, D, E, F, G, H, I>): Stack1<I>
  open fun rethrow() {}
  open fun tos(): H = throw TopOfStackException()

  class Okay<A, B, C, D, E, F, G, H>(
    private val v1: A,
    private val v2: B,
    private val v3: C,
    private val v4: D,
    private val v5: E,
    private val v6: F,
    private val v7: G,
    private val v8: H,
  ) :
    Stack8<A, B, C, D, E, F, G, H>() {
    override fun drop(): Stack7<A, B, C, D, E, F, G> = Stack7.Okay(v1, v2, v3, v4, v5, v6, v7)

    override fun <I> map(m: Mapper1<H, I>): Stack8<A, B, C, D, E, F, G, I> {
      return try {
        Okay(v1, v2, v3, v4, v5, v6, v7, m.invoke(v8))
      } catch (ex: Exception) {
        Error(ex)
      }
    }

    override fun <I> map(m: Mapper2<G, H, I>): Stack7<A, B, C, D, E, F, I> {
      return try {
        Stack7.Okay(v1, v2, v3, v4, v5, v6, m.invoke(v7, v8))
      } catch (ex: Exception) {
        Stack7.Error(ex)
      }
    }

    override fun <I> map(m: Mapper3<F, G, H, I>): Stack6<A, B, C, D, E, I> {
      return try {
        Stack6.Okay(v1, v2, v3, v4, v5, m.invoke(v6, v7, v8))
      } catch (ex: Exception) {
        Stack6.Error(ex)
      }
    }

    override fun <I> map(m: Mapper4<E, F, G, H, I>): Stack5<A, B, C, D, I> {
      return try {
        Stack5.Okay(v1, v2, v3, v4, m.invoke(v5, v6, v7, v8))
      } catch (ex: Exception) {
        Stack5.Error(ex)
      }
    }

    override fun <I> map(m: Mapper5<D, E, F, G, H, I>): Stack4<A, B, C, I> {
      return try {
        Stack4.Okay(v1, v2, v3, m.invoke(v4, v5, v6, v7, v8))
      } catch (ex: Exception) {
        Stack4.Error(ex)
      }
    }

    override fun <I> map(m: Mapper6<C, D, E, F, G, H, I>): Stack3<A, B, I> {
      return try {
        Stack3.Okay(v1, v2, m.invoke(v3, v4, v5, v6, v7, v8))
      } catch (ex: Exception) {
        Stack3.Error(ex)
      }
    }

    override fun <I> map(m: Mapper7<B, C, D, E, F, G, H, I>): Stack2<A, I> {
      return try {
        Stack2.Okay(v1, m.invoke(v2, v3, v4, v5, v6, v7, v8))
      } catch (ex: Exception) {
        Stack2.Error(ex)
      }
    }

    override fun <I> map(m: Mapper8<A, B, C, D, E, F, G, H, I>): Stack1<I> {
      return try {
        Stack1.Okay(m.invoke(v1, v2, v3, v4, v5, v6, v7, v8))
      } catch (ex: Exception) {
        Stack1.Error(ex)
      }
    }

    override fun tos() = v8
  }

  class Error<A, B, C, D, E, F, G, H>(private val ex: Exception) : Stack8<A, B, C, D, E, F, G, H>() {
    override fun drop(): Stack7<A, B, C, D, E, F, G> = Stack7.Error(ex)
    override fun <I> map(m: Mapper1<H, I>): Stack8<A, B, C, D, E, F, G, I> = Error(ex)
    override fun <I> map(m: Mapper2<G, H, I>): Stack7<A, B, C, D, E, F, I> = Stack7.Error(ex)
    override fun <I> map(m: Mapper3<F, G, H, I>): Stack6<A, B, C, D, E, I> = Stack6.Error(ex)
    override fun <I> map(m: Mapper4<E, F, G, H, I>): Stack5<A, B, C, D, I> = Stack5.Error(ex)
    override fun <I> map(m: Mapper5<D, E, F, G, H, I>): Stack4<A, B, C, I> = Stack4.Error(ex)
    override fun <I> map(m: Mapper6<C, D, E, F, G, H, I>): Stack3<A, B, I> = Stack3.Error(ex)
    override fun <I> map(m: Mapper7<B, C, D, E, F, G, H, I>): Stack2<A, I> = Stack2.Error(ex)
    override fun <I> map(m: Mapper8<A, B, C, D, E, F, G, H, I>): Stack1<I> = Stack1.Error(ex)
    override fun rethrow() = throw RethrowException(ex)
  }
}