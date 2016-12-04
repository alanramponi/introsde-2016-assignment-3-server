package introsde.assignment.soap.ws;

import java.util.List;

import javax.jws.WebService;

import introsde.assignment.soap.model.Measure;
import introsde.assignment.soap.model.Measurement;
import introsde.assignment.soap.model.MeasurementHistory;
import introsde.assignment.soap.model.Person;

/**
 * Service implementation
 * @author alan
 *
 */

@WebService(endpointInterface="introsde.assignment.soap.ws.People", serviceName="People")
public class PeopleImplementation implements People {

	@Override
	public List<Person> readPersonList() {
		System.out.println("Executing readPersonList()...");
		List<Person> people = Person.getAllPeople();
		
		System.out.println("\tReturning the whole list of people...");
		return people;
	}

	@Override
	public Person readPerson(Long id) {
		System.out.println("Executing readPerson()...");
		Person person = Person.getPersonById(id.intValue());
		
		System.out.println("\tReturning the person with ID " + id.toString() + "...");
		return person;
	}

	@Override
	public Person updatePerson(Person p) {
		System.out.println("Executing updatePerson()...");
		p.setHealthProfile(null);	// prevent updates on the person's health profile
		p.updatePerson(p);			// update data (without updating the health profile)
		
		System.out.println("\tReturning the person updated (ID: " + p.getId() + ")...");
		return p;
	}

	@Override
	public Person createPerson(Person p) {
		System.out.println("Executing createPerson()...");
		Person person = Person.savePerson(p);	// create person and its health profile
		
		System.out.println("\tReturning the person created (ID: " + person.getId() + ")...");
		return person;
	}

	@Override
	public void deletePerson(Long id) {
		System.out.println("Executing deletePerson()...");
		Person person = Person.getPersonById(id.intValue());
		
		if (person != null) {					// check if the person exists
			Person.deletePerson(person);		// if yes, delete it
			System.out.println("\tDeleting the person with ID: " + person.getId() + "...");
		} else {								// o.w., print an error message
			System.out.println("\tERROR! The person with ID: " + id + " doesn't exist!");
		}
	}
	
	@Override
	public List<MeasurementHistory> readPersonHistory(Long id, String measureType) {
		System.out.println("Executing readPersonHistory()...");
		Person person = Person.getPersonById(id.intValue());
		List<MeasurementHistory> history = MeasurementHistory.getHistoryOfAMeasure(person, measureType);
		
		System.out.println("\tReturning the \"" + measureType + "\" history of the person with ID: " + id + "...");
		return history;
	}

	@Override
	public List<Measure> readMeasureTypes() {
		System.out.println("Executing readMeasureTypes()...");
		List<Measure> measures = Measure.getAllMeasures();
		
		System.out.println("\tReturning the whole list of measures...");
		return measures;
	}

	@Override
	public MeasurementHistory readPersonMeasure(Long id, String measureType, Long mid) {
		System.out.println("Executing readPersonMeasure()...");
		Person person = Person.getPersonById(id.intValue());
		MeasurementHistory measurement = MeasurementHistory.getHistoryOfAMeasureById(
				person, mid.intValue(), measureType);
		
		System.out.println("\tReturning the \"" + measureType + "\" with mID: " + mid + 
				" of the person with ID: " + id + "...");
		return measurement;
	}

	@Override
	public Measurement savePersonMeasure(Long id, Measurement m) {
		System.out.println("Executing savePersonMeasure()...");
		Person person = Person.getPersonById(id.intValue());
		m.setPerson(person);
		Measurement mCurr = Measurement.getMeasure(person, m.getMeasureName());
		int mIdCurr = (mCurr!=null) ? mCurr.getId() : -1;	// get the current measure ID

		mCurr = Measurement.updateMeasurement(mIdCurr, m);	// update/create the measurement
		MeasurementHistory.addMeasurementToHistory(mCurr);	// add it to the history
		
		System.out.println("\tReturning the saved measurement with mID: " + mIdCurr + 
				" of the person with ID: " + id + "...");
		return mCurr;
	}

	@Override
	public Long updatePersonMeasure(Long id, MeasurementHistory m) {
		System.out.println("Executing updatePersonMeasure()...");
		Person person = Person.getPersonById(id.intValue());
		MeasurementHistory mHistory = MeasurementHistory.getHistoryOfAMeasureById(
				person, m.getId(), m.getMeasureName());
		
		mHistory.setMeasureValue(m.getMeasureValue());
		MeasurementHistory.updateMeasurementHistory(mHistory);
		
		return Long.valueOf(mHistory.getId());
	}
}