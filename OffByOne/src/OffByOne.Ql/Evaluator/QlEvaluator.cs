﻿namespace OffByOne.Ql.Evaluator
{
    using System.Windows;
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Evaluator.Controls.Questions;
    using OffByOne.Ql.Visitors;
    using OffByOne.Ql.Visitors.Base;

    public class QlEvaluator : BaseQlVisitor<UIElement, VisitorTypeEnvironment>
    {
        public override UIElement Visit(FormStatement form, VisitorTypeEnvironment context)
        {
            var questionnaire = new Window { Title = form.Identifier };
            var list = new ListView();
            foreach (var statement in form.Statements)
            {
                list.Items.Add(statement.Accept(this, context));
            }

            questionnaire.Content = list;
            questionnaire.Show();
            return questionnaire;
        }

        public override UIElement Visit(QuestionStatement statement, VisitorTypeEnvironment context)
        {
            var factory = new ControlFactory();
            return factory.CreateControl(statement).Control;
        }
    }
}
