package xuemin.advanced_scala

trait IO[A] { self =>
  def run: A
  def flatMap[B](f: A => IO[B]): IO[B] = new IO[B] {
    override def run: B = f(self.run).run
  }
  def map[B](f: A => B): IO[B] = new IO[B] {
    override def run: B = f(self.run)
  }
}

object IO {
  def apply[A](a: => A): IO[A] = new IO[A] {
    override def run: A = a
  }
}

object Pure {
  def println(msg: String) = IO.apply(print(msg))
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