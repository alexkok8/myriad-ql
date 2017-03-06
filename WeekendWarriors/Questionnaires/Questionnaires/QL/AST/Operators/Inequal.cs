﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis;
using Questionnaires.SemanticAnalysis.Messages;
using Questionnaires.Value;

namespace Questionnaires.QL.AST.Operators
{
    public class Inequal : Binary
    {
        public Inequal(IExpression lhs, IExpression rhs) : base(lhs, rhs)
        {
        }
        

        public override IValue GetResultType(QLContext context)
        {
            return Lhs.GetResultType(context).InequalTo(Rhs.GetResultType(context));
        }
    }
}