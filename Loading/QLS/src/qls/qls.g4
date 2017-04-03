grammar qls;

@parser::header
{
    import qls.ast.*;
    import qls.ast.widget.*;
    import QL.ast.type.*;
    import QL.ui.Style;
}


stylesheet returns [Stylesheet result] 
 : 'stylesheet' ID pages { $result = new Stylesheet($pages.result, $ctx.start.getLine()); };
 
 pages returns [List<Page> result]
 @ init {
	$result = new ArrayList<Page>();
}
 : ( 'page' ID '{' defaultWidgets sections  '}' { $result.add(new Page($sections.result, $defaultWidgets.result, $ctx.start.getLine())); })+
 | ( 'page' ID '{' sections '}' { $result.add(new Page($sections.result, new ArrayList<DefaultWidget>(), $ctx.start.getLine())); })+
 ;
 
 sections returns [List<Section> result]
 @ init {
	$result = new ArrayList<Section>();
}
 : ('section' STRING '{'? defaultWidgets questions  '}'? { $result.add(new Section($questions.result, $defaultWidgets.result, $ctx.start.getLine())); }
 |  'section' STRING '{'? questions '}'? { $result.add(new Section($questions.result, new ArrayList<DefaultWidget>(), $ctx.start.getLine())); })+
 ;

questions returns [List<Question> result]
@ init {
	$result = new ArrayList<Question>();
}
 : ('question' ID widget { $result.add(new QuestionWithWidget($ID.text, $widget.result, $ctx.start.getLine())); }
 |  'question' ID { $result.add(new Question($ID.text, $ctx.start.getLine())); }
   )+
 ;


widget returns [Widget result]
 : 'widget' 'checkbox' { $result = new CheckBox($ctx.start.getLine()); }
 | 'widget' 'radio(' param1 = str ',' param2 = str ')' { $result = new RadioButton($param1.result, $param2.result, $ctx.start.getLine()); }
 | 'widget' 'spinbox' { $result = new SpinBox($ctx.start.getLine()); }
 | 'widget' 'slider' { $result = new Slider($ctx.start.getLine()); }
 | 'widget' 'numberfield' { $result = new NumberField($ctx.start.getLine()); }
 | 'widget' 'textfield' { $result = new TextField($ctx.start.getLine()); }
 | 'widget' 'dropdown(' param1 = str ',' param2 = str ')' { $result = new DropDown($param1.result, $param2.result, $ctx.start.getLine()); }
 ;
  
defaultWidgets returns [List<DefaultWidget> result]
@ init {
	$result = new ArrayList<DefaultWidget>();
}
 : ('default' type widget { $result.add(new DefaultWidget($type.result, $widget.result, $ctx.start.getLine())); }
 | 'default' type '{'
   'width:' width = INT
   'font:' font = str
   'fontsize:' fontSize = INT
   'color:' color =  str
   widget { $result.add(new DefaultStyle($type.result, new Style(Integer.parseInt($width.text), $font.result, 
     Integer.parseInt($fontSize.text), $color.result), $widget.result, $ctx.start.getLine())); }
   '}'
   )+
 ;
 
 type returns [Type result]
 : 'boolean' { $result = new BooleanType($ctx.start.getLine()); }
 | 'integer' { $result = new IntegerType($ctx.start.getLine()); }
 | 'string'  { $result = new StringType($ctx.start.getLine()); }
 ;

str returns [String result]
 : STRING { $result = $STRING.text.substring(1, $STRING.text.length()-1); }
 ;

ID : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

INT : ('0'..'9')+;

STRING : '"' .*? '"';

WS : [ \t\r\n]+ -> skip;

COMMENT : ( '//' ~[\r\n]* '\r'? '\n' | '/*' .*? '*/') -> skip;
