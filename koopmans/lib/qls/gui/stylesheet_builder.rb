# TODO use wrapper pattern to apply styles
module QLS
  module GUI
    class StylesheetBuilder
      def visit_stylesheet(stylesheet, _)
        @question_styles = {}
        @defaults = stylesheet.accept(DefaultCollector.new)
        stylesheet.pages.map { |page| page.accept(self) }
        @question_styles
      end

      def visit_page(page)
        default = @defaults[page.object_id]
        page.body.map { |element| element.accept(self, default) }
      end

      def visit_section(section, parent_default)
        default = @defaults[section.object_id] || parent_default
        section.body.map { |element| element.accept(self, default)}
      end

      def visit_question(question, parent_default)
        properties =  @defaults[question.object_id] || parent_default
        @question_styles[question.variable.name] = properties if properties
      end

      def visit_default(_, _) end
    end
  end
end