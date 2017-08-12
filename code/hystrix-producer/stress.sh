#!/bin/bash

i=1
to=100
while [ $i -le $to ]
do
  curl -S "http://localhost:7070/calc/sum?a=25&b=$i"
  curl -S "http://localhost:7070/calc/sub?a=25&b=$i"
  curl -S "http://localhost:7070/calc/mul?a=25&b=$i"
  curl -S "http://localhost:7070/calc/div?a=25&b=$i"
  i=$(( $i + 1 ))
done
