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
     * впоследствии присваивается приватному полю Connection для хранения данных о
     * подключении к базе данных
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
        String query = "select fam_sotrud, name_sotrud from \"DNS\".\"objSotrud\" where id_sotrud = ?";
        String out = "";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(workerID));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            rs = ps.executeQuery();
            rs.next();
            out = rs.getString("fam_sotrud") + " " + rs.getString("name_sotrud");
            rs.close();
            ps.close();
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
                    out += nameColumn[i] + ": " + rs.getString(i + 1) + "    ";
                }
                out += "\n";
            }
            rs.close();
            ps.close();
        } catch (SQLException throwables) {
            out = "Ошибка в названии таблицы или внутренняя ошибка";
        }
        return out;
    }

    /**
     * Создаётся пометка в таблице arcSkladSotrud (таблица, куда сотрудник делает пометку с
     * текстом приказа, временем и своим id об архивации плана), затем формируеруется
     * строка запроса, подставляются поля с текстом и id сотрудника. Перед этим происходит
     * выяснение какое поле выбрать исходя из поступившей таблицы (id_plan_s или id_plan).
     * Далее паршу String в int и подставляю во второй запрос через setInt, удаляя тем самым
     * строку с нужным id. Далее через триггер эта строка перенесётся в нужную таблицу
     *
     * @param text текст приказа
     * @param plid id плана, который нужно архивировать
     * @param strid id сотрудника, который производит архивирование
     * @param table таблица, из который нужно перенести данные в архив
     */
    public void archiveInformation(String text, String plid, String strid, @NotNull String table) {
        ResultSet rs;
        String querySS = "insert into \"DNS\".\"arcSkladSotrud\" (date_arc, text_report, id_sotrud) VALUES (current_date, ?, ?);";
        String[] arc = {"objPSotrud", "objPSklad"};
        String[] arc_atr = {"id_plan_s", "id_plan"};
        String need = "";
        if (table.equalsIgnoreCase(arc[0])) {
            need = arc_atr[0];
        } else {
            if (table.equalsIgnoreCase(arc[1])) {
                need = arc_atr[1];
            } else {
                return;
            }
        }
        String queryArc = "delete from \"DNS\".\"" + table + "\" where " + need + " = ?";
        // arcSkladSotrud
        int stridi = Integer.parseInt(strid);
        try {
            ps = con.prepareStatement(querySS);
            ps.setString(1, text);
            ps.setInt(2, stridi);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ps.executeUpdate();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // arc
        int plidi = Integer.parseInt(plid);
        try {
            ps = con.prepareStatement(queryArc);
            ps.setInt(1, plidi);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ps.executeUpdate();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
