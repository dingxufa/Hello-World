#!/usr/bin/env python
# -*- coding:utf-8 -*-
#
# class Rule:
# 	"""
# 	Base class for all rules.所有规则的基类
# 	"""
# 	def action(self,block,handler):
# 		handler.start(self.type)  #  handler.start(self.type)-->handler.callback('start_',slef.type)-->handler.start_"self.type"()
# 		handler.feed(block)      # handler.feed(block)
# 		handler.end(self.type)
# 		return True
#
# class HeadingRule(Rule):
# 	"""
# 	A heading is a single line that is at most 70 characters and
# 	that doesn't end with a colon.
# 	标题占一行 最多70个字符  并且不可以以冒号结尾
# 	"""
# 	type = 'heading'
# 	def condition(self,block):
# 		return not '\n' in block and len(block) <= 70  and not block[-1] == ':'
#
# class TitleRule(HeadingRule):
# 	"""
# 	The title is the first block in the document, provided that
# 	it is a heading.
# 	题目是文档的第一行 但前提是它是大标题
# 	"""
# 	type='title'
# 	first=True    #是否第一行
# 	def condition(self,block):
# 		if not self.first:return False
# 		self.first = False
# 		return HeadingRule.condition(self,block)
#
# class ListItemRule(Rule):
# 	"""
# 	A list item is a paragraph that begins with a hyphen. As part of the
# 	formatting, the hyphen is removed.
# 	列表项是以连字符开始的段落。 作为格式化的一部分，要移除连字符
# 	"""
# 	type='listitem'
# 	def condition(self,block):
# 		return block[0] == '-'
# 	def action(self,block,handler): #重写了基类的action方法，因为要移除连字符
# 		handler.start(self.type)
# 		handler.feed(block[1:].strip())
# 		handler.end(self.type)
# 		return True
#
# class ListRule(ListItemRule):
# 	"""
# 	A list begins between a block that is not a list item and a
# 	subsequent list item. It ends after the last consecutive list item.
# 	列表从不是 列表项的块和随后的列表项之间  在最后一个连续列表项之后结束
# 	"""
# 	type = 'list'
# 	inside=False  #当前是否是在列表之中
# 	def condition(self, block):
# 		return True
# 	def action(self,block,handler):
# 		# 当解析到第一个列表项时，一定是inside=False,且当前ListItemRule.condition(self,block)为True
#
# 		if not self.inside and ListItemRule.condition(self,block):
# 			handler.start(self.type)
# 			self.inside =True
# 		elif self.inside and not ListItemRule.condition(self,block):
# 			handler.end(self.type)
# 			self.inside=False
# 		return False  #注意这里
#
# class ParagraphRule(Rule):
# 	"""
# 	A paragraph is simply a block that isn't covered by any of the other rules.
# 	段落只是其他规则没有覆盖到的块 即其他规则不起作用时，默认使用
# 	"""
# 	type='paragraph'
# 	def condition(self,block):
# 		return True
#
#
#
#
#

class Rule:
    """
    Base class for all rules.
    """

    def action(self, block, handler):
        handler.start(self.type)
        handler.feed(block)
        handler.end(self.type)
        return True

class HeadingRule(Rule):
    """
    A heading is a single line that is at most 70 characters and
    that doesn't end with a colon.
    """
    type = 'heading'
    def condition(self, block):
        return not '\n' in block and len(block) <= 70 and not block[-1] == ':'

class TitleRule(HeadingRule):
    """
    The title is the first block in the document, provided that
    it is a heading.
    """
    type = 'title'
    first = True

    def condition(self, block):
        if not self.first: return False
        self.first = False
        return HeadingRule.condition(self, block)

class ListItemRule(Rule):
    """
    A list item is a paragraph that begins with a hyphen. As part of the
    formatting, the hyphen is removed.
    """
    type = 'listitem'
    def condition(self, block):
        return block[0] == '-'
    def action(self, block, handler):
        handler.start(self.type)
        handler.feed(block[1:].strip())
        handler.end(self.type)
        return True

class ListRule(ListItemRule):
    """
    A list begins between a block that is not a list item and a
    subsequent list item. It ends after the last consecutive list item.
    """
    type = 'list'
    inside = False
    def condition(self, block):
        return True
    def action(self, block, handler):
        if not self.inside and ListItemRule.condition(self, block):
            handler.start(self.type)
            self.inside = True
        elif self.inside and not ListItemRule.condition(self, block):
            handler.end(self.type)
            self.inside = False
        return False

class ParagraphRule(Rule):
    """
    A paragraph is simply a block that isn't covered by any of the other rules.
    """
    type = 'paragraph'
    def condition(self, block):
        return True