require 'parslet'

module QL
  module Parser
    class Transformer < Parslet::Transform
      include AST
      # variable
      rule(variable: simple(:name)) { Variable.new(name) }

      # types
      rule(type: 'boolean') { BooleanType.new}
      rule(type: 'integer') { IntegerType.new }
      rule(type: 'money') { MoneyType.new }
      rule(type: 'string') { StringType.new }
      rule(type: 'decimal') { DecimalType.new }
      rule(type: 'date') { DateType.new }

      # literal
      rule(boolean_literal: simple(:value)) { BooleanLiteral.new(value) }
      rule(integer_literal: simple(:value)) { IntegerLiteral.new(value) }
      rule(string_literal: simple(:value)) { StringLiteral.new(value) }

      # question
      rule(question: { label: simple(:string_literal), id: simple(:variable), type: simple(:type) }) { Question.new(string_literal, variable, type) }
      rule(question: { label: simple(:string_literal), id: simple(:variable), type: simple(:type), assignment: subtree(:assignment) }) { Question.new(string_literal, variable, type, assignment) }


      # if statement
      rule(if_statement: { condition: subtree(:condition), body: subtree(:body) }) { IfStatement.new(condition, body) }

      # form
      rule(form: { id: simple(:variable), body: subtree(:body) }) { Form.new(variable, body) }

      # expression
      rule(expression: subtree(:expression)) { ExpressionTransformer.new.apply(expression) }
    end

    class ExpressionTransformer < Parslet::Transform
      include AST
      # negation: ! -
      rule(negation: '!', variable: simple(:variable)) { BooleanNegation.new(variable) }
      rule(negation: '-', variable: simple(:variable)) { IntegerNegation.new(variable) }
      rule(negation: '!', boolean_literal: simple(:boolean_literal)) { BooleanNegation.new(boolean_literal) }
      rule(negation: '-', integer_literal: simple(:integer_literal)) { IntegerNegation.new(integer_literal) }

      # arithmetic: + - / *
      rule(operator: '*', right: simple(:right)) { Multiply.new(right) }
      rule(operator: '/', right: simple(:right)) { Divide.new(right) }
      rule(operator: '+', right: simple(:right)) { Add.new(right) }
      rule(operator: '-', right: simple(:right)) { Subtract.new(right) }

      # comparison: == != < > <= >=
      rule(operator: '==', right: simple(:right)) { Equal.new(right) }
      rule(operator: '!=', right: simple(:right)) { NotEqual.new(right) }
      rule(operator: '<', right: simple(:right)) { Less.new(right) }
      rule(operator: '>', right: simple(:right)) { Greater.new(right) }
      rule(operator: '<=', right: simple(:right)) { LessEqual.new(right) }
      rule(operator: '>=', right: simple(:right)) { GreaterEqual.new(right) }

      # boolean: && ||
      rule(operator: '&&', right: simple(:right)) { And.new(right) }
      rule(operator: '||', right: simple(:right)) { Or.new(right) }
      rule(left: simple(:integer_literal)) { integer_literal }

      rule(sequence(:sequence)) { Sequence.new(sequence) }
    end
  end
end