/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Navigation;

/**
 *
 * @author An
 */
public class NavigationDAO extends DBContext {

    public static void main(String[] args) {
        NavigationDAO nadao = new NavigationDAO();
        List<Navigation> ls = nadao.getAllNavigation();
        for (Navigation n : ls) {
            System.out.println(n.getNavigationName());
        }
    }

    public List<Navigation> getAllNavigation() {
        List<Navigation> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM dbo.Navigation\n"
                + "WHERE NavigationID <> 5;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Navigation(
                        rs.getInt("NavigationID"),
                        rs.getString("navigationName"),
                        rs.getString("navigationDescription"),
                        rs.getInt("createdBy"),
                        rs.getDate("createdDate"),
                        rs.getInt("modifiedBy"),
                        rs.getDate("modifiedDate")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void DeleteNaigation(int id) {
        String sql = "USE [SQL_SWP_5]\n"
                + "DELETE FROM [dbo].[Navigation]"
                + "      WHERE [Navigation].[navigationID]= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void CreateNavigation(String navigationName, String navigationDescription, String position, int createdBy, int modifiedBy) {
        String sql = "INSERT INTO [dbo].[Navigation]"
                + "    ([navigationName]"
                + "    ,[navigationDescription]"
                + "    ,[createdBy]\n"
                + "    ,[position]\n"
                + "    ,[modifiedBy])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, navigationName);
            st.setString(2, navigationDescription);
            st.setInt(3, createdBy);
            st.setString(4, position);
            st.setInt(5, modifiedBy);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void UpdateNavigation(int id, String navigationName, String navigationDescription, String position, int modifiedBy) {
        String sql = "UPDATE [dbo].[Navigation]"
                + "   SET [navigationName] = ?"
                + "      ,[navigationDescription] = ?"
                + "      ,[position] = ?"
                + "      ,[modifiedBy] = ?"
                + "      ,[modifiedDate] = getdate()"
                + " WHERE [Navigation].[navigationID]=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, navigationName);
            st.setString(2, navigationDescription);
            st.setString(3, position);
            st.setInt(4, modifiedBy);
            st.setInt(5, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
