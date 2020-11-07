package lab8;

import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class TableWorker {
    private final String url = "jdbc:postgresql://localhost:5433/uchebDB";
    private final String login = "postgres";
    private final String pass = "rstjke";
    private Connection con = connectToPostgreSQL();
    private PreparedStatement ps;

    /**
     * Создаётся подключение к базе данных через драйвер pgJDBC. В Connection
     * попадают данные о подключении и затем возвращается тип данных Connection, который
     * впоследствии присваивается приватному полю Connection для создания постоянного
     * подключения к базе данных
     *
     * @return данные подключения
     */
    private Connection connectToPostgreSQL() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url, login, pass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    /**
     * Производится sql запрос в таблицу с ID, который вписал человек при запуске программы,
     * если id не будет найден, то в catch попадёт кортеж ошибок, следовательно, эта ошибка
     * может быть вызвана только тем, что id сотрудника отсутствует. Значит присваиваю out
     * соответствующую информацию о том, что такого номера нет и возвращаю строку
     *
     * @param workerID id сотрудника
     * @return строка с фамилией и именем или "Такого номера нет!"
     */
    public String workerCheck(String workerID) {
        ResultSet rs = null;
        String query = "select fam_sotrud, name_sotrud from \"DNS\".\"objSotrud\" where id_sotrud = " + workerID;
        String out = "";
        try {
            ps = con.prepareStatement(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            rs = ps.executeQuery();
            rs.next();
            out = rs.getString("fam_sotrud") + " " + rs.getString("name_sotrud");
        } catch (SQLException throwables) {
            out = "Такого номера нет!";
        }
        return out;
    }

    /**
     * Выполняется sql запрос к серверу, который должен вернуть какой-то результат. В
     * случае, если результат не удалось получить, то идёт попадание в catch: так как
     * это может быть связано только с неправильным вводом названия таблицы или неверным
     * получением данных (что сводится почти к нулю), то возвращаем соответствующую
     * информацию. Если удалось получить какой-то результат через executeQuery, то
     * получаем данные о количестве колонок, об их именах и присваиваем строке
     * информацию в формате "Колонка: значение"
     *
     * @param tableName название таблицы
     * @return результат запроса
     */
    public String informationOut(String tableName) {
        String out = "";
        String query = "select * from \"DNS\".\"" + tableName + "\"";
        ResultSet rs = null;
        int cntColumn;
        String arr;
        try {
            ps = con.prepareStatement(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            rs = ps.executeQuery();
            cntColumn = rs.getMetaData().getColumnCount();
            String[] nameColumn = new String[cntColumn];
            for (int i = 0; i < cntColumn; i++) {
                nameColumn[i] = rs.getMetaData().getColumnName(i + 1);
            }
            while (rs.next()) {
                for (int i = 0; i < cntColumn; i++) {
                    out += nameColumn[i] + ": " + rs.getString(i + 1) + "    ";
                }
                out += "\n";
            }
        } catch (SQLException throwables) {
            out = "Ошибка в названии таблицы или внутренняя ошибка";
        }
        return out;
    }

    /**
     * Выполняется sql запрос к серверу, который возвращает какие-то данные. Если был сбой,
     * то вернётся строка (после попадания в catch), которая уведомит об ошибке. Иначе, стакаем
     * строку до тех пор, пока есть какой-то незаписанный результат. Стакаем в формате
     * "Колонка: значение", затем возвращаем её
     *
     * @param tableName название таблицы
     * @param key название колонки
     * @param znach значение колонки
     * @return результат запроса
     */
    public String informationKeyOut(String tableName, String key, String znach) {
        String out = "";
        String query = "select * from \"DNS\".\"" + tableName + "\" where " + key + " = " + znach;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            rs = ps.executeQuery();
            int cntColumn = rs.getMetaData().getColumnCount();
            String[] nameColumn = new String[cntColumn];
            for (int i = 0; i < cntColumn; i++) {
                nameColumn[i] = rs.getMetaData().getColumnName(i + 1);
            }
            while (rs.next()) {
                for (int i = 0; i < cntColumn; i++) {
                    out += nameColumn[i] + ": " + rs.getString(i + 1) + " ";
                }
            }
        } catch (SQLException throwables) {
            out = "Ошибка в названии таблицы или внутренняя ошибка";
        }

        return out;
    }

    /**
     * Создаётся пометка в таблице arcSkladSotrud (таблица, куда сотрудник делает пометку с текстом приказа, временем
     * и своим id об архивации плана), затем удаляется информация из плана и вставляется в архив сотрудника или склада
     *
     * @param text текст приказа
     * @param plid id плана, который нужно архивировать
     * @param strid id сотрудника, который производит архивирование
     * @param table таблица, из который нужно перенести данные в архив
     */
    public void archiveInformation(String text, String plid, String strid, @NotNull String table) {
        ResultSet rs;
        String querySS = "insert into \"DNS\".\"arcSkladSotrud\" (date_arc, text_report, id_sotrud) VALUES (current_date, \'" + text + "\', " + strid + ");";
        String[] arc = {"objPSotrud", "objPSklad"};
        String[] arc_atr = {"id_plan_s", "id_plan"};
        String need = "";
        int irow = 0;
        int drow = 0;
        if (table.equalsIgnoreCase(arc[0])) {
            need = arc_atr[0];
        } else {
            if (table.equalsIgnoreCase(arc[1])) {
                need = arc_atr[1];
            } else {
                return;
            }
        }
        String queryArc = "delete from \"DNS\".\"" + table + "\" where " + need + " = " + plid;
        // arcSkladSotrud
        try {
            ps = con.prepareStatement(querySS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            irow = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // arc
        try {
            ps = con.prepareStatement(queryArc);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            drow = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
