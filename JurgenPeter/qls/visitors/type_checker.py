from gui.visitors.widget_creator import WidgetCreator
from misc.messages import *


class QlsTypeChecker:
    def __init__(self, symbol_table, errors=[]):
        self.symboltable = symbol_table
        self.errors = errors

    def error(self, message):
        self.errors.append(ErrorMessage(message))

    def check(self, node):
        self.visit(node, [])

    def visit(self, node, stylings):
        return node.accept(self, stylings)

    def visit_layout(self, node, stylings):
        for element in node.body:
            self.visit(element, stylings)

    def visit_styled_layout(self, node, stylings):
        self.visit_layout(node, stylings + node.stylings)

    def visit_page(self, node, stylings):
        for element in node.body:
            self.visit(element, stylings)

    def visit_styled_page(self, node, stylings):
        self.visit_page(node, stylings + node.stylings)

    def visit_section(self, node, stylings):
        for element in node.body:
            self.visit(element, stylings)

    def visit_styled_section(self, node, stylings):
        self.visit_section(node, stylings + node.stylings)

    def visit_question_anchor(self, node, stylings):
        widget_constructor = WidgetCreator().create(self.symboltable[node.name])

        for styling in stylings:
            # TODO: refactor constructor argument pair
            widget_constructor, _ = styling.modify_widget_constructor(self.symboltable[node.name], widget_constructor, None)

        widget_datatype = widget_constructor.get_datatype()
        if widget_datatype != self.symboltable[node.name]:
            self.error("widget datatype {} for question anchor \"{}\" does not "
                       "match question datatype {}".format(widget_datatype,
                                                           node.name,
                                                           self.symboltable[node.name]))


    def visit_styled_question_anchor(self, node, stylings):
        self.visit_question_anchor(node, stylings + [node.styling])