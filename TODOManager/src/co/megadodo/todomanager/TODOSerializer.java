package co.megadodo.todomanager;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class TODOSerializer extends Serializer<TODO> {

	@Override
	public TODO read(Kryo kryo, Input input, Class<TODO> arg2) {
		TODO todo = new TODO();
		todo.number = input.readInt();
		todo.parentNumber = input.readInt();
		todo.descr = input.readString();
		return todo;
	}

	@Override
	public void write(Kryo kryo, Output output, TODO object) {
		output.writeInt(object.number);
		output.writeInt(object.parentNumber);
		output.writeString(object.descr);
	}

}
