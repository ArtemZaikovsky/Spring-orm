package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(int carSeries, String carModel){
      String hql = "FROM User u JOIN FETCH u.car WHERE u.car.model = :model AND u.car.series = :series";
      return (User) sessionFactory
              .getCurrentSession()
              .createQuery(hql)
              .setParameter("model", carModel)
              .setParameter("series", carSeries)
              .setMaxResults(1)
              .uniqueResult();
   }
}
