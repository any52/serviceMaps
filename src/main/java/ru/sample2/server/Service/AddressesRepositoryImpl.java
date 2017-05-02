package ru.sample2.server.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.sample2.shared.AddressDTO;
import ru.sample2.shared.SuggestionDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 09.03.2017.
 */
public class AddressesRepositoryImpl implements AddressesRepository {
    private List<AddressDTO> addresses;
    private List<String> streetes;
    private List<Integer> numberHouses;
    private Session session;

    @Override
    public SuggestionDTO getAddressesList(final String streetName) {
        String temp = streetName.toLowerCase() + "%";

        SessionFactory sessions = new Configuration().configure().buildSessionFactory();
        session = sessions.openSession();
//            Transaction tx = session.beginTransaction();
        Query query = session.createQuery("select street from AddressEntity WHERE lower(street) like :param");
        query.setParameter("param", temp);
        streetes = query.list();
//            tx.commit();
        Query query1 = session.createQuery("select numberhome from AddressEntity WHERE lower(street) like :param");
        query1.setParameter("param", temp);
        numberHouses = query1.list();
        session.close();

        if (addresses == null) {
            addresses = new ArrayList<>();
        }
        addresses.clear();
        for (int i = 0; i < streetes.size(); i++) {
            AddressDTO address = new AddressDTO(streetes.get(i).trim(), numberHouses.get(i));
            addresses.add(address);
        }


        if (addresses.isEmpty() == true) {
            addresses.add(new AddressDTO("<None>", 0));
        }

        SuggestionDTO suggestionDTO = new SuggestionDTO(addresses);

        return suggestionDTO;
    }

}
