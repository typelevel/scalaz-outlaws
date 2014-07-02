# scalaz-outlaws

"An outlaw can be defined as somebody who lives outside the law,
beyond the law and not necessarily against it." ― Hunter S. Thompson

## About

scalaz-outlaws aims to be a reopository to collect Typeclasses,
Typeclass instances and other curios which are not welcome in
scalaz-core because they are either lawless, or don't strictly adhere
to all the laws of a given typeclass.

## What is here and why?

### Set

Set isn't considered to be a functor. You should be able to easily find some
flamewars about this

### Try

Try isn't a valid functor. [read about it
here](https://issues.scala-lang.org/browse/SI-6284)

What should you consider using instead?  Instead of:

    Try {
        might throw
    }

Try:

    \/.fromTryCatchThrowable {
        might throw
    }

or perhaps:

    Task.delay {
        might throw
    }

### Each[A]

This is a typeclass with a single function "each" which is like "foreach" found
on many scala standard library collection classes. There are no meaningful laws
for Each. It is only used for side-effects, which are considered harmful.

What should you use instead of side-effecting?

instead of:

    xxx.foreach(sideeffect)

consider:

    val sideEffectingComputation = xxx.traverse_(x => Task.delay(sideeffect(x))


followed eventually by:

    val didItWork: Throwable \/ Unit = sideEffectingComputation.attemptRun
