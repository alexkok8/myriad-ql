module QLS.TypeChecker.DefaultValueConfigWidgetType exposing (check)

import QL.AST exposing (Form, Location, Id, ValueType(..))
import QLS.AST exposing (StyleSheet, Question, Configuration(..), Widget(..))
import QLS.AST.Widget as Widget
import QLS.TypeChecker.Messages exposing (Message(WidgetDefaultConfigMismatch))
import QLS.AST.Collectors as QLSCollectors
import QLS.TypeChecker.WidgetCompatibility as WidgetCompatibility
import Tuple3


check : Form -> StyleSheet -> List Message
check _ styleSheet =
    QLSCollectors.collectDefaultValueConfigs styleSheet
        |> List.filterMap
            (\( location, valueType, conf ) ->
                Widget.widgetFromConfiguration conf
                    |> Maybe.map ((,,) location valueType)
            )
        |> List.filter (not << WidgetCompatibility.allowedValueTypeWidgetPair << Tuple3.tail)
        |> List.map asMessage


asMessage : ( Location, ValueType, Widget ) -> Message
asMessage ( location, valueType, widget ) =
    WidgetDefaultConfigMismatch location valueType widget
