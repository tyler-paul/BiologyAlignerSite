package tylerpaul.bio.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tylerpaul.bio.models.SequenceAlignment;
import tylerpaul.bio.models.SequenceAlignmentPart;

@Repository
public class AlignmentDAO implements IAlignmentDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public SequenceAlignment getAlignment(int alignmentID) {
		Session session = sessionFactory.getCurrentSession();
		
		SequenceAlignment alignment = null;
		String hql = "FROM SequenceAlignment alignment WHERE alignment.id = :alignmentID";
		Query query = session.createQuery(hql);
		query.setParameter("alignmentID", alignmentID);
		alignment = (SequenceAlignment) query.list().get(0);
		Hibernate.initialize(alignment.getParts());

		return alignment;
	}
	
	@Override
	public void deleteAlignment(int alignmentID) {
		Session session = sessionFactory.getCurrentSession();

		SequenceAlignment alignment = (SequenceAlignment)session.get(SequenceAlignment.class, alignmentID);
		session.delete(alignment);

	}
	
	@Override
	public void saveAlignment(SequenceAlignment alignment) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(alignment);
		for (SequenceAlignmentPart part : alignment.getParts())
			session.saveOrUpdate(part);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SequenceAlignment> getAlignments(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		List<SequenceAlignment> alignments = new ArrayList<SequenceAlignment>();
		String hql = "SELECT user.alignments FROM User user WHERE user.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		alignments = query.list();

		return alignments;
	}
}
