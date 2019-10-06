package DAO;

public class DAOFactory {
    private static DAOFactory instance;
    private static DAOService dao;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public DAOService getDAO() {
         return dao;
    }
}
