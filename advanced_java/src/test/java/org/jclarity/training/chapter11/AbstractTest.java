package org.jclarity.training.chapter11;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractTest {

    protected static EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;
    
    @BeforeClass
    public static void setUpClass() throws Exception {

        /*
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, javaURLContextFactory.class.getName());
        InitialContext initialContext = new InitialContext();
        initialContext.createSubcontext("java:");
        initialContext.createSubcontext("java:comp");
        initialContext.createSubcontext("java:comp/env");
        initialContext.createSubcontext("java:comp/env/jdbc");
        EmbeddedDataSource ds = new EmbeddedDataSource();
        ds.setDatabaseName("tutorialDB");
        ds.setCreateDatabase("create");
        initialContext.bind("java:comp/env/jdbc/tutorialDS", ds);
        */
        
        entityManagerFactory = Persistence.createEntityManagerFactory("userPersistenceUnit");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        /*
        InitialContext initialContext = new InitialContext();
        initialContext.unbind("java:comp/env/jdbc/tutorialDS");
        */
        entityManagerFactory.close();
    }
    
    @Before
    public void beginTransaction() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @After
    public void rollbackTransaction() {   
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }

        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}