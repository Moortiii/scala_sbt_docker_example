FROM hseeberger/scala-sbt:8u212_1.2.8_2.13.0

WORKDIR /app/

COPY src /app/src/
COPY project /app/project/
COPY build.sbt /app/

#RUN sed -e '$s/$/\n/' -s *.scala *.java > PuzzleSolver.scala
