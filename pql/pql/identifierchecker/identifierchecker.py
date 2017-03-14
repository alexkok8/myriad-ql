# coding=utf-8
from collections import defaultdict

from pql.traversal.FormVisitor import FormVisitor


class IdentifierChecker(FormVisitor):
    def __init__(self):
        self.__symbol_table = defaultdict(list)

    def visit(self, pql_ast):
        def build_error_list(identifiers):
            errors = list()
            for key, value in identifiers.items():
                if len(value) > 1:
                    errors.append("Key: {} contained multiple entries, the following locations: {}"
                                  .format(key, [v.location for v in value]))
            return errors
        self.__symbol_table.clear()
        [form.apply(self) for form in pql_ast]
        return build_error_list(self.__symbol_table)

    def form(self, node):
        for statement in node.statements:
            statement.apply(self)

    def conditional_if_else(self, node):
        self.conditional_if(node)
        for statement in node.else_statement_list:
            statement.apply(self)

    def conditional_if(self, node):
        for statement in node.statements:
            statement.apply(self)

    def field(self, node):
        self.__symbol_table[node.name.name].append(node.name)
