package util.extensions

extension(b: Boolean) def ?[A](second: => A) = new Ternary(b, second)

final class Ternary[A](b: Boolean, second: => A) {
  def |(third: => A) = if (b) second else third
}


// object GlobalOperators {

//   @inline
//   implicit class TernaryOps(b: Boolean) {
//     def ?[A](second: A) = new Ternary(second)

//     final class Ternary[A](second: A) {
//       def |(third: A) = if (b) second else third
//     }
//   }
// }
