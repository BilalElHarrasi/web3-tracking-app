package domain.db;

import domain.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonService {
	private Map<String, Person> persons = new HashMap<>();
	
	public PersonService () {
		Person administrator = new Person("001", "admin@ucll.be", "t", "Ad", "Min");
		add(administrator);
		Person klant1 = new Person("101", "boris@ucll.be", "t", "Boris", "Bosmans");
		add(klant1);
		Person klant2 = new Person("102", "fred@ucll.be", "t", "Fred", "Friction");
		add(klant2);
	}
	
	public Person get(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		return persons.get(personId);
	}
	
	public List<Person> getAll(){
		return new ArrayList<Person>(persons.values());	
	}

	public void add(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if (persons.containsKey(person.getUserid())) {
			throw new DbException("User already exists");
		}
		persons.put(person.getUserid(), person);
	}
	
	public void update(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if(!persons.containsKey(person.getUserid())){
			throw new DbException("No person found");
		}
		persons.put(person.getUserid(), person);
	}
	
	public void delete(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		persons.remove(personId);
	}

	public int getNumberOfPersons() {
		return persons.size();
	}
}
