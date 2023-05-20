#!/usr/bin/bash

pdflatex -interaction nonstopmode projekt.tex
biber projekt
pdflatex -interaction nonstopmode projekt.tex
pdflatex -interaction nonstopmode projekt.tex
