package com.qxcode.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qxcode.JDBC.JDBC;
import com.qxcode.Model.Category;

public class CategoryDAO {

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categoria";


            try (Connection conn = JDBC.getConnection();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery();){

                while (resultSet.next()) {
                    int id = resultSet.getInt("id_categoria");
                    String title = resultSet.getString("titulo");
                    String description = resultSet.getString("descricao");

                    Category category = new Category(id, title, description);
                    categories.add(category);
                }

                resultSet.close();
                preparedStatement.close();
                return categories;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }

    public String getTitleById(int id) {
        String title = null;
        String sql = "SELECT titulo FROM categoria WHERE id_categoria = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                title = rs.getString("titulo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return title;
    }

    public String getDescriptionById(int id) {
        String description = null;
        String sql = "SELECT descricao FROM categoria WHERE id_categoria = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                description = rs.getString("descricao");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return description;
    }

    public ArrayList<Integer> getCategoryQuestionsId(int id_category) {
        ArrayList<Integer> categories_questions_ids = new ArrayList<>();
        String sql = "SELECT id_questao FROM possuir_categoria WHERE id_categoria = ?";

            try(Connection conn = JDBC.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);) {

                preparedStatement.setInt(1, id_category);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id_questao = resultSet.getInt("id_questao");

                    categories_questions_ids.add(id_questao);
                }

                resultSet.close();
                preparedStatement.close();
                return categories_questions_ids;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }
}
