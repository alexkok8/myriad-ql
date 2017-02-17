import sys
req_version = (3,0)
cur_version = sys.version_info

if cur_version >= req_version:
    from pyparsing import *
    from tkinter import *
    from ast.node import *
else:
    exit("Did you forget to run it using python >= 3.0 ??")

class Interface(Frame):
    # private
    __root = None
    __tree = None

    def __init__(self, ast):
        self.__root = Tk()
        self.__root.minsize(width=400, height=300)
        super().__init__(self.__root)
        self.pack()
        self.__tree = ast

    def main(self):
        pass

    def start_gui(self):
        if(self.__tree is None):
            self.__root.destroy()
            exit("No Data to construct interface... Exiting...")
        self.build_interface()

    def callback(self):
        print("callback!")

    def refresh(self):
        self.__root.update()
        print(self.__variables)
        print("refresh!")

    def construct_from_node(self,node):
        # Get possible displayables
        if(node.__class__ == QuestionNode):
            if(node._field_type == "boolean"):
                node._variable = IntVar()
                Checkbutton(self.__root, text=node._text, variable=node._variable).pack()
            elif(node._field_type == "string"):
                node._variable = StringVar()
                Entry(self.__root, textvariable=node._variable).pack()
            elif(node._field_type == "integer"):
                pass
            elif(node._field_type == "data"):
                pass
            elif(node._field_type == "decimal"):
                pass
            elif(node._field_type == "money"):
                pass
            elif(node._field_type == "currency"):
                pass
            else:
                pass

        elif(node.__class__ == ConditionalNode):
            # recursive
            for child in node.get_children():
                self.construct_from_node(child)
        elif(node.__class__ == StatementNode):
            # statement node
            Label(self.__root, text=node._text).pack()
        else:
            pass

    def build_interface(self):
        # Title (based on identifier of root node)
        self.__root.title(self.__tree.get_identifier())

        # walk through the nodes
        for content_node in self.__tree.get_children():
            self.construct_from_node(content_node)

        self.menu = Menu(self.__root)
        self.__root.config(menu=self.menu)

        self.filemenu = Menu(self.menu)
        self.menu.add_cascade(label="File", menu=self.filemenu)
        self.filemenu.add_command(label="New", command=self.callback)
        self.filemenu.add_command(label="Open...", command=self.callback)
        self.filemenu.add_separator()
        self.filemenu.add_command(label="Exit", command=self.__root.destroy)

        self.helpmenu = Menu(self.menu)
        self.menu.add_cascade(label="Help", menu=self.helpmenu)
        self.helpmenu.add_command(label="About...", command=self.callback)

        self.__root.mainloop()
