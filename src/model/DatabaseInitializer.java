package model;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {

            String sqlFarm = "CREATE TABLE IF NOT EXISTS farm (" +
                             "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                             "name TEXT NOT NULL," +
                             "location TEXT NOT NULL);";

            String sqlInventory = "CREATE TABLE IF NOT EXISTS inventory (" +
                                  "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                  "item_name TEXT NOT NULL," +
                                  "quantity INTEGER NOT NULL," +
                                  "unit TEXT NOT NULL);";

            String sqlHarvest = "CREATE TABLE IF NOT EXISTS harvest (" +
                                 "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                 "crop_name TEXT NOT NULL," +
                                 "farm_id INTEGER," +
                                 "quantity INTEGER," +
                                 "date TEXT," +
                                 "FOREIGN KEY (farm_id) REFERENCES farm(id));";

            stmt.execute(sqlFarm);
            stmt.execute(sqlInventory);
            stmt.execute(sqlHarvest);

            System.out.println("Tabelas criadas com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}