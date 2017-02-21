# At a stable state of lexing and parsing this import needs to be changed to a more concrete version which only imports
# the needed methods from the pyparsing package
from pyparsing import *
from pql.ast import ast



def parse(input_string):
    identifier = Word(alphas, alphanums + '_')
    number = Word(nums + ".")

    arith_operand = number | identifier
    bool_operand = Literal("true") | Literal("false") | number | identifier
    bool_operand.setParseAction(ast.BoolOperand)

    # Reserved keywords
    form_lit = Suppress("form")
    if_lit = Suppress("if")
    else_lit = Suppress("else")

    # Reserved symbols
    l_curly = Suppress("{")
    r_curly = Suppress("}")
    l_paren = Suppress("(")
    r_paren = Suppress(")")
    colon = Suppress(":")
    data_types = oneOf(["boolean", "money", "string", "integer"])

    # Reserved operators
    multiplication_op = Literal("*").setResultsName("multiplication_operator")
    divide_op = Literal("/").setResultsName("division_operator")
    add_op = Literal("+").setResultsName("addition_operator")
    subtract_op = Literal("-").setResultsName("subtraction_operator")

    rat_op = oneOf(["<", "<=", ">", ">="])
    eqal_op = oneOf(["==", "!="])
    con_and_op = Literal("&&")
    con_or_op = Literal("||")
    assign_op = Suppress("=")

    #TODO: Signop toevoegen
    arith_prec = [
        (add_op, 2, opAssoc.LEFT, ast.Addition),
        (subtract_op, 2, opAssoc.LEFT, ast.Substraction),
        (divide_op, 2, opAssoc.LEFT, ast.Division),
        (multiplication_op, 2, opAssoc.LEFT, ast.Multiplication),
    ]

    #TODO: Not toevoegen
    bool_prec = [
        (rat_op, 2, opAssoc.LEFT),
        (eqal_op, 2, opAssoc.LEFT),
        (con_and_op, 2, opAssoc.LEFT, ast.BoolAnd),
        (con_or_op, 2, opAssoc.LEFT, ast.BoolOr),
    ]

    # Arithmetic precedence
    arithmetic = infixNotation(
        arith_operand.setResultsName('arithmetic_operand*'),
        arith_prec
    ).setResultsName('arithmetic_expr')

    bool_expr = infixNotation(
        bool_operand.setResultsName('boolean_operand'),
        (arith_prec + bool_prec)
    ).setResultsName('boolean_expr')

    arithmetic.setParseAction(lambda parsed_tokens: ast.Arithmetic(*parsed_tokens))

    boolean_expr = bool_expr

    arithmetic_expression = \
        OneOrMore(
            arithmetic | (l_paren + arithmetic + r_paren)
        ).setResultsName("arithmetic_statement")

    arithmetic_expression.addParseAction(lambda parsed_tokens: ast.Expression(*parsed_tokens))
    boolean_statement = \
        OneOrMore(boolean_expr | (l_paren + boolean_expr + r_paren))
    boolean_statement.setParseAction(lambda parsed_tokens: ast.Condition(*parsed_tokens))

    field_expr = \
        QuotedString('"', unquoteResults=True).setResultsName("title") + \
        identifier.setResultsName("identifier") + \
        colon + \
        data_types.setResultsName("data_type") + \
        Optional(
            assign_op +
            arithmetic_expression
        )
    field_expr.setParseAction(lambda parsed_tokens: ast.Field(*parsed_tokens))

    statement_list = Forward()
    if_stmt = Forward()
    if_stmt << \
        Group(
            if_lit +
            l_paren +
            boolean_statement +
            r_paren +
            statement_list +
            Optional(else_lit + l_curly + statement_list + r_curly).setResultsName('else_statement')
        ).setParseAction(ast.Conditional)

    statement = field_expr ^ if_stmt
    statement_list <<= l_curly + ZeroOrMore(statement) + r_curly
    statement_list.addParseAction(lambda parsed_tokens: [parsed_tokens.asList()])

    # Program
    form = \
        form_lit + \
        identifier.setResultsName("form_identifier") + \
        statement_list.setResultsName("form_statement_list")
    form.addParseAction(lambda parsed_tokens: ast.Form(*parsed_tokens))
    tokens = form.parseString(input_string)
    return tokens
