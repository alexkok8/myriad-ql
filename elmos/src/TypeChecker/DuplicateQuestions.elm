module TypeChecker.DuplicateQuestions exposing (duplicateQuestions)

import AST exposing (..)
import DictList exposing (DictList)
import List.Extra
import Maybe.Extra
import TypeChecker.CheckerUtil exposing (QuestionIndex, questionIndexFromBlock)
import TypeChecker.Messages as Messages exposing (Message)


-- TODO: Use DictSet instead of DictList?


type alias Duplicate =
    ( String, List Location )


duplicateQuestions : Form -> List Message
duplicateQuestions form =
    duplicateQuestionsInBlock (questionIndexFromBlock form.items) form.items
        |> mergeOverlappingDuplicates
        |> DictList.map Messages.duplicateQuestionDefinition
        |> DictList.values


mergeOverlappingDuplicates : List Duplicate -> DictList String (List Location)
mergeOverlappingDuplicates duplicates =
    let
        updateDuplicateIndex ( id, locations ) duplicateIndex =
            DictList.update id (updateLocations locations) duplicateIndex

        updateLocations : List Location -> Maybe (List Location) -> Maybe (List Location)
        updateLocations newLocations existingLocations =
            Maybe.withDefault [] existingLocations
                |> (++) newLocations
                |> List.Extra.uniqueBy (\(Location line col) -> ( line, col ))
                |> Just
    in
        List.foldl (updateDuplicateIndex) DictList.empty duplicates


duplicateQuestionsInBlock : QuestionIndex -> Block -> List Duplicate
duplicateQuestionsInBlock parentScope block =
    let
        currentScope =
            DictList.union parentScope (questionIndexFromBlock block)
    in
        List.concatMap (duplicateQuestionsInItem currentScope) block


duplicateQuestionsInItem : QuestionIndex -> FormItem -> List Duplicate
duplicateQuestionsInItem declaredQuestions formItem =
    case formItem of
        Field _ questionId _ ->
            duplicateQuestionDeclarations declaredQuestions questionId
                |> Maybe.Extra.maybeToList

        ComputedField _ questionId _ _ ->
            duplicateQuestionDeclarations declaredQuestions questionId
                |> Maybe.Extra.maybeToList

        IfThen _ thenBranch ->
            duplicateQuestionsInBlock declaredQuestions thenBranch

        IfThenElse _ thenBranch elseBranch ->
            (++)
                (duplicateQuestionsInBlock declaredQuestions thenBranch)
                (duplicateQuestionsInBlock declaredQuestions elseBranch)


duplicateQuestionDeclarations : QuestionIndex -> Id -> Maybe Duplicate
duplicateQuestionDeclarations declaredQuestions question =
    DictList.get (Tuple.first question) declaredQuestions
        |> Maybe.andThen (duplicateQuestion question)


duplicateQuestion : Id -> List Location -> Maybe Duplicate
duplicateQuestion ( id, location ) declaredQuestionLocations =
    if List.member location declaredQuestionLocations then
        Nothing
    else
        Just ( id, declaredQuestionLocations ++ [ location ] )
