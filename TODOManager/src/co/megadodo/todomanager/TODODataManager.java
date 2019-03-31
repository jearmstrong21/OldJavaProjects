package co.megadodo.todomanager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class TODODataManager {

	String fileName = "TODOS.txt";
	public static void main(String[] args) {
		TODODataManager manager = new TODODataManager();
		TODO AOPS = new TODO("Art of Problem Solving");

		TODO CP = new TODO("Introduction to Counting & Probability");
		manager.write();
		manager.addTODO(AOPS);
		manager.addTODO(CP,AOPS);
		manager.removeTODO(0);
		manager.print();
	}
	
	public void print() {
		for(TODO todo : todos) {
			System.out.println("TODO:");
			System.out.println(todo.descr);
			System.out.println("Number: " + todo.number);
			System.out.println("Parent number: " + todo.parentNumber);
		}
	}
	
	public void removeTODO(int ind) {
		ArrayList<Integer> removed = new ArrayList<Integer>();
		removed.add(ind);
		boolean cont = true;
		while(cont) {
			cont = false;
			for(TODO t : todos) {
				if(removed.contains(t.parentNumber) && !removed.contains(t.number)) {
					removed.add(t.number);
					cont = true;
				}
			}
		}
		for(int index = 0; index < removed.size(); index++) {
			int removedIndex = removed.get(index);
			int actualIndex = -1;
			
			for(int i = 0; i < todos.size(); i++) {
				if(todos.get(i).number == removedIndex) {
					actualIndex = i;
				}
			}
			
			if(actualIndex != -1) {
				todos.remove(actualIndex);
			}
		}
	}
	
	public void addTODO(TODO todo) {
		addTODO(todo, null);
	}
	
	public void addTODO(TODO todo, TODO parent) {
		todo.number = todos.size();
		if(parent != null) {
			todo.parentNumber = parent.number;
		} else {
			todo.parentNumber = -1;
		}
		todos.add(todo);
	}

	ArrayList<TODO> todos;
	
	public TODODataManager() {
		todos = new ArrayList<TODO>();
	}
	
	public static TODODataManager read() {
		try {
			Kryo kryo = new Kryo();
			Input input = new Input(new FileInputStream("todo_data"));
			return kryo.readObject(input, TODODataManager.class);
		} catch(Throwable t) {
			System.out.println("Reading error");
			t.printStackTrace();
		}
		return null;
	}
	
	public void write() {
		try {
			Kryo kryo = new Kryo();
			Output output = new Output(new FileOutputStream("todo_data"));
			kryo.writeObject(output, this);
			output.close();
		} catch (Throwable t) {
			System.out.println("Writing error");
			t.printStackTrace();
		}
	}
}
