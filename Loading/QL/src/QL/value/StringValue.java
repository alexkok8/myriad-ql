package QL.value;

public class StringValue extends Value {

	private final String value;

    public StringValue(String value) {
        this.value = value;
    }
    
    public StringValue() {
    	this.value = "";
    }

	@Override
	public BoolValue eq(Value other) {
    	return new BoolValue(value.equals(((StringValue) other).getValue()) );
	}

	@Override
	public BoolValue notEq(Value other) {
		return new BoolValue(!value.equals(((StringValue) other).getValue()) );
	}
		
	public String getValue() {
		return this.value;
	}

	public String convertToString() {
    	return this.value;
    }

	@Override
	public boolean equals(Value other) {
		return value.equals(((StringValue) other).getValue());
	}
}
