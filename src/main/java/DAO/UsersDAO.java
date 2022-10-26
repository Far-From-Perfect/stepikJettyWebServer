package DAO;

import accounts.Users;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UsersDAO {
    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public Users get(long id) throws HibernateException {
        return (Users) session.get(Users.class, id);
    }

    public long getUserId(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(Users.class);
        return ((Users) criteria.add(Restrictions.eq("login", login)).uniqueResult()).getId();
    }

    public long insertUser(String login, String password) throws HibernateException {
        return (Long) session.save(new Users(login, password));
    }
}
