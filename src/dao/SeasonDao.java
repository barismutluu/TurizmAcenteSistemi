package dao;

import core.Db;
import entity.Season;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {
    private final Connection con;

    public SeasonDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Season> getSeasonsByOtelId(int otelId) {
        ArrayList<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM public.hotel_season WHERE hotel_id = ?";

        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, otelId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Season season = match(rs);
                seasons.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasons;
    }

    public Season getByID(int id) {
        Season obj = null;
        String query = "SELECT * FROM public.hotel_season WHERE season_id = ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Season match(ResultSet rs) throws SQLException {
        Season obj = new Season();
        obj.setId(rs.getInt("season_id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setStart_date(LocalDate.parse(rs.getString("start_date")));
        obj.setFinish_date(LocalDate.parse(rs.getString("finish_date")));


        return obj;
    }

    public ArrayList<Season> findAll() {
        ArrayList<Season> seasonList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel_season";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {

                seasonList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }

    public boolean save(Season season) {
        String query = "INSERT INTO public.hotel_season" +
                "(" +
                "hotel_id," +
                "start_date," +
                "finish_date" +
                ")" +
                "VALUES (?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, season.getHotel_id());
            pr.setDate(2, Date.valueOf(season.getStart_date()));
            pr.setDate(3, Date.valueOf(season.getFinish_date()));
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean delete(int hotel_id) {
        try {
            String query = "DELETE FROM public.hotel_season WHERE season_id = ?";
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
