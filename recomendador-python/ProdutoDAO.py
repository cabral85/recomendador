
listaProduto = []

class ProdutoDAO(object):

	global listaProduto

	def get___produtoVisualizado(self):
		return self.____produtoVisualizado

	def get___listaProduto(self):
		return listaProduto

	def set___listaProduto(self, value):
		listaProduto.append(value)

	def set___produtoVisualizado(self, value):
		self.____produtoVisualizado = value

	def set___limpaLista(self):
		listaProduto.clear()

	produtoVisualizado = property(fget=get___produtoVisualizado, fset=set___produtoVisualizado)
	getListaProduto = property(fget=get___listaProduto, fset=set___listaProduto)