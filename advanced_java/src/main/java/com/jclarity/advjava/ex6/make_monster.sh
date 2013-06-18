#!/bin/bash

echo "package advjava.reflection.ex4;"
echo 
echo "public class Monster {"
for ((val=0; val<50; val++))
do
    echo "    private int myInt$val = 0;"
done
for ((val=0; val<50; val++))
do
    echo "    private String myStr$val = null;"
done
echo "}"
