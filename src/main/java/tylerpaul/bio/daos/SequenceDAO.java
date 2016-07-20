package tylerpaul.bio.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tylerpaul.bio.models.Sequence;

@Repository
public class SequenceDAO implements ISequenceDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addSequence(Sequence sequence) {
		Session session = sessionFactory.getCurrentSession();

		session.save(sequence);
	}
	
	@Override
	public void updateSequence(Sequence sequence) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(sequence);
	}
	
	@Override
	public void deleteSequence(int sequenceID) {
		Session session = sessionFactory.getCurrentSession();

		Sequence sequence = (Sequence)session.get(Sequence.class, sequenceID);
		session.delete(sequence);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Sequence> getSequences(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		List<Sequence> sequences = new ArrayList<Sequence>();
		String hql = "SELECT user.sequences FROM User user WHERE user.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		sequences = query.list();

		return sequences;
	}
	
	@Override
	public Sequence getSequence(int sequenceId) {
		Session session = sessionFactory.getCurrentSession();
		
		Sequence sequence = null;
		String hql = "FROM Sequence sequence WHERE sequence.id = :sequenceid";
		Query query = session.createQuery(hql);
		query.setParameter("sequenceid", sequenceId);
		sequence = (Sequence) query.list().get(0);
		
		return sequence;
	}
}
