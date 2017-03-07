﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents;

namespace Questionnaires.AST
{
    public enum QLType
    {
        Number,
        Money,
        Bool,
        String
    };

    public interface INode
    {
        QLType? CheckOperandTypes(List<QLType> parameters, SemanticAnalysis.QLContext context, List<ISemenaticAnalysisEvent> events);
    }
}