module QL
  module GUI
    class StringQuestion < TextQuestion
      include AST

      def initialize(args)
        super
        @variable.value = ("")
        @variable.type  = StringType

        TextWidget.new(question: self)
      end
    end
  end
end