package ch.ergonomics

sealed class Stack6<A, B, C, D, E, F> {
  abstract fun drop(): Stack5<A, B, C, D, E>
  abstract fun <G> push(v: G): Stack7<A, B, C, D, E, F, G>
  abstract fun <G> map(m: (F) -> G): Stack6<A, B, C, D, E, G>
  abstract fun <G> map(m: (E, F) -> G): Stack5<A, B, C, D, G>
  abstract fun <G> map(m: (D, E, F) -> G): Stack4<A, B, C, G>
  abstract fun <G> map(m: (C, D, E, F) -> G): Stack3<A, B, G>
  abstract fun <G> map(m: (B, C, D, E, F) -> G): Stack2<A, G>
  abstract fun <G> map(m: (A, B, C, D, E, F) -> G): Stack1<G>
  open fun rethrow() {}
  open fun tos(): F = throw TopOfStackException()

  class Okay<A, B, C, D, E, F>(
    private val v1: A,
    private val v2: B,
    private val v3: C,
    private val v4: D,
    private val v5: E,
    private val v6: F,
  ) :
    Stack6<A, B, C, D, E, F>() {
    override fun drop(): Stack5<A, B, C, D, E> = Stack5.Okay(v1, v2, v3, v4, v5)
    override fun <G> push(v: G): Stack7<A, B, C, D, E, F, G> = Stack7.Okay(v1, v2, v3, v4, v5, v6, v)
    override fun <G> map(m: (F) -> G): Stack6<A, B, C, D, E, G> {
      return try {
        Okay(v1, v2, v3, v4, v5, m.invoke(v6))
      } catch (ex: Exception) {
        Error(ex)
      }
    }

    override fun <G> map(m: (E, F) -> G): Stack5<A, B, C, D, G> {
      return try {
        Stack5.Okay(v1, v2, v3, v4, m.invoke(v5, v6))
      } catch (ex: Exception) {
        Stack5.Error(ex)
      }
    }

    override fun <G> map(m: (D, E, F) -> G): Stack4<A, B, C, G> {
      return try {
        Stack4.Okay(v1, v2, v3, m.invoke(v4, v5, v6))
      } catch (ex: Exception) {
        Stack4.Error(ex)
      }
    }

    override fun <G> map(m: (C, D, E, F) -> G): Stack3<A, B, G> {
      return try {
        Stack3.Okay(v1, v2, m.invoke(v3, v4, v5, v6))
      } catch (ex: Exception) {
        Stack3.Error(ex)
      }
    }

    override fun <G> map(m: (B, C, D, E, F) -> G): Stack2<A, G> {
      return try {
        Stack2.Okay(v1, m.invoke(v2, v3, v4, v5, v6))
      } catch (ex: Exception) {
        Stack2.Error(ex)
      }
    }

    override fun <G> map(m: (A, B, C, D, E, F) -> G): Stack1<G> {
      return try {
        Stack1.Okay(m.invoke(v1, v2, v3, v4, v5, v6))
      } catch (ex: Exception) {
        Stack1.Error(ex)
      }
    }

    override fun tos() = v6
  }

  class Error<A, B, C, D, E, F>(private val ex: Exception) : Stack6<A, B, C, D, E, F>() {
    override fun drop(): Stack5<A, B, C, D, E> = Stack5.Error(ex)
    override fun <G> push(v: G): Stack7<A, B, C, D, E, F, G> = Stack7.Error(ex)
    override fun <G> map(m: (F) -> G): Stack6<A, B, C, D, E, G> = Error(ex)
    override fun <G> map(m: (E, F) -> G): Stack5<A, B, C, D, G> = Stack5.Error(ex)
    override fun <G> map(m: (D, E, F) -> G): Stack4<A, B, C, G> = Stack4.Error(ex)
    override fun <G> map(m: (C, D, E, F) -> G): Stack3<A, B, G> = Stack3.Error(ex)
    override fun <G> map(m: (B, C, D, E, F) -> G): Stack2<A, G> = Stack2.Error(ex)
    override fun <G> map(m: (A, B, C, D, E, F) -> G): Stack1<G> = Stack1.Error(ex)
    override fun rethrow() = throw FluentException(ex)
  }
}