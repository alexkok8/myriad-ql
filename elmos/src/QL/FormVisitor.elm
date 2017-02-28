module QL.FormVisitor exposing (..)

import QL.AST exposing (..)
import VisitorUtil exposing (Order(Continue), actionLambda)


type alias Config context =
    { onIfThen : VisitorUtil.Order context ( Expression, Block )
    , onIfThenElse : VisitorUtil.Order context ( Expression, Block, Block )
    , onField : VisitorUtil.Order context ( Label, Id, ValueType )
    , onComputedField : VisitorUtil.Order context ( Label, Id, ValueType, Expression )
    , onExpression : VisitorUtil.Order context Expression
    }


defaultConfig : Config x
defaultConfig =
    { onIfThen = Continue
    , onIfThenElse = Continue
    , onField = Continue
    , onComputedField = Continue
    , onExpression = Continue
    }


inspect : Config a -> Form -> a -> a
inspect config { items } =
    inspectBlock config items


inspectBlock : Config a -> Block -> a -> a
inspectBlock config block context =
    List.foldl (inspectFormItem config) context block


inspectExpression : Config a -> Expression -> a -> a
inspectExpression config expression context =
    actionLambda config.onExpression
        identity
        expression
        context


inspectFormItem : Config a -> FormItem -> a -> a
inspectFormItem config formItem context =
    case formItem of
        Field label id valueType ->
            actionLambda config.onField
                identity
                ( label, id, valueType )
                context

        ComputedField label id valueType computation ->
            actionLambda config.onComputedField
                (inspectExpression config computation)
                ( label, id, valueType, computation )
                context

        IfThen condition thenBlock ->
            actionLambda config.onIfThen
                (inspectExpression config condition >> inspectBlock config thenBlock)
                ( condition, thenBlock )
                context

        IfThenElse condition thenBlock elseBlock ->
            actionLambda config.onIfThenElse
                (inspectExpression config condition >> inspectBlock config thenBlock >> inspectBlock config elseBlock)
                ( condition, thenBlock, elseBlock )
                context
