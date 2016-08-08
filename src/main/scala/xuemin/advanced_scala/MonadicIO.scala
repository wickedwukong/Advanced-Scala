package xuemin.advanced_scala

trait IO[A] {
  def run: Unit
  def flatMap(f: A => IO[A]): IO[A]
  def map(f: A => A): IO[A]
}

object Pure {
  def println(msg: String): IO[String] = new IO[String] {
    override def run: Unit = Predef.println(msg)

    def flatMap(f: String => IO[String]): IO[String] = f(msg)

    def map(f: (String) => String): IO[String] = println(f(msg))
  }
}


object Example {
  val io =
    for {
      _ <- Pure.println("Starting work now.")
      // Do some pure work
      x = 1 + 2 + 3
      _ <- Pure.println("All done. Home time.")
    } yield x

  def run = io.run
}