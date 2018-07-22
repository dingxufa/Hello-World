#!/usr/bin/env python
# -*- coding:utf-8 -*-
from urllib.request import urlopen
from reportlab.graphics.shapes import *
from reportlab.graphics.charts.lineplots import LinePlot
from reportlab.graphics.charts.textlabels import Label
from reportlab.graphics import renderPDF

URL = 'http://services.swpc.noaa.gov/text/predicted-sunspot-radio-flux.txt'
COMMENT_CHARS = '#:'

drawing = Drawing(400, 200)
data = []
# for line in urlopen(URL).readlines():
#     if not line.isspace() and not line[0] in COMMENT_CHARS:
#         data.append([float(n) for n in line.split()])


"""
isspace()方法检测字符串是否只由空格组成， 语法 str.isspace()，如果字符串只包含空格则返回True 否则返回False
python3
"""
for line in urlopen(URL).readlines():
    print('line:')
    print(line.decode('utf-8'))
    if not line.decode('utf-8').isspace() and not line.decode('utf-8')[0] in COMMENT_CHARS:
        data.append([float(n) for n in line.split()])

# b'2018 09         9.3    17.3     1.3       69.0    76.0    62.0 ' print(line)
# b'2018 09         9.3    17.3     1.3       69.0    76.0    62.0 ' print(line.decode('utf-8'))
pred = [row[2] for row in data]
high = [row[3] for row in data]
low = [row[4] for row in data]
times = [row[0] + row[1]/12.0 for row in data]
#print(times)

lp = LinePlot()
lp.x = 50
lp.y = 50
lp.height = 125
lp.width = 300
lp.data = [list(zip(times, pred)), list(zip(times, high)), list(zip(times, low))]
#py3 加入list(zip(times, pred))否则报错TypeError: 'zip' object is not subscriptable zip对象不可下标


lp.lines[0].strokeColor = colors.blue
lp.lines[1].strokeColor = colors.red
lp.lines[2].strokeColor = colors.green

drawing.add(lp)
drawing.add(String(250, 150, 'Sunspots',
            fontSize=14, fillColor=colors.red))
renderPDF.drawToFile(drawing, './sunspots.pdf', '2 Title Sunspots')