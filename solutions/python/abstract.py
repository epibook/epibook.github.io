from abc import ABC, abstractmethod
class Abstract(ABC):
#from abc import abstractmethod
#class Abstract():
    @abstractmethod
    def foo(self):
        pass

class Concrete(Abstract):
    def bar(self):
        print("Hi from Concrete")

    def widget(self):
        print("Hi from Concrete widget")

    #def foo(self):
    #    print("Hi from Concrete foo()")

x = Concrete()
x.bar()
x.widget()
x.foo()

