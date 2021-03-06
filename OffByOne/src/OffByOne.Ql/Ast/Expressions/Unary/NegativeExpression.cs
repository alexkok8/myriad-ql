﻿namespace OffByOne.Ql.Ast.Expressions.Unary
{
    using OffByOne.Ql.Ast.Expressions.Unary.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;

    public class NegativeExpression : UnaryExpression
    {
        public NegativeExpression(Expression expression)
            : base(expression)
        {
        }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
