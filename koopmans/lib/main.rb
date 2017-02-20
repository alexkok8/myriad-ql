require_relative 'parser/file_reader'
require_relative 'parser/parser'

require_relative 'ast/expression'
require_relative 'ast/type'
require_relative 'ast/literal'
require_relative 'ast/statement'
require_relative 'ast/form'
require_relative 'ast/variable'

require_relative 'type_checker/duplicate_label_checker'
require_relative 'type_checker/duplicate_variable_checker'
require_relative 'type_checker/variable_visitor'
require_relative 'type_checker/undefined_variable_checker'
require_relative 'type_checker/condition_type_checker'
require_relative 'type_checker/operands_type_checker'
require_relative 'type_checker/cyclic_checker'

require 'parslet'
require 'pp'

# read file
file_reader = FileReader.new
contents = file_reader.read_file('../examples/simple_questionnaire.ql')

# parse content
parser = Parslet::Parser.new
parsed = parser.parse(contents)

transformer = Parslet::Transform.new
ast = transformer.apply(parsed)

# duplicate_labels = ast.accept(DuplicateLabelChecker.new)
# if duplicate_labels
#   p '[WARNING] duplicate labels:'
#   p duplicate_labels
#   p ""
# end
#
# duplicate_question_variables = ast.accept(DuplicateVariableChecker.new)
# if duplicate_question_variables
#   p '[ERROR] duplicate question declarations with different types:'
#   p duplicate_question_variables
#   p ""
# end
#
# undefinedVariables = ast.accept(UndefinedVariableChecker.new)
# if undefinedVariables
#   p '[ERROR] reference to undefined questions:'
#   p undefinedVariables
#   p ""
# end
#
# conditions = ast.accept(ConditionTypeChecker.new)
# if conditions
#   p '[ERROR] conditions that are not of the type boolean:'
#   p conditions
#   p ""
# end
#
# operands = ast.accept(OperandsTypeChecker.new)
# if operands
#   p 'operands of invalid type to operators:'
#   operands.each do |o|
#     p o
#   end
#   p ""
# end

cyclic = ast.accept(CyclicChecker.new)
if cyclic
  p 'cyclic dependencies between questions:'
  cyclic.each do |o|
    p o
  end
  p ""
end
# DuplicateLabelChecker.new.visit_ast(ast)
# pp parsed
# pp ast

# gui = Gui.new
# gui.question('joe?')
# gui.launch

