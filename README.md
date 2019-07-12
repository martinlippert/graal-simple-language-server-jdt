# graal-simple-language-server-jdt

This is an experiment running a language server as native image which uses JDT to parse Java source code

## how to run

First, run the maven build inside of `org.test.language-server-jd`

`mvn clean package`

Then run `./compile.sh` to create the native image. Make sure you have a recent GraalVM installed.
