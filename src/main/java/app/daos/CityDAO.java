package app.daos;

import app.config.HibernateConfig;
import app.dtos.CityInfoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class CityDAO implements GenericDAO<CityInfoDTO>
{

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private static CityDAO instance;


    public CityDAO(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public List<CityInfoDTO> createList(List<CityInfoDTO> cities)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(cities);
            em.getTransaction().commit();
        }
        return cities;
    }
    @Override
    public CityInfoDTO create(CityInfoDTO city)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(city);
            em.getTransaction().commit();
        }
        return city;
    }

    @Override
    public CityInfoDTO read(CityInfoDTO city)
    {
        return null;
    }

    @Override
    public CityInfoDTO update(CityInfoDTO city)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.merge(city);
            em.getTransaction().commit();
        }
        return city;
    }

    @Override
    public CityInfoDTO delete(CityInfoDTO city)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.remove(city);
            em.getTransaction().commit();
        }
        return city;
    }

    @Override
    public List<CityInfoDTO> findAll()
    {
        List<CityInfoDTO> cities;
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            String jpql = "SELECT l FROM CityInfoDTO l";
            Query query = em.createQuery(jpql);
            cities = query.getResultList();
            cities.stream().forEach(System.out::println);
            em.getTransaction().commit();
        }
        return cities;
    }



    public static CityDAO getInstance(EntityManagerFactory emf)
    {
        if (instance == null)
        {
            instance = new CityDAO(emf);
        }
        return instance;
    }


}
