from abc import ABCMeta, abstractmethod

class ExpressionAlg(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def Literal(self, value):
        pass

    @abstractmethod
    def Variable(self, name, datatype):
        pass


class StatementAlg(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def Question(self, variable, label):
        pass

    @abstractmethod
    def ComputedQuestion(self, variable, label):
        pass

    @abstractmethod
    def Block(self, statements):
        pass


class QlAlg(ExpressionAlg, StatementAlg):
    __metaclass__ = ABCMeta

    @abstractmethod
    def Form(self, statements):
        pass


class ToString(QlAlg):

    def Literal(self, value):
        class _anon():
            to_string = lambda self: "Literal({})".format(str(value))

        return _anon()

    def Form(self, name, block):
        class _anon():
            to_string = lambda self: "Form({}, {})".format(
                str(name), block.to_string())
        return _anon()

    def Block(self, statements):
        class _anon():
            to_string = lambda self: "Block(\n{}\n)".format(
                (("\n").join([k.to_string() for _, k in enumerate(statements)])))

        return _anon()

    def Variable(self, name, datatype):
        class _anon():
            to_string = lambda self: "Variable(name={}, datatype={})".format(
                str(name), datatype.to_string())
        return _anon()

    def RefVariable(self, name):
        class _anon():
            to_string = lambda self: "RefVariable(name={})".format(str(name))
        return _anon()

    def Question(self, variable, label):
        class _anon():
            to_string = lambda self: "Question(variable={}, label='{}')".format(
                variable.to_string(), str(label))
        return _anon()

    def ifThen(self, expression, block):
        class _anon():
            to_string = lambda self: "ifThen(expression={}, label='{}')".format(
                expression.to_string(), block.to_string())
        return _anon()

    def ComputedQuestion(self, variable, label, expression):
        class _anon():
            to_string = lambda self: "Question(variable={}, label='{}', expression={})".format(
                variable.to_string(), str(label), expression.to_string())
        return _anon()

    def Boolean(self, value=False):
        class _anon():
            to_string = lambda self: "Boolean({})".format(str(value))
        return _anon()

    def Money(self, value=False):
        class _anon():
            to_string = lambda self: "Money({})".format(str(value))
        return _anon()

    def Substraction(self, lhs, rhs):
        class _anon():
            to_string = lambda self: "Substraction({}, {})".format(
                lhs.to_string(), rhs.to_string())
        return _anon()

    def Integer(self, value):
        class _anon():
            to_string = lambda self: "Integer({})".format(str(value))
        return _anon()


def add_tabs(tabs=0):
    return '\t' * tabs


class PrettyPrint(QlAlg):
    state = []

    def __init__(self, tabs):
        self.tabs = tabs

    def Literal(self, value):
        class _anon():
            to_string = lambda self, tabs: "Literal({})".format(str(value))

        return _anon()

    def Form(self, name, block):
        class _anon():
            to_string = lambda self, tabs: "Form({}, {} \n)".format(
                str(name), block.to_string(tabs + 1))
        return _anon()

    def Block(self, statements):
        class _anon():
            to_string = lambda self, tabs: "Block(\n{})".format(
                (("\n").join([k.to_string(tabs + 1) for _, k in enumerate(statements)])))

        return _anon()

    def Variable(self, name, datatype):
        class _anon():
            to_string = lambda self, tabs: "Variable(name={}, datatype={})".format(
                str(name), datatype.to_string(tabs))
        return _anon()

    def RefVariable(self, name):
        class _anon():
            to_string = lambda self, tabs: "RefVariable(name={})".format(
                str(name))
        return _anon()

    def Question(self, variable, label):
        class _anon():
            to_string = lambda self, tabs: add_tabs(
                tabs) + "Question(variable={}, label='{}')".format(variable.to_string(tabs + 1), label.to_string(tabs))
        return _anon()

    def ifThen(self, expression, block):
        class _anon():
            to_string = lambda self, tabs:  add_tabs(tabs) + "ifThen(expression={}, block={}".format(
                expression.to_string(tabs + 1), block.to_string(tabs + 1)) + "\n" + add_tabs(tabs) + ")"
        return _anon()

    def ComputedQuestion(self, variable, label, expression):
        class _anon():
            to_string = lambda self, tabs: add_tabs(tabs) + "ComputedQuestion(variable={}, label='{}',".format(variable.to_string(
                tabs + 1), label.to_string(tabs)) + "\n" + add_tabs(tabs) + "expressions={})".format(expression.to_string(tabs + 1))
        return _anon()

    def Boolean(self, value=False):
        class _anon():
            to_string = lambda self, tabs: "Boolean({})".format(str(value))
        return _anon()

    def Money(self, value=False):
        class _anon():
            to_string = lambda self, tabs: "Money({})".format(str(value))
        return _anon()

    def Substraction(self, lhs, rhs):
        class _anon():
            to_string = lambda self, tabs: "Substraction({}, {})".format(
                lhs.to_string(tabs + 1), rhs.to_string(tabs + 1))
        return _anon()

    def Integer(self, value):
        class _anon():
            to_string = lambda self, tabs: "Integer({})".format(str(value))
        return _anon()

    def String(self, value):
        class _anon():
            to_string = lambda self, tabs: "String({})".format(str(value))
        return _anon()


class Environment(object):
    variables = []
    ref_variables = []
    questions = []
    variables_dict = {}

    def __init__(self):
        pass

    def add_var(self, var):
        self.variables.append(var)
        self.variables_dict.update(var)

    def add_ref(self, var):
        self.ref_variables.append(var)

    def add_question(self, variable, label):
        self.questions.append((variable, label))

    def get_var(self, var):
        return self.variables_dict.get(var)

    def update_var(self, var, value):
        self.variables_dict.update({var: value})
        print(self.variables_dict)


    def check_type(self, variable):
        pass

    def check_question_type(self, question):
        pass

    def check_types(self):
        sequence = [(_var, _datatype) for _var, _datatype in self.variables]
        var_table = {}
        issues = []
        for _var, _datatype in sequence:
            if var_table.has_key(_var):
                existing_var = var_table.get(_var)
                if existing_var != _datatype:
                    issues.append('{} is already defined with {}, it can\'t be redeclared with {}'.format(
                        _var, existing_var, _datatype))
            else:
                var_table.update({_var: _datatype})

        return issues  # [v for v,d in sequence]

    def all_labels(self):
        return [y for x, y in self.questions]

    def duplicate_labels(self):
        labels = self.all_labels()
        frequency = {_label: labels.count(_label)
                     for _var, _label in self.questions}
        return {label for label, x in frequency.items() if x > 1}

    def is_registerd(self, var):
        return var in set([_var for _var, _type in self.variables])

    def get_variables(self):
        return self.variables

    def export(self):
        return {key:variable.get() for key, variable in self.variables_dict.items()}

    def undefined_variables(self):
        return set([_var for _var in self.ref_variables if self.is_registerd(_var) == False])


class GetVariables(QlAlg):

    def __init__(self, environment_vars):
        self.environment_vars = environment_vars
        self.environment = Environment()

    def Literal(self, value):
        class _anon():
            execute = None

        return _anon()

    def Form(self, name, block):
        def _register():
            block.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Block(self, statements):
        class _anon():
            execute = lambda self: [k.execute()
                                    for _, k in enumerate(statements)]
        return _anon()

    def Variable(self, name, datatype):
        def _register():
            self.environment.add_var((name, datatype.execute()))
            return (name, datatype.execute())

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def RefVariable(self, name):
        def _register():
            self.environment.add_ref((name))
            print(self.environment.is_registerd(name))

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Question(self, variable, label):
        def _register():
            self.environment.add_question(variable.execute(), label.execute())

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def ifThen(self, expression, block):
        def _register():
            expression.execute()
            block.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def ComputedQuestion(self, variable, label, expression):
        def _register():
            variable.execute()
            label.execute()
            expression.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Boolean(self, value=False):
        def _register(self):
            return 'boolean'

        class _anon():
            execute = lambda self: _register(self)
        return _anon()

    def Money(self, value=False):
        def _register():
            return 'money'

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Substraction(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Integer(self, value):
        def _register():
            return 'integer'

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def StringLiteral(self, value):
        def _register():
            return value

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def String(self, value):
        def _register():
            return 'string'

        class _anon():
            execute = lambda self: _register()
        return _anon()


class GetVariables(QlAlg):

    def __init__(self, environment_vars):
        self.environment = Environment()

    def Literal(self, value):
        class _anon():
            execute = None

        return _anon()

    def Form(self, name, block):
        def _register():
            block.execute()
            return

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Block(self, statements):
        class _anon():
            execute = lambda self: [k.execute()
                                    for _, k in enumerate(statements)]
        return _anon()

    def Variable(self, name, datatype):
        def _register():
            self.environment.add_var((name, datatype.execute()))
            return (name, datatype.execute())

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def RefVariable(self, name):
        def _register():
            self.environment.add_ref((name))
            print(self.environment.is_registerd(name))

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Question(self, variable, label):
        def _register():
            self.environment.add_question(variable.execute(), label.execute())

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def ifThen(self, expression, block):
        def _register():
            expression.execute()
            block.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def ComputedQuestion(self, variable, label, expression):
        def _register():
            variable.execute()
            label.execute()
            expression.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Boolean(self, value=False):
        def _register(self):
            return 'boolean'

        class _anon():
            execute = lambda self: _register(self)
        return _anon()

    def Money(self, value=False):
        def _register():
            return 'money'

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Substraction(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Integer(self, value):
        def _register():
            return 'integer'

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def StringLiteral(self, value):
        def _register():
            return value

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def String(self, value):
        def _register():
            return 'string'

        class _anon():
            execute = lambda self: _register()
        return _anon()
