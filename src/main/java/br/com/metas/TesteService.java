package br.com.metas;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/rest")
public class TesteService {

	@GET
	public String testRest() {
		return "REST OK";
	}

	@Transactional
	@GET
	@Path("/getPerson")
	public String getPerson() {
		EntityManager entityManager = null;
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		Query q = entityManager.createQuery("From Person");

		List<Person> list = q.getResultList();
		String retorno = "";
		for (Person person : list) {
			retorno = retorno + ", " + person.getFirstName();
		}

		return retorno;

	}

	@GET
	@Path("/salvarPerson")
	@Transactional
	public String salvarPerson() {

		EntityManager entityManager = null;
		try {
			entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

			Person person = new Person();
			person.setFirstName("Thomas");
			person.setMiddleName("Alva");
			person.setLastName("Edison");
			// Set DOB
			Calendar dob = Calendar.getInstance();
			dob.set(Calendar.DATE, 11);
			dob.set(Calendar.MONTH, 02);
			dob.set(Calendar.YEAR, 1947);
			dob.set(Calendar.HOUR_OF_DAY, 0);
			dob.set(Calendar.MINUTE, 0);
			dob.set(Calendar.SECOND, 0);
			dob.set(Calendar.MILLISECOND, 0);
			person.setDob(dob.getTime());

			// Save entity
			entityManager.persist(person);

			System.out.println("Person saved successfully");
		} catch (Exception e) {
			e.printStackTrace();
			if (entityManager != null) {
				System.out.println("Transaction is being rolled back.");
			}
		} 

		return "Sucesso";
	}

}
