package by.epam.web.unit6.dao.impl;

import by.epam.web.unit6.bean.Tarif;
import by.epam.web.unit6.dao.DAOException;
import by.epam.web.unit6.dao.TarifDAO;
import by.epam.web.unit6.dto.UserTarif;
import by.epam.web.unit6.pool.ConnectionPool;
import by.epam.web.unit6.pool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLTarifDAO implements TarifDAO {
    private final static Logger logger = LogManager.getLogger();
    private final static ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final static String UPD_TARIF="UPDATE  tarif (id, name, description, price, speed, discount) values (?,?,?,?,?,?);";
    private final static String ADD_TARIF = "INSERT INTO tarif (id, name, description, price, speed, discount) values (?,?,?,?,?,?);";
    private final static String GET_TARIF_BY_ID = "SELECT * FROM tarif WHERE id=?;";
    private final static String GET_ALL_TARIF = "SELECT * FROM tarif;";
    private final static String GET_MAX_ID = "SELECT MAX(id) FROM tarif;";
    private final static String DEL_TARIF_BY_ID="DELETE  FROM tarif WHERE id=?;";
    private final static String GET_TARIF_BY_USER_ID="SELECT mytelecom.tarif.*, mytelecom.tarif_note.create_time,mytelecom.tarif_note.id AS 'noteId'  FROM mytelecom.tarif, mytelecom.tarif_note WHERE mytelecom.tarif_note.tarif_id=mytelecom.tarif.id AND mytelecom.tarif_note.user_id=?;";

    private Map<String, PreparedStatement> preparedStatementMap = new HashMap<>();

    public SQLTarifDAO(){
        try (Connection connection = connectionPool.takeConnection()) {
            setPreparedStatement(connection, UPD_TARIF);
            setPreparedStatement(connection, ADD_TARIF);
            setPreparedStatement(connection, GET_TARIF_BY_ID);
            setPreparedStatement(connection, GET_ALL_TARIF);
            setPreparedStatement(connection, GET_MAX_ID);
            setPreparedStatement(connection, DEL_TARIF_BY_ID);
            setPreparedStatement(connection, GET_TARIF_BY_USER_ID);

        } catch (ConnectionPoolException | SQLException | DAOException e) {
            logger.error(e);

        }
    }
    private void setPreparedStatement(Connection connection, String sql) throws DAOException {
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatementMap.put(sql, preparedStatement);
            } catch (SQLException e) {
                logger.error(e);
                throw new DAOException(e);

            }
        }
    }

    private Tarif convertFromResultSet(ResultSet resultSet) throws DAOException{
        Tarif tarif = new Tarif();
        try {
            tarif.setId(resultSet.getInt("id"));
            tarif.setName(resultSet.getString("name"));
            tarif.setDescription(resultSet.getString("description"));
            tarif.setPrice(resultSet.getDouble("price"));
            tarif.setSpeed(resultSet.getInt("speed"));
            tarif.setDiscount(resultSet.getDouble("discount"));
        }catch (SQLException e){
            logger.error(e);
            throw new DAOException(e);
        }
        return tarif;
    }
    @Override
    public boolean addTarif(Tarif tarif) throws DAOException {
        boolean flag = false;
        try  {
            PreparedStatement statement = preparedStatementMap.get(ADD_TARIF);

            statement.setInt(1, tarif.getId());
            statement.setString(2, tarif.getName());
            statement.setString(3, tarif.getDescription());
            statement.setDouble(4, tarif.getPrice());
            statement.setInt(5, tarif.getSpeed());
            statement.setDouble(6, tarif.getDiscount());


            if (statement.executeUpdate() == 1) {
                flag = true;
                logger.info("Add tarif OK");
            }

        } catch (SQLException e) {
            logger.error("Can't add tarif");
            throw new DAOException(e);
        }
        return flag;
    }

    @Override
    public boolean editTarif(Tarif tarif) throws DAOException {
        boolean flag = false;
        try  {
            PreparedStatement statement = preparedStatementMap.get(UPD_TARIF);

            statement.setInt(1, tarif.getId());
            statement.setString(2, tarif.getName());
            statement.setString(3, tarif.getDescription());
            statement.setDouble(4, tarif.getPrice());
            statement.setInt(5, tarif.getSpeed());
            statement.setDouble(6, tarif.getDiscount());

            if (statement.executeUpdate() == 1) {
                flag = true;
                logger.info("Add tarif OK");
            }
        } catch (SQLException e) {
            logger.error("Can't add tarif");
            throw new DAOException(e);
        }
        return flag;
    }

    @Override
    public Tarif getTarifById(int id) throws DAOException {
        Tarif tarif=null;
        try  {
            PreparedStatement statement = preparedStatementMap.get(GET_TARIF_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                tarif=convertFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            logger.error("Can't get tarif by Id");
            throw new DAOException(e);
        }

        return tarif;
    }

    @Override
    public List<Tarif> getAll() throws DAOException {
        List<Tarif> tarifs = new ArrayList<>();
        try  {
            PreparedStatement statement = preparedStatementMap.get(GET_ALL_TARIF);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tarif tarif = convertFromResultSet(resultSet);

                tarifs.add(tarif);

            }
        } catch (SQLException e) {
            logger.error(" can't got all tarifs");
            throw new DAOException(e);
        }
        return tarifs;
    }



    //стоит ли удалять информацию о тарифе из таблицы записи тарифов на которые подключены пользователи?
    @Override
    public boolean deleteTarifById(int id) throws DAOException {
        boolean flag = false;

        try  {
            PreparedStatement statement = preparedStatementMap.get(DEL_TARIF_BY_ID);
            statement.setInt(1, id);
            if (statement.executeUpdate() == 1) {
                flag = true;
            }

        } catch (SQLException e) {
            logger.error("can't delete tarif");
            throw new DAOException(e);

        }

        return flag;
    }

    @Override
    public List<UserTarif> getTarifByUserId(int id) throws DAOException {
        List<UserTarif> tariffs = new ArrayList<>();
        try  {
            PreparedStatement statement = preparedStatementMap.get(GET_TARIF_BY_USER_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
// DTO нельзя объединить в один метод
                UserTarif tarif = new UserTarif();
                tarif.setUserId(id);
                tarif.setTarifId(resultSet.getInt("id"));
                tarif.setName(resultSet.getString("name"));
                tarif.setDescription(resultSet.getString("description"));
                tarif.setSpeed(resultSet.getInt("speed"));
                tarif.setDiscount(resultSet.getDouble("discount"));
                tarif.setPrice(resultSet.getDouble("price"));
                tarif.setDate(resultSet.getDate("create_time"));
                tarif.setDiscount(resultSet.getDouble("discount"));
                tarif.setNoteId(resultSet.getInt("noteId"));
                logger.info("dao получили тариф по id ");
                tariffs.add(tarif);
            }


        } catch (SQLException e) {
            logger.error("can't get tariff list by user id");
            throw new DAOException(e);

        }

        return tariffs;
    }
}
