package co.megadodo.todomanager;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class TODODataManagerSerializer extends Serializer<TODODataManager> {

	public TODODataManager read(Kryo kryo, Input input, Class<TODODataManager> clazz) {
		TODODataManager manager = new TODODataManager();
		int size = input.readInt();
		for(int i = 0; i < size; i++) {
			manager.todos.add(kryo.readObject(input, TODO.class));
		}
		return manager;
	}
	
	public void write(Kryo kryo, Output output, TODODataManager object) {
		output.writeInt(object.todos.size());
		for(TODO todo : object.todos) {
			kryo.writeObject(output, todo);
		}
	}
}
