﻿using Questionnaires.SemanticAnalysis;
using Questionnaires.Types;

namespace Questionnaires.QL.AST.Operators
{
    public class And : Binary
    {
        public And(IExpression lhs, IExpression rhs) : base(lhs, rhs)
        {

        }

        public override IType GetResultType(QLContext context)
        {
            return Lhs.GetResultType(context).And(Rhs.GetResultType(context));
        }
    }
}
