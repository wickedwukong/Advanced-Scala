package xuemin.advanced_scala

trait IO[String] {
  def run: Unit
  def flatMap[B](f: String => IO[String]): IO[String]
  def map[B](f: String => Strin): IO[B]
}




object Pure {
  def println(msg: String): IO[String] = new IO[String] {
    override def run: Unit = Predef.println(msg)

    override def flatMap[B](f: (String) => IO[B]): IO[B] = {
      f(msg)
    }

    override def map[B](f: (String) => B): IO[B] = new IO[B] {
      override def flatMap[B](f: (B) => IO[B]): IO[B] = ???

      override def run: Unit = ???

      override def map[B](f: (B) => B): IO[B] = ???
    }
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