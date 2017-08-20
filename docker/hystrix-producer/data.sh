#!/bin/bash

curl -s "http://172.10.0.71:7070/calc/sum?a=10&b=6"
curl -s "http://172.10.0.71:7070/calc/sub?a=10&b=6"
curl -s "http://172.10.0.71:7070/calc/mul?a=10&b=6"
curl -s "http://172.10.0.71:7070/calc/div?a=10&b=6"
curl -s "http://172.10.0.71:7070/calc/slow?a=10&b=6"

while true; do; curl -s "http://172.10.0.71:7070/calc/sum?a=10&b=6"; done;
while true; do; curl -s "http://172.10.0.71:7070/calc/err"; done;
