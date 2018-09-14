#!/usr/bin/env python
# -*- coding:utf-8 -*-

from reportlab.graphics.shapes import Drawing,String
from reportlab.graphics import renderPDF
d = Drawing(100,100)
s =String(50,50,"hello world",textAnchor='middle')
d.add(s)
renderPDF.drawToFile(d,'hello0.pdf','A simple pdf file',autoSize=0)
renderPDF.drawToFile(d,'hello1.pdf','A simple pdf file',autoSize=1)



