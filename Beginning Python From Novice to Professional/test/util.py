#!/usr/bin/env python
# -*- coding:utf-8 -*-

def lines(file):
	'''生成器 依次返回每一行，在文件末尾返回\n'''
	for line in file:
		yield line
	yield '\n'

def blocks(file):
	block=[]
	for line in lines(file):
		if line.strip(): #如果返回是文本行，就添加到block中
			block.append(line)
		elif block:  #返回的不是文本是\nd等，将当前的block返回，当前block有多行文本 block要清空
			yield ''.join(block).strip()
			block = []
