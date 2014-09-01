# scalaz-outlaws

"An outlaw can be defined as somebody who lives outside the law,
beyond the law and not necessarily against it." ― Hunter S. Thompson

## About

scalaz-outlaws aims to be a repository to collect Typeclasses,
Typeclass instances and other curios which are not welcome in
scalaz-core because they are either lawless, or don't strictly adhere
to all the laws of a given typeclass.

## Using scalaz-outlaws

Add the following to your SBT build:

``` scala
resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/stew/snapshots"
```
And add the library dependency:

``` scala
libraryDependencies += "org.typelevel" %% "scalaz-outlaws" % "0.2"
```

## What is here and why?

### Set

Set isn't considered to be a functor. You should be able to easily find some
flamewars about this

in order to import the Set instances from scalaz-outlaws:
``` scala
import scalaz.outlaws.std.set._
```

### Try

Try isn't a valid functor. [read about it
here](https://issues.scala-lang.org/browse/SI-6284)

What should you consider using instead?  Instead of:
``` scala
    Try {
        might throw
    }
```
Try:
``` scala
    \/.fromTryCatchThrowable {
        might throw
    }
```
or perhaps:
``` scala
    Task.delay {
        might throw
    }
```

in order to import the Try instances from scalaz-outlaws:
``` scala
import scalaz.outlaws.std.utilTry._
```

### Each[A]

This is a typeclass with a single function "each" which is like "foreach" found
on many scala standard library collection classes. There are no meaningful laws
for Each. It is only used for side-effects, which are considered harmful.

What should you use instead of side-effecting?

instead of:
``` scala
    xxx.foreach(sideeffect)
```
consider:
``` scala
    val sideEffectingComputation = xxx.traverse_(x => Task.delay(sideeffect(x))
```

followed eventually by:
``` scala
    val didItWork: Throwable \/ Unit = sideEffectingComputation.attemptRun
```

### Monoid[Double] — Monoid[Float]

scalaz doesn't have a monoid instance for Double/Float since you cannot
count on + operations on Double/Float to be associative.

```scala

import scalaz.outlaws.std.double._
import scalaz.outlaws.std.float._
import scalaz.syntax.monoid._

val x: Double = 1D |+| 2D
val y: Float = 1F |+| 2F
```
