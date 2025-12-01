package dataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import cookit.Recipe;

public class Database_Manager {
    Connection connection;
    String table;


    public Database_Manager() {
        String dbName = "recipes.db";
        table = "recipe_table";
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement statement = null;
        try {
            assert connection != null;
            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + table + " (" +
                    "name Text NOT NULL," +
                    "ingredients TEXT," +
                    "instructions TEXT," +
                    "skill TEXT," +
                    "time INTEGER," +
                    "UNIQUE(name))";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public List<Recipe> fetchRecipeList(){
        List<Recipe> recipes = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.sqlite.JDBC");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + table);
            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String ingredients = resultSet.getString("ingredients");
                String instructions = resultSet.getString("instructions");
                String skillLevel = resultSet.getString("skill");
                int cookTime = resultSet.getInt("time");

                List<String> ingredientsList = new ArrayList<>(List.of(ingredients.split(" ")));
                Recipe recipe = new Recipe(name, ingredientsList, instructions, skillLevel, cookTime);

                recipes.add(recipe);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return recipes;
    }

    public List<Recipe> fetchRecipeList(List<String> names){
        List<Recipe> recipes = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.sqlite.JDBC");
            statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("SELECT * FROM " + table + " WHERE ");
            for(String i : names) {
                sb.append("name = '").append(i).append("' OR ");
            }
            sb.delete(sb.length() - 4, sb.length());

            resultSet = statement.executeQuery(sb.toString());
            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String ingredients = resultSet.getString("ingredients");
                String instructions = resultSet.getString("instructions");
                String skillLevel = resultSet.getString("skill");
                int cookTime = resultSet.getInt("time");

                List<String> ingredientsList = new ArrayList<>(List.of(ingredients.split(" ")));
                Recipe recipe = new Recipe(name, ingredientsList, instructions, skillLevel, cookTime);

                recipes.add(recipe);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return recipes;
    }

    public List<Recipe> fetchRecipeList(List<String> ingredients, String skill, String time){
        List<Recipe> recipes = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.sqlite.JDBC");
            statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("SELECT * FROM " + table + " WHERE ");
            for(String i : ingredients) sb.append("ingredients LIKE '%").append(i).append("%' AND ");
            if (!skill.equalsIgnoreCase("any skill")) {
                sb.append("skill LIKE '%").append(skill).append("%' AND ");
            }

            if(time.equalsIgnoreCase("under 30")) {
                sb.append("time < 30");

            } else if (time.equalsIgnoreCase("30-60")) {
                sb.append("time >= 30 AND time <= 60");
            } else if (time.equalsIgnoreCase("over 60")) {
                sb.append("time > 60");
            } else { sb.delete(sb.length() - 5, sb.length()); }

            resultSet = statement.executeQuery(sb.toString());

            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String ingredients_string = resultSet.getString("ingredients");
                String instructions = resultSet.getString("instructions");
                String skillLevel = resultSet.getString("skill");
                int cookTime = resultSet.getInt("time");

                List<String> ingredientsList = this.ingredientsToList(ingredients_string);
                Recipe recipe = new Recipe(name, ingredientsList, instructions, skillLevel, cookTime);

                recipes.add(recipe);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return recipes;
    }

    public boolean saveRecipe(Recipe recipe){
        PreparedStatement preparedStatement = null;

        try {


            String sql = "INSERT OR IGNORE INTO " + table + " (name, ingredients, instructions, skill, time) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, this.ingredientsToString(recipe.getIngredients()));
            preparedStatement.setString(3, recipe.getInstructions());
            preparedStatement.setString(4, recipe.getSkillLevel());
            preparedStatement.setInt(5, recipe.getCookTime());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private List<String> ingredientsToList(String ingredients) {
        List<String> list = new ArrayList<>(List.of(ingredients.split(" ")));
        for(String ing : list) ing = ing.replaceAll("_", " ");
        return list;
    }
    private String ingredientsToString(List<String> ingredients) {
        StringBuilder sb = new StringBuilder();
        for (String ing : ingredients) {
            ing = ing.replaceAll(" ", "_");
            sb.append(ing).append(" ");
        }
        return sb.toString();
    }
}
