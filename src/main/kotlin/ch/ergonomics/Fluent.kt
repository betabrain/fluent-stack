package ch.ergonomics

object Fluent {
  @JvmStatic
  fun stack() = Stack0.Okay()

  @JvmStatic
  fun <A> stack(v1: A) = Stack1.Okay(v1)

  @JvmStatic
  fun <A, B> stack(v1: A, v2: B) = Stack2.Okay(v1, v2)

  @JvmStatic
  fun <A, B, C> stack(v1: A, v2: B, v3: C) = Stack3.Okay(v1, v2, v3)

  @JvmStatic
  fun <A, B, C, D> stack(v1: A, v2: B, v3: C, v4: D) = Stack4.Okay(v1, v2, v3, v4)

  @JvmStatic
  fun <A, B, C, D, E> stack(v1: A, v2: B, v3: C, v4: D, v5: E) = Stack5.Okay(v1, v2, v3, v4, v5)

  @JvmStatic
  fun <A, B, C, D, E, F> stack(v1: A, v2: B, v3: C, v4: D, v5: E, v6: F) = Stack6.Okay(v1, v2, v3, v4, v5, v6)

  @JvmStatic
  fun <A, B, C, D, E, F, G> stack(v1: A, v2: B, v3: C, v4: D, v5: E, v6: F, v7: G) =
    Stack7.Okay(v1, v2, v3, v4, v5, v6, v7)

  @JvmStatic
  fun <A, B, C, D, E, F, G, H> stack(v1: A, v2: B, v3: C, v4: D, v5: E, v6: F, v7: G, v8: H) =
    Stack8.Okay(v1, v2, v3, v4, v5, v6, v7, v8)
}