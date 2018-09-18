package ERP.eXpertSoft.wfsi.loianes.dao;

import java.util.List;

import ERP.eXpertSoft.wfsi.loianes.model.Contact;

public interface IContactDAO {

	List<Contact> getContacts();
	
	void deleteContact(int id);
	
	Contact saveContact(Contact contact);
	
}
