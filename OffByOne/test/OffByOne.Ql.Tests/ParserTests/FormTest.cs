﻿namespace OffByOne.Ql.Tests.ParserTests
{
    using System;
    using System.Linq;

    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Branch;
    using OffByOne.Ql.Tests.ParserTests.Base;

    using Xunit;

    public class FormTest : ParserTest
    {
        [Fact]
        public void AstCreation_ShouldReturnFormNodeIfGivenSytaxIsCorrect()
        {
            var astTree = this.GetAstNodesFromInput(@"
                form questionnaire { 
                    ""What is your birth date?"" 
                        birthDate: date

                    ""Do you want to continue?""
                        continue: boolean

                    if (birthDate < '31-12-1999' and continue) { 
                        ""How much money do you spend on alcoholic beverages?""
                            alcoholicBeverages: money
                    } else {
                        ""Okay. Goodbye?""
                            exit: boolean
                    }
                }
            ");

            Assert.IsType<FormStatement>(astTree);

            var castAstTree = (FormStatement)astTree;

            Assert.Equal("questionnaire", castAstTree.Identifier);
            Assert.Equal(3, castAstTree.Statements.Count());

            var questions = castAstTree
                .Statements
                .OfType<QuestionStatement>()
                .Select(x => (QuestionStatement)x)
                .ToList();

            Assert.True(questions.Any(x => x.Identifier == "birthDate" && x.Question.Value == "What is your birth date?"));
            Assert.True(questions.Any(x => x.Identifier == "continue" && x.Question.Value == "Do you want to continue?"));

            var ifStatement = castAstTree.Statements.OfType<IfStatement>().First();
            Assert.True(ifStatement.Condition is AndExpression);
            var condition = (AndExpression)ifStatement.Condition;

            Assert.IsType<LessThanExpression>(condition.LeftExpression);
            Assert.IsType<VariableExpression>(condition.RightExpression);

            var lessThanExp = (LessThanExpression)condition.LeftExpression;
            var variableExp = (VariableExpression)condition.RightExpression;

            Assert.IsType<VariableExpression>(lessThanExp.LeftExpression);
            Assert.IsType<DateLiteral>(lessThanExp.RightExpression);
            Assert.Equal(variableExp.Identifier, "continue");

            var lhs = (VariableExpression)lessThanExp.LeftExpression;
            var rhs = (DateLiteral)lessThanExp.RightExpression;

            Assert.Equal(lhs.Identifier, "birthDate");
            Assert.Equal(rhs.Value, new DateTime(1999, 12, 31));

            var elseStatement = ifStatement.ElseStatement;
            Assert.Equal(1, elseStatement.Statements.Count());

        }
    }
}
