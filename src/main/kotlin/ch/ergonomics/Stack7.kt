package ch.ergonomics

sealed class Stack7<A, B, C, D, E, F, G> {
  abstract fun drop(): Stack6<A, B, C, D, E, F>
  abstract fun <H> push(v: H): Stack8<A, B, C, D, E, F, G, H>
  abstract fun <H> map(m: Mapper1<G, H>): Stack7<A, B, C, D, E, F, H>
  abstract fun <H> map(m: Mapper2<F, G, H>): Stack6<A, B, C, D, E, H>
  abstract fun <H> map(m: Mapper3<E, F, G, H>): Stack5<A, B, C, D, H>
  abstract fun <H> map(m: Mapper4<D, E, F, G, H>): Stack4<A, B, C, H>
  abstract fun <H> map(m: Mapper5<C, D, E, F, G, H>): Stack3<A, B, H>
  abstract fun <H> map(m: Mapper6<B, C, D, E, F, G, H>): Stack2<A, H>
  abstract fun <H> map(m: Mapper7<A, B, C, D, E, F, G, H>): Stack1<H>
  open fun rethrow() {}
  open fun tos(): G = throw TopOfStackException()
  abstract fun swap(): Stack7<A, B, C, D, E, G, F>
  abstract fun dup(): Stack8<A, B, C, D, E, F, G, G>
  abstract fun over(): Stack8<A, B, C, D, E, F, G, F>

  class Okay<A, B, C, D, E, F, G>(
    private val v1: A,
    private val v2: B,
    private val v3: C,
    private val v4: D,
    private val v5: E,
    private val v6: F,
    private val v7: G,
  ) :
    Stack7<A, B, C, D, E, F, G>() {
    override fun drop(): Stack6<A, B, C, D, E, F> = Stack6.Okay(v1, v2, v3, v4, v5, v6)
    override fun <H> push(v: H): Stack8<A, B, C, D, E, F, G, H> = Stack8.Okay(v1, v2, v3, v4, v5, v6, v7, v)

    override fun <H> map(m: Mapper1<G, H>): Stack7<A, B, C, D, E, F, H> {
      return try {
        Okay(v1, v2, v3, v4, v5, v6, m.invoke(v7))
      } catch (ex: Exception) {
        Error(ex)
      }
    }

    override fun <H> map(m: Mapper2<F, G, H>): Stack6<A, B, C, D, E, H> {
      return try {
        Stack6.Okay(v1, v2, v3, v4, v5, m.invoke(v6, v7))
      } catch (ex: Exception) {
        Stack6.Error(ex)
      }
    }

    override fun <H> map(m: Mapper3<E, F, G, H>): Stack5<A, B, C, D, H> {
      return try {
        Stack5.Okay(v1, v2, v3, v4, m.invoke(v5, v6, v7))
      } catch (ex: Exception) {
        Stack5.Error(ex)
      }
    }

    override fun <H> map(m: Mapper4<D, E, F, G, H>): Stack4<A, B, C, H> {
      return try {
        Stack4.Okay(v1, v2, v3, m.invoke(v4, v5, v6, v7))
      } catch (ex: Exception) {
        Stack4.Error(ex)
      }
    }

    override fun <H> map(m: Mapper5<C, D, E, F, G, H>): Stack3<A, B, H> {
      return try {
        Stack3.Okay(v1, v2, m.invoke(v3, v4, v5, v6, v7))
      } catch (ex: Exception) {
        Stack3.Error(ex)
      }
    }

    override fun <H> map(m: Mapper6<B, C, D, E, F, G, H>): Stack2<A, H> {
      return try {
        Stack2.Okay(v1, m.invoke(v2, v3, v4, v5, v6, v7))
      } catch (ex: Exception) {
        Stack2.Error(ex)
      }
    }

    override fun <H> map(m: Mapper7<A, B, C, D, E, F, G, H>): Stack1<H> {
      return try {
        Stack1.Okay(m.invoke(v1, v2, v3, v4, v5, v6, v7))
      } catch (ex: Exception) {
        Stack1.Error(ex)
      }
    }

    override fun tos() = v7
    override fun swap(): Stack7<A, B, C, D, E, G, F> = Okay(v1, v2, v3, v4, v5, v7, v6)
    override fun dup(): Stack8<A, B, C, D, E, F, G, G> = Stack8.Okay(v1, v2, v3, v4, v5, v6, v7, v7)
    override fun over(): Stack8<A, B, C, D, E, F, G, F> = Stack8.Okay(v1, v2, v3, v4, v5, v6, v7, v6)
  }

  class Error<A, B, C, D, E, F, G>(private val ex: Exception) : Stack7<A, B, C, D, E, F, G>() {
    override fun drop(): Stack6<A, B, C, D, E, F> = Stack6.Error(ex)
    override fun <H> push(v: H): Stack8<A, B, C, D, E, F, G, H> = Stack8.Error(ex)
    override fun <H> map(m: Mapper1<G, H>): Stack7<A, B, C, D, E, F, H> = Error(ex)
    override fun <H> map(m: Mapper2<F, G, H>): Stack6<A, B, C, D, E, H> = Stack6.Error(ex)
    override fun <H> map(m: Mapper3<E, F, G, H>): Stack5<A, B, C, D, H> = Stack5.Error(ex)
    override fun <H> map(m: Mapper4<D, E, F, G, H>): Stack4<A, B, C, H> = Stack4.Error(ex)
    override fun <H> map(m: Mapper5<C, D, E, F, G, H>): Stack3<A, B, H> = Stack3.Error(ex)
    override fun <H> map(m: Mapper6<B, C, D, E, F, G, H>): Stack2<A, H> = Stack2.Error(ex)
    override fun <H> map(m: Mapper7<A, B, C, D, E, F, G, H>): Stack1<H> = Stack1.Error(ex)
    override fun rethrow() = throw RethrowException(ex)
    override fun swap(): Stack7<A, B, C, D, E, G, F> = Error(ex)
    override fun dup(): Stack8<A, B, C, D, E, F, G, G> = Stack8.Error(ex)
    override fun over(): Stack8<A, B, C, D, E, F, G, F> = Stack8.Error(ex)
  }
}