# Gnuplot snippets
- [GNUPLOT 4.2 - A Brief Manual and Tutorial](http://people.duke.edu/~hpgavin/gnuplot.html)

.dot file sample (will call it sample.dot from our scripts):
```
0     1
1000  3
2000  7
3000  12
4000  20
5000  32
6000  48
7000  60
8000  90
```

Plot with lines and points and add custom arrows and subscriptions:
```
set output "sample.svg"
set terminal svg enhanced size 1000,500
set arrow from 4000,0 to 4000,20 nohead linecolor 0
set arrow from 4000,20 to 4500,20 nohead linecolor 0
set arrow from 4500,0 to 4500,26 heads linecolor 0
set arrow from 0,26 to 4500,26 nohead linecolor 0
set label "arrow" at 3500,22
set arrow from 4000,20 to 4000,26 heads linecolor 0
plot "sample.dat" using 1:2 title 'data A' with linespoints
```
